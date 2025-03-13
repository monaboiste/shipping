package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.Shipment;

public class ShipmentReallocatedPayload implements ShipmentPayload {
    private final String shipmentId;
    private final AllocationPayload allocation;
    private final AllocationPayload previousAllocation;

    public ShipmentReallocatedPayload(Shipment shipment, Shipment previousShipment) {
        this.shipmentId = shipment.id().value();
        this.allocation = shipment.getAllocation()
                .map(AllocationPayload::new)
                .orElse(null);
        this.previousAllocation = previousShipment.getAllocation()
                .map(AllocationPayload::new)
                .orElse(null);
    }

    @Override
    public String shipmentId() {
        return shipmentId;
    }
}
