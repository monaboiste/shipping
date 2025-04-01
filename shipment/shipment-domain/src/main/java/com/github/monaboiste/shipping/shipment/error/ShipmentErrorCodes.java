package com.github.monaboiste.shipping.shipment.error;

public final class ShipmentErrorCodes {

    public static final String CANNOT_ALLOCATE_ALLOCATED = "shipment.cannot-allocate.allocated";
    public static final String CANNOT_REALLOCATE_NOT_ALLOCATED = "shipment.cannot-reallocate.not-allocated";
    public static final String CANNOT_DEALLOCATE_MANIFESTED = "shipment.cannot-deallocate.manifested";

    public static final String EMPTY_SHIPMENT_ID = "shipment.id.empty";
    public static final String EMPTY_SHIPMENT_SENDER = "shipment.sender.empty";
    public static final String EMPTY_SHIPMENT_RECEIVER = "shipment.receiver.empty";

    public static final String EMPTY_PARTY_ID = "party.id.empty";
    public static final String EMPTY_PARTY_NAME = "party.name.empty";
    public static final String EMPTY_PARTY_ADDRESS = "party.address.empty";

    public static final String EMPTY_ADDRESS_COUNTRY = "address.country.empty";
    public static final String EMPTY_ADDRESS_POSTCODE = "address.postcode.empty";
    public static final String EMPTY_ADDRESS_POSTALCITY = "address.postal-city.empty";
    public static final String EMPTY_ADDRESS_LOCALITY = "address.locality.empty";

    private ShipmentErrorCodes() {
    }
}
