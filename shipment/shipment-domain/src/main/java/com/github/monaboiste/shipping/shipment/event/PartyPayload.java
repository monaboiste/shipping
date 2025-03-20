package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.Party;
import com.github.monaboiste.shipping.PartyId;
import com.github.monaboiste.shipping.StructuredAddress;

import java.io.Serializable;

public class PartyPayload extends Party implements Serializable {

    protected PartyPayload(PartyId id, String name, StructuredAddress address) {
        super(id, name, address);
    }
}
