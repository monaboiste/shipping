package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.Shipment;

public record ShipmentCreatedPayload(
        String shipmentId,
        PartyPayload sender,
        PartyPayload receiver
) implements ShipmentPayload {

    public ShipmentCreatedPayload(Shipment shipment) {
        this(
                shipment.id().value(),
                new PartyPayload(shipment.sender()),
                new PartyPayload(shipment.receiver())
        );
    }
}
