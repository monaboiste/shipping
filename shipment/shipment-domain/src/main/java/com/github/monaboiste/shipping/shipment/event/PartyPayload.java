package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.Party;
import com.github.monaboiste.shipping.event.Payload;
import com.github.monaboiste.shipping.shipment.Company;
import com.github.monaboiste.shipping.shipment.NaturalPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record PartyPayload(
        String partyType,
        String id,
        String name,
        AddressPayload address,
        CompanyPayload companyPayload,
        NaturalPersonPayload naturalPersonPayload
) implements Payload {
    private static final Logger log = LoggerFactory.getLogger(PartyPayload.class);

    public PartyPayload(Party party) {
        this(
                partyType(party),
                party.id().value(),
                party.name(),
                new AddressPayload(party.address()),
                // TODO: revisit - instanceof is fragile, new type may break the code base
                (party instanceof Company company) ? new CompanyPayload(company) : null,
                (party instanceof NaturalPerson person) ? new NaturalPersonPayload(person) : null
        );
    }

    private static String partyType(Party party) {
        if (party instanceof Company) {
            return "Company";
        } else if (party instanceof NaturalPerson) {
            return "NaturalPerson";
        }
        log.error("Cannot determine Party Type");
        return null;
    }

    public record CompanyPayload(String phoneNumer) implements Payload {

        public CompanyPayload(Company company) {
            this(company.phoneNumber().value());
        }
    }

    public record NaturalPersonPayload(
            String firstName,
            String lastName,
            String phoneNumber
    ) implements Payload {

        public NaturalPersonPayload(NaturalPerson person) {
            this(
                    person.firstName(),
                    person.lastName(),
                    person.phoneNumber().value()
            );
        }
    }
}
