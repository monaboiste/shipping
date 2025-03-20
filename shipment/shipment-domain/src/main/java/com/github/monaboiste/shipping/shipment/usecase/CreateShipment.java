package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.shipment.Shipment;

public interface CreateShipment {

    /**
     * Creates a new shipment in the system.
     *
     * <p>Assumes {@link com.github.monaboiste.shipping.ShipmentId} uses UUID generation with negligible collision risk.
     * If identifier generation strategy changes from UUID, this implementation should add
     * explicit uniqueness checks.
     *
     * @param shipment The shipment to be created with a pre-generated {@link com.github.monaboiste.shipping.ShipmentId}
     */
    void create(Shipment shipment);
}
