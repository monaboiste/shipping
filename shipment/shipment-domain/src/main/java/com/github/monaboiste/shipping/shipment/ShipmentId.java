package com.github.monaboiste.shipping.shipment;

import java.util.UUID;

public record ShipmentId(String value) {

    public ShipmentId() {
        this(UUID.randomUUID().toString());
    }
}
