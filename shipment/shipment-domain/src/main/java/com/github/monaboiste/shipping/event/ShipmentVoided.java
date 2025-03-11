package com.github.monaboiste.shipping.event;

import com.github.monaboiste.shipping.Shipment;

import java.time.Instant;
import java.util.UUID;

public class ShipmentVoided implements DomainEvent<ShipmentSnapshot> {
    private final String eventId;
    private final Instant occurredAt;
    private final String aggregateId;
    private final ShipmentSnapshot payload;

    public ShipmentVoided(Shipment shipment) {
        this.eventId = UUID.randomUUID().toString();
        this.occurredAt = Instant.now();
        this.aggregateId = shipment.id().value();
        this.payload = new ShipmentSnapshot(shipment);
    }

    @Override
    public String aggregateId() {
        return aggregateId;
    }

    @Override
    public Class<? extends Event<ShipmentSnapshot>> type() {
        return ShipmentVoided.class;
    }

    @Override
    public String eventId() {
        return eventId;
    }

    @Override
    public String name() {
        return "ShipmentVoided";
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
