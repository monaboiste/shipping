package com.github.monaboiste.shipping.event;

import com.github.monaboiste.shipping.shipment.AllocationContext;
import com.github.monaboiste.shipping.shipment.CarrierServiceId;

import java.time.OffsetDateTime;

public class AllocationSnapshot implements Snapshot {
    private final CarrierServiceId carrierServiceId;
    private final OffsetDateTime allocatedAt;

    public AllocationSnapshot(AllocationContext allocationContext) {
        this.carrierServiceId = allocationContext.carrierServiceId();
        this.allocatedAt = allocationContext.allocatedAt();
    }
}