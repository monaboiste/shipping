package com.github.monaboiste.shipping;

import com.github.monaboiste.shipping.error.CannotBeEmptyException;

import static com.github.monaboiste.shipping.error.GenericErrorCodes.EMPTY_ADDRESS;
import static com.github.monaboiste.shipping.error.GenericErrorCodes.EMPTY_PARTY_ID;
import static com.github.monaboiste.shipping.error.GenericErrorCodes.EMPTY_PARTY_NAME;

public abstract class Party {
    private final PartyId id;
    private final String name;
    private final StructuredAddress address;

    protected Party(PartyId id, String name, StructuredAddress address) {
        if (id == null) {
            throw new CannotBeEmptyException(EMPTY_PARTY_ID);
        }
        if (name == null || name.isBlank()) {
            throw new CannotBeEmptyException(EMPTY_PARTY_NAME);
        }
        if (address == null) {
            throw new CannotBeEmptyException(EMPTY_ADDRESS);
        }
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
