package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.shipment.error.CannotBeEmptyException;

public class Shipment {

    private Sender sender;
    private Receiver receiver;
    private ShipmentStatus status;

    public Shipment(Sender sender, Receiver receiver) {
        if (sender == null) {
            throw new CannotBeEmptyException("sender");
        }
        if (receiver == null) {
            throw new CannotBeEmptyException("receiver");
        }

        this.sender = sender;
        this.receiver = receiver;
        this.status = ShipmentStatus.PENDING;
    }
}
