package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.Shipment;

public record ShipmentReallocatedPayload(
        String shipmentId,
        AllocationPayload allocation,
        AllocationPayload previousAllocation
) implements ShipmentPayload {

    public ShipmentReallocatedPayload(Shipment shipment, Shipment previousShipment) {
        this(
                shipment.id().value(),
                shipment.getAllocation().map(AllocationPayload::new).orElse(null),
                previousShipment.getAllocation().map(AllocationPayload::new).orElse(null)
        );
    }
}
