package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.event.BatchEvent;
import com.github.monaboiste.shipping.event.EventPublisher;
import com.github.monaboiste.shipping.shipment.event.ShipmentEvent;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
class SourcedShipmentRepository implements ShipmentReadRepository, ShipmentWriteRepository {

    private final ShipmentEventStreamRepository streamRepository;
    private final EventPublisher eventPublisher;

    @Override
    public Optional<Shipment> findById(ShipmentId id) {
        return streamRepository.findById(id)
                .map(this::rehydrate);
    }

    private Shipment rehydrate(ShipmentEventStream stream) {
        return Shipment.recreate(stream.shipmentId(), stream.version(), stream.events());
    }

    /**
     * This class delegates write operations to the underlying event repository and subsequently publishes
     * the pending events. The events are published in a batch to optimize processes in downstream services.
     * Additionally, individual (non-batched) events are also published, allowing subscribers to listen
     * for specific events of interest.
     * <p>
     * If a subscriber is listening to both batched and single events, it is important to avoid duplication
     * in event processing. To prevent duplication, a consumer should handle either batched or single events,
     * but not both.
     * <p>
     * Use Spring's {@code org.springframework.transaction.event.TransactionalEventListener#phase}
     * or manual transaction management to control when events are handled: either within the same transaction
     * (e.g., {@code BEFORE_COMMIT}) or outside of it. Avoid mixing database operations with external TCP calls
     * within a single transaction.
     *
     * @param shipment state to persist
     */
    @Override
    public void save(Shipment shipment) {
        ShipmentEventStream stream = streamRepository.findById(shipment.id())
                .orElseGet(() -> new ShipmentEventStream(shipment.id()));
        stream.incrementVersion();

        if (!Objects.equals(stream.version(), shipment.version())) {
            throw new IllegalStateException();
            // throw new OptimisticLockException(); todo
        }

        var events = shipment.flushPendingIncomingEvents();
        stream.append(events);
        streamRepository.save(stream);

        publish(events);
    }

    /**
     * Publishes a batch of events followed by publishing each event individually.
     *
     * @param events events to publish
     */
    private void publish(List<ShipmentEvent> events) {
        eventPublisher.publish(new BatchEvent<>(events));
        events.forEach(eventPublisher::publish);
    }
}
