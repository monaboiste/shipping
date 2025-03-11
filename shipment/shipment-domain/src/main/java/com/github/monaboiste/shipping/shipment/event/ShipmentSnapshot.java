package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.Receiver;
import com.github.monaboiste.shipping.shipment.Sender;
import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.shipment.ShipmentId;

// TODO: make interface
public class ShipmentSnapshot implements Snapshot {
    private final ShipmentId shipmentId;
    private final Sender sender;
    private final Receiver receiver;

    private final AllocationSnapshot allocationSnapshot;

    public ShipmentSnapshot(Shipment shipment) {
        this.shipmentId = shipment.id();
        this.sender = shipment.sender();
        this.receiver = shipment.receiver();

        this.allocationSnapshot = shipment.getAllocation()
                .map(AllocationSnapshot::new)
                .orElse(null);
    }
}
