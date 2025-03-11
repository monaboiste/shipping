package com.github.monaboiste.shipping;

import org.jetbrains.annotations.NotNull;

public enum ShipmentStatus {

    PENDING("pending"),
    ALLOCATED("allocated"),
    MANIFESTED("manifested"),
    COMPLETED("completed");

    private final String status;

    public boolean after(@NotNull ShipmentStatus status) {
        return this.ordinal() > status.ordinal();
    }

    public boolean afterOrEqual(@NotNull ShipmentStatus status) {
        return this.ordinal() >= status.ordinal();
    }

    ShipmentStatus(String status) {
        this.status = status;
    }
}
