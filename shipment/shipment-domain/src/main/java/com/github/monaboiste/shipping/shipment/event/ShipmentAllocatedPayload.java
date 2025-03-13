package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.Shipment;

public class ShipmentAllocatedPayload implements ShipmentPayload {
    private final String shipmentId;
    private final AllocationPayload allocation;

    public ShipmentAllocatedPayload(Shipment shipment) {
        this.shipmentId = shipment.id().value();
        this.allocation = shipment.getAllocation()
                .map(AllocationPayload::new)
                .orElse(null);
    }

    @Override
    public String shipmentId() {
        return shipmentId;
    }
}
