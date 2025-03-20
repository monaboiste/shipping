package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.Party;
import com.github.monaboiste.shipping.PartyId;
import com.github.monaboiste.shipping.PhoneNumber;
import com.github.monaboiste.shipping.StructuredAddress;
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
