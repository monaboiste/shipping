package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.event.DomainEvent;
import com.github.monaboiste.shipping.event.Event;
import com.github.monaboiste.shipping.shipment.Shipment;

import java.time.Instant;
import java.util.UUID;

public class ShipmentAllocated implements DomainEvent<ShipmentPayload> {
    private final String eventId;
    private final Instant occurredAt;
    private final String aggregateId;
    private final ShipmentPayload payload;

    public ShipmentAllocated(Shipment shipment) {
        this.eventId = UUID.randomUUID().toString();
        this.occurredAt = Instant.now();
        this.aggregateId = shipment.id().value();
        this.payload = new ShipmentPayload(shipment);
    }

    @Override
    public String aggregateId() {
        return aggregateId;
    }

    @Override
    public Class<? extends Event<ShipmentPayload>> type() {
        return ShipmentAllocated.class;
    }

    @Override
    public String eventId() {
        return eventId;
    }

    @Override
    public String name() {
        return "ShipmentAllocated";
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }

    @Override
    public ShipmentPayload payload() {
        return payload;
    }
}
