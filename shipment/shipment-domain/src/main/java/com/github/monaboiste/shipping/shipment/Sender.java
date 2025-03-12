package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.PhoneNumber;
import com.github.monaboiste.shipping.StructuredAddress;
import com.github.monaboiste.shipping.error.CannotBeEmptyException;
import org.jetbrains.annotations.Nullable;

import static com.github.monaboiste.shipping.shipment.error.ShipmentErrorCodes.EMPTY_SHIPMENT_SENDER;

public class Sender {

    private String firstName;
    private String lastName;
    private StructuredAddress address;

    public Sender(@Nullable String firstName,
                  @Nullable String lastName,
                  StructuredAddress address,
                  @Nullable PhoneNumber phoneNumber) {
        if (address == null) {
            throw new CannotBeEmptyException(EMPTY_SHIPMENT_SENDER);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
