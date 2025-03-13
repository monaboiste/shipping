package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.Shipment;

public class ShipmentDeallocatedPayload implements ShipmentPayload {
    private final String shipmentId;

    public ShipmentDeallocatedPayload(Shipment shipment) {
        this.shipmentId = shipment.id().value();
    }

    @Override
    public String shipmentId() {
        return shipmentId;
    }
}
