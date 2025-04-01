package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.Shipment;

import java.time.Instant;
import java.util.UUID;

public class ShipmentAllocated extends ShipmentEvent {
    private final ShipmentAllocatedPayload payload;

    public ShipmentAllocated(Shipment shipment) {
        super(
                UUID.randomUUID().toString(),
                Instant.now(),
                shipment.id().value()
        );
        this.payload = new ShipmentAllocatedPayload(shipment);
    }

    @Override
    public String name() {
        return "ShipmentAllocated";
    }

    @Override
    public ShipmentAllocatedPayload payload() {
        return payload;
    }

    @Override
    public Class<? extends ShipmentEvent> type() {
        return ShipmentAllocated.class;
    }

    @Override
    public void accept(ShipmentEventVisitor visitor) {
        visitor.visit(this);
    }
}
