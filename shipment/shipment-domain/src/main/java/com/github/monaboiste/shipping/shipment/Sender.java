package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.shipment.error.CannotBeEmptyException;
import org.jetbrains.annotations.Nullable;

public class Sender {
    private String firstName;
    private String lastName;
    private StructuredAddress address;

    public Sender(@Nullable String firstName,
                  @Nullable String lastName,
                  @Nullable PhoneNumber phoneNumber) {
        if (address == null) {
            throw new CannotBeEmptyException("address");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
