package com.github.monaboiste.shipping;

import java.time.OffsetDateTime;

public class AllocationContext {

    private final CarrierServiceId carrierServiceId;
    private final OffsetDateTime allocatedAt;

    public AllocationContext(CarrierServiceId carrierServiceId) {
        this(carrierServiceId, OffsetDateTime.now());
    }

    private AllocationContext(CarrierServiceId carrierServiceId, OffsetDateTime allocatedAt) {
        this.carrierServiceId = carrierServiceId;
        this.allocatedAt = allocatedAt;
    }

    public CarrierServiceId carrierServiceId() {
        return carrierServiceId;
    }

    public OffsetDateTime allocatedAt() {
        return allocatedAt;
    }
}
