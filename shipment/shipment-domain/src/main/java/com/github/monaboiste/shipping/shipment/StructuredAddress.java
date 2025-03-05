package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.shipment.error.CannotBeEmptyException;
import org.jetbrains.annotations.Nullable;

public record StructuredAddress(Country country,
                                String postcode,
                                String postalCity,

                                @Nullable String city,
                                @Nullable String town,
                                @Nullable String village,

                                @Nullable String county,
                                @Nullable String state,

                                @Nullable String road,
                                @Nullable String houseNumber) {

    public StructuredAddress {
        if (country == null) {
            throw new CannotBeEmptyException("country");
        }
        if (postcode == null || postcode.isBlank()) {
            throw new CannotBeEmptyException("postcode");
        }
        if (postalCity == null || postalCity.isBlank()) {
            throw new CannotBeEmptyException("postalCity");
        }
        if (city == null || city.isBlank() ||
            town == null || town.isBlank() ||
            village == null || village.isBlank()) {
            throw new CannotBeEmptyException("city", "town", "village");
        }
    }
}
