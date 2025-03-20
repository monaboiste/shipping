package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.event.DomainEvent;
import com.github.monaboiste.shipping.shipment.Shipment;

import java.time.Instant;
import java.util.UUID;

public class ShipmentCreated implements ShipmentEvent {
    private final String eventId;
    private final Instant occurredAt;
    private final String aggregateId;
    private final ShipmentCreatedPayload payload;

    public ShipmentCreated(Shipment shipment) {
        this.eventId = UUID.randomUUID().toString();
        this.occurredAt = Instant.now();
        this.aggregateId = shipment.id().value();
        this.payload = new ShipmentCreatedPayload(shipment);
    }

    @Override
    public void accept(ShipmentEventVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String eventId() {
        return eventId;
    }

    @Override
    public String name() {
        return "ShipmentCreated";
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }

    @Override
    public ShipmentPayload payload() {
        return payload;
    }

    @Override
    public String aggregateId() {
        return aggregateId;
    }

    @Override
    public Class<? extends DomainEvent<ShipmentPayload>> type() {
        return ShipmentCreated.class;
    }
}
