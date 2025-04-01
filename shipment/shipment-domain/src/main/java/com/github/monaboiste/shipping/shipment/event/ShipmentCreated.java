package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.event.Event;
import com.github.monaboiste.shipping.shipment.Shipment;

import java.time.Instant;
import java.util.UUID;

public class ShipmentCreated extends ShipmentEvent {
    private final ShipmentCreatedPayload payload;

    public ShipmentCreated(Shipment shipment) {
        super(
                UUID.randomUUID().toString(),
                Instant.now(),
                shipment.id().value()
        );
        this.payload = new ShipmentCreatedPayload(shipment);
    }

    @Override
    public String name() {
        return "ShipmentCreated";
    }

    @Override
    public ShipmentPayload payload() {
        return payload;
    }

    @Override
    public Class<? extends Event<ShipmentPayload>> type() {
        return ShipmentCreated.class;
    }

    @Override
    public void accept(ShipmentEventVisitor visitor) {
        visitor.visit(this);
    }
}
