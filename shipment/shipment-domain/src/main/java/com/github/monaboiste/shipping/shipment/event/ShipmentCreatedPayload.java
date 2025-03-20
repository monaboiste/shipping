package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.Party;
import com.github.monaboiste.shipping.shipment.Shipment;

public class ShipmentCreatedPayload implements ShipmentPayload {

    private final String shipmentId;
    private final Party sender;
    private final Party receiver;

    public ShipmentCreatedPayload(Shipment shipment) {
        this.shipmentId = shipment.id().value();
        this.sender = shipment.sender();
        this.receiver = shipment.receiver();
    }

    @Override
    public String shipmentId() {
        return shipmentId;
    }

    public Party sender() {
        return sender;
    }

    public Party receiver() {
        return receiver;
    }
}
