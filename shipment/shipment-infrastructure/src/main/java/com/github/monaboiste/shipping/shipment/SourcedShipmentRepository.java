package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.ShipmentId;
import com.github.monaboiste.shipping.event.BatchEvent;
import com.github.monaboiste.shipping.event.EventPublisher;
import com.github.monaboiste.shipping.shipment.event.ShipmentEvent;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
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

    @Override
    public boolean existsById(ShipmentId id) {
        return streamRepository.existsById(id);
    }

    private Shipment rehydrate(ShipmentEventStream stream) {
        return Shipment.recreate(stream.shipmentId(), stream.version(), stream.events());
    }

    @Override
    public void save(Shipment shipment) {
        ShipmentEventStream stream = streamRepository.findById(shipment.id())
                .orElseGet(() -> new ShipmentEventStream(shipment.id(), new ArrayList<>()));
        stream.incrementVersion();

        if (!Objects.equals(stream.version(), shipment.version())) {
            throw new IllegalStateException();
            // throw new OptimisticLockException(); todo
        }

        List<ShipmentEvent> events = shipment.flushPendingEvents();
        stream.append(events);
        streamRepository.save(stream);

        eventPublisher.publish(new BatchEvent<>(events));
    }
}
