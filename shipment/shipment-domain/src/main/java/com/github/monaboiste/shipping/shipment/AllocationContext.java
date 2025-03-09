package com.github.monaboiste.shipping.shipment;

import java.time.OffsetDateTime;

public class AllocationContext {

    private final CarrierServiceId carrierServiceId;
    private final OffsetDateTime allocatedAt;

    public AllocationContext(CarrierServiceId carrierServiceId) {
        this.carrierServiceId = carrierServiceId;
        this.allocatedAt = OffsetDateTime.now();
    }

    public CarrierServiceId carrierServiceId() {
        return carrierServiceId;
    }
}
