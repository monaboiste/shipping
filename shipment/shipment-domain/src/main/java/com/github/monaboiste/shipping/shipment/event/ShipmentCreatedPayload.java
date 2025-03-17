package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.Shipment;

public class ShipmentCreatedPayload implements ShipmentPayload {

    private final String shipmentId;

    public ShipmentCreatedPayload(Shipment shipment) {
        this.shipmentId = shipment.id().value();
    }

    @Override
    public String shipmentId() {
        return shipmentId;
    }
}
