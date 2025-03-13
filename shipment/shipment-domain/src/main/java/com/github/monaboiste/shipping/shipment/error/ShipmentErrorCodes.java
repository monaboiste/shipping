package com.github.monaboiste.shipping.shipment.error;

public final class ShipmentErrorCodes {

    public static final String CANNOT_ALLOCATE_ALLOCATED = "shipment.cannot-allocate.allocated";
    public static final String CANNOT_REALLOCATE_NOT_ALLOCATED = "shipment.cannot-reallocate.not-allocated";
    public static final String CANNOT_DEALLOCATE_MANIFESTED = "shipment.cannot-deallocate.manifested";

    public static final String EMPTY_SHIPMENT_ID = "shipment.id";
    public static final String EMPTY_SHIPMENT_SENDER = "shipment.sender";
    public static final String EMPTY_SHIPMENT_RECEIVER = "shipment.receiver";

    private ShipmentErrorCodes() {
    }
}
