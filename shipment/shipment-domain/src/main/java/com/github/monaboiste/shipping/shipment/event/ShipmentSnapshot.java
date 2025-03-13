package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.Party;
import com.github.monaboiste.shipping.ShipmentId;
import com.github.monaboiste.shipping.event.Snapshot;
import com.github.monaboiste.shipping.shipment.Shipment;

// TODO: make interface
public class ShipmentSnapshot implements Snapshot {
    private final ShipmentId shipmentId;
    private final Party sender;
    private final Party receiver;

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
