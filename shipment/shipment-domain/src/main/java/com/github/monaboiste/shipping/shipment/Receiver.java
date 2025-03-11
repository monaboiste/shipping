package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.shipment.error.CannotBeEmptyException;
import org.jetbrains.annotations.Nullable;

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
            throw new CannotBeEmptyException("address");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
