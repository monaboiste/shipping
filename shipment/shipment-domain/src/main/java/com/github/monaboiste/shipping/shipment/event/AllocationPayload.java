package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.event.Payload;
import com.github.monaboiste.shipping.shipment.AllocationContext;
import com.github.monaboiste.shipping.CarrierServiceId;

import java.time.OffsetDateTime;

public class AllocationPayload implements Payload {
    private final CarrierServiceId carrierServiceId;
    private final OffsetDateTime allocatedAt;

    public AllocationPayload(AllocationContext allocationContext) {
        this.carrierServiceId = allocationContext.carrierServiceId();
        this.allocatedAt = allocationContext.allocatedAt();
    }
}