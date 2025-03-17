package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.ShipmentId;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class ShipmentEventStreamInMemoryRepository implements ShipmentEventStreamRepository {

    private final Map<ShipmentId, ShipmentEventStream> streams;

    ShipmentEventStreamInMemoryRepository() {
        this(new ConcurrentHashMap<>());
    }

    ShipmentEventStreamInMemoryRepository(Map<ShipmentId, ShipmentEventStream> streams) {
        this.streams = streams;
    }

    @Override
    public Optional<ShipmentEventStream> findById(ShipmentId shipmentId) {
        return Optional.ofNullable(streams.get(shipmentId));
    }

    @Override
    public boolean existsById(ShipmentId id) {
        return streams.containsKey(id);
    }

    @Override
    public void save(ShipmentEventStream stream) {
        streams.put(stream.shipmentId(), stream);
    }
}
