package com.github.monaboiste.shipping;

import com.github.monaboiste.shipping.error.CannotBeEmptyException;
import org.jetbrains.annotations.Nullable;

import static com.github.monaboiste.shipping.error.GenericErrorCodes.EMPTY_ADDRESS_COUNTRY;
import static com.github.monaboiste.shipping.error.GenericErrorCodes.EMPTY_ADDRESS_LOCALITY;
import static com.github.monaboiste.shipping.error.GenericErrorCodes.EMPTY_ADDRESS_POSTALCITY;
import static com.github.monaboiste.shipping.error.GenericErrorCodes.EMPTY_ADDRESS_POSTCODE;

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
            throw new CannotBeEmptyException(EMPTY_ADDRESS_COUNTRY);
        }
        if (postcode == null || postcode.isBlank()) {
            throw new CannotBeEmptyException(EMPTY_ADDRESS_POSTCODE);
        }
        if (postalCity == null || postalCity.isBlank()) {
            throw new CannotBeEmptyException(EMPTY_ADDRESS_POSTALCITY);
        }
        if (city == null || city.isBlank() ||
            town == null || town.isBlank() ||
            village == null || village.isBlank()) {
            throw new CannotBeEmptyException(EMPTY_ADDRESS_LOCALITY);
        }
    }
}
