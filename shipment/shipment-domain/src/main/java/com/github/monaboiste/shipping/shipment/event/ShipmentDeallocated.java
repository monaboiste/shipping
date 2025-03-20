package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.event.DomainEvent;
import com.github.monaboiste.shipping.shipment.Shipment;

import java.time.Instant;
import java.util.UUID;

public class ShipmentDeallocated implements ShipmentEvent {
    private final String eventId;
    private final Instant occurredAt;
    private final String aggregateId;
    private final ShipmentDeallocatedPayload payload;

    public ShipmentDeallocated(Shipment shipment) {
        this.eventId = UUID.randomUUID().toString();
        this.occurredAt = Instant.now();
        this.aggregateId = shipment.id().value();
        this.payload = new ShipmentDeallocatedPayload(shipment);
    }

    @Override
    public void accept(ShipmentEventVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String aggregateId() {
        return aggregateId;
    }

    @Override
    public Class<? extends DomainEvent<ShipmentPayload>> type() {
        return ShipmentDeallocated.class;
    }

    @Override
    public String eventId() {
        return eventId;
    }

    @Override
    public String name() {
        return "ShipmentDeallocated";
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }

    @Override
    public ShipmentDeallocatedPayload payload() {
        return payload;
    }
}
