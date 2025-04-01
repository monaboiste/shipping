package com.github.monaboiste.shipping.shipment;

import java.util.UUID;

public record ShipmentId(String value) {

    public ShipmentId() {
        this(UUID.randomUUID().toString());
    }

    /**
     * Holds debug information. Should NOT be used for retrieving
     * the underlying value. Use {@link #value()} instead.
     *
     * @return a text representation of {@code this}
     */
    @Override
    public String toString() {
        return value;
    }
}
