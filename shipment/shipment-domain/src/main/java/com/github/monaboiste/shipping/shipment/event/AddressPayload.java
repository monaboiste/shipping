package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.StructuredAddress;
import com.github.monaboiste.shipping.event.Payload;

public record AddressPayload(
        String twoLetterCountryCode,
        String postcode,
        String postalCity,
        String city,
        String town,
        String village,
        String county,
        String state,
        String road,
        String houseNumber
) implements Payload {

    public AddressPayload(StructuredAddress address) {
        this(
                address.country().twoLetterCode(),
                address.postcode(),
                address.postalCity(),
                address.city(),
                address.town(),
                address.village(),
                address.county(),
                address.state(),
                address.road(),
                address.houseNumber()
        );
    }
}
