package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.Shipment;

public record ShipmentDeallocatedPayload(String shipmentId) implements ShipmentPayload {

    public ShipmentDeallocatedPayload(Shipment shipment) {
        this(shipment.id().value());
    }
}
