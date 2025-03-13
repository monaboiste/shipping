package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.event.DomainEvent;
import com.github.monaboiste.shipping.shipment.Shipment;

import java.time.Instant;
import java.util.UUID;

public class ShipmentReallocated implements DomainEvent<ShipmentPayload> {
    private final String eventId;
    private final Instant occurredAt;
    private final String aggregateId;
    private final ShipmentReallocatedPayload payload;

    public ShipmentReallocated(Shipment shipment, Shipment previousShipment) {
        this.eventId = UUID.randomUUID().toString();
        this.occurredAt = Instant.now();
        this.aggregateId = shipment.id().value();
        this.payload = new ShipmentReallocatedPayload(shipment, previousShipment);
    }

    @Override
    public String aggregateId() {
        return aggregateId;
    }

    @Override
    public Class<? extends DomainEvent<ShipmentPayload>> type() {
        return ShipmentReallocated.class;
    }

    @Override
    public String eventId() {
        return eventId;
    }

    @Override
    public String name() {
        return "ShipmentReallocated";
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }

    @Override
    public ShipmentReallocatedPayload payload() {
        return payload;
    }
}
