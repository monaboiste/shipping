package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.Party;
import com.github.monaboiste.shipping.ShipmentId;
import com.github.monaboiste.shipping.event.Payload;
import com.github.monaboiste.shipping.shipment.Shipment;

public class ShipmentPayload implements Payload {
    private final ShipmentId shipmentId;
    private final Party sender;
    private final Party receiver;

    private final AllocationPayload allocationSnapshot;

    public ShipmentPayload(Shipment shipment) {
        this.shipmentId = shipment.id();
        this.sender = shipment.sender();
        this.receiver = shipment.receiver();

        this.allocationSnapshot = shipment.getAllocation()
                .map(AllocationPayload::new)
                .orElse(null);
    }
}
