package com.github.monaboiste.shipping;

import java.util.UUID;

public record ShipmentId(String value) {

    public ShipmentId() {
        this(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return value;
    }
}
