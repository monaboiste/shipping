package com.github.monaboiste.shipping.shipment.error;

import com.github.monaboiste.shipping.error.TranslatableDomainException;

public class ShipmentStatusException extends TranslatableDomainException {

    public ShipmentStatusException(String errorKey, String debugMessage) {
        super(errorKey, debugMessage);
    }
}
