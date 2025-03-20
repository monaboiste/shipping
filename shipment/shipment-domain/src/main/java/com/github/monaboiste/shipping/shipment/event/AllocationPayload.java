package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.event.Payload;
import com.github.monaboiste.shipping.shipment.AllocationContext;

import java.time.OffsetDateTime;

public record AllocationPayload(
        String carrierServiceId,
        OffsetDateTime allocatedAt
) implements Payload {

    public AllocationPayload(AllocationContext allocationContext) {
        this(
                allocationContext.carrierServiceId().value(),
                allocationContext.allocatedAt()
        );
    }
}