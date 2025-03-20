package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.Party;
import com.github.monaboiste.shipping.shipment.Shipment;

public record ShipmentCreatedPayload(
        String shipmentId,
        Party sender,
        Party receiver
) implements ShipmentPayload {

    public ShipmentCreatedPayload(Shipment shipment) {
        this(
                shipment.id().value(),
                shipment.sender(),
                shipment.receiver()
        );
    }
}
