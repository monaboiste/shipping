package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.BatchEvent;
import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.shipment.ShipmentWriteRepository;

import java.util.List;

/**
 * This class delegates write operations to the underlying repository and subsequently publishes
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
 */
public class PublishingShipmentWriteRepository implements ShipmentWriteRepository {
    private final EventPublisher<ShipmentSnapshot> eventPublisher;
    private final ShipmentWriteRepository delegate;

    public PublishingShipmentWriteRepository(EventPublisher<ShipmentSnapshot> eventPublisher,
                                             ShipmentWriteRepository delegate) {
        this.eventPublisher = eventPublisher;
        this.delegate = delegate;
    }

    @Override
    public void save(Shipment shipment) {
        var events = shipment.flushPendingEvents();
        delegate.save(shipment);

        publish(events);
    }

    /**
     * Publishes a batch of events followed by publishing each event individually.
     *
     * @param events events to publish
     */
    private void publish(List<Event<ShipmentSnapshot>> events) {
        eventPublisher.publish(new BatchEvent<>(events));
        events.forEach(eventPublisher::publish);
    }
}
