package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.event.Event;

import java.time.Instant;

public abstract class ShipmentEvent implements Event<ShipmentPayload> {
    private final String eventId;
    private final Instant occurredAt;
    private final String aggregateId;

    protected ShipmentEvent(String eventId, Instant occurredAt, String aggregateId) {
        this.eventId = eventId;
        this.occurredAt = occurredAt;
        this.aggregateId = aggregateId;
    }

    public abstract void accept(ShipmentEventVisitor visitor);

    @Override
    public String eventId() {
        return eventId;
    }

    @Override
    public Instant occurredAt() {
        return occurredAt;
    }

    @Override
    public String aggregateId() {
        return aggregateId;
    }
}
