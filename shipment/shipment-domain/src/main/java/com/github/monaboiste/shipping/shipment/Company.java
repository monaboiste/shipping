package com.github.monaboiste.shipping.shipment;

import org.jetbrains.annotations.Nullable;

public class Company extends Party {

    private PhoneNumber phoneNumber;

    public Company(PartyId partyId,
                   String companyName,
                   StructuredAddress address,
                   @Nullable PhoneNumber phoneNumber) {
        super(partyId, companyName, address);
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }
}
