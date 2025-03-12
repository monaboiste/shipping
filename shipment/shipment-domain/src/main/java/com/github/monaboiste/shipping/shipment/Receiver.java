package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.PhoneNumber;
import com.github.monaboiste.shipping.StructuredAddress;
import com.github.monaboiste.shipping.error.CannotBeEmptyException;
import com.github.monaboiste.shipping.shipment.error.ShipmentErrorCodes;
import org.jetbrains.annotations.Nullable;

import static com.github.monaboiste.shipping.shipment.error.ShipmentErrorCodes.*;

// todo: party archetype?
public class Receiver {

    private String firstName;
    private String lastName;
    private StructuredAddress address;
    private PhoneNumber phoneNumber;

    public Receiver(@Nullable String firstName,
                    @Nullable String lastName,
                    StructuredAddress address,
                    @Nullable PhoneNumber phoneNumber) {
        if (address == null) {
            throw new CannotBeEmptyException(EMPTY_SHIPMENT_RECEIVER);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
