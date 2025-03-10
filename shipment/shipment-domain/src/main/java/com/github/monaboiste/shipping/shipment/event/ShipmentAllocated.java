package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.Shipment;

import java.time.Instant;
import java.util.UUID;

public class ShipmentAllocated implements DomainEvent<ShipmentSnapshot> {
    private final String aggregateId;
    private final Class<? extends Event<ShipmentSnapshot>> type;
    private final String eventId;
    private final Instant occurredAt;
    private final ShipmentSnapshot payload;

    public ShipmentAllocated(Shipment shipment) {
        this.eventId = UUID.randomUUID().toString();
        this.occurredAt = Instant.now();
        this.type = ShipmentAllocated.class;
        this.aggregateId = shipment.id().value();
        this.payload = new ShipmentSnapshot(shipment);
    }

    @Override
    public String aggregateId() {
        return aggregateId;
    }

    @Override
    public Class<? extends Event<ShipmentSnapshot>> type() {
        return type;
    }

    @Override
    public String eventId() {
        return eventId;
    }

    @Override
    public String name() {
        return ShipmentAllocated.class.getSimpleName();
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }

    @Override
    public ShipmentSnapshot payload() {
        return payload;
    }
}
