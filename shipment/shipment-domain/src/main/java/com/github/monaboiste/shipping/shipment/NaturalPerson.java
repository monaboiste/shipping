package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.Party;
import com.github.monaboiste.shipping.PartyId;
import com.github.monaboiste.shipping.PhoneNumber;
import com.github.monaboiste.shipping.StructuredAddress;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class NaturalPerson extends Party {

    private String firstName;
    private String lastName;
    private PhoneNumber phoneNumber;

    public NaturalPerson(PartyId partyId,
                         @Nullable String firstName,
                         @Nullable String lastName,
                         StructuredAddress address,
                         @Nullable PhoneNumber phoneNumber) {
        super(partyId, partyName(firstName, lastName), address);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    private static String partyName(String firstName, String lastName) {
        return Objects.toString(firstName, "") + " " + Objects.toString(lastName, "");
    }
}
