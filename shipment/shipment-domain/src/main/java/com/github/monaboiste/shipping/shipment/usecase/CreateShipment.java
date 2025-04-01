package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.shipment.ShipmentId;

public interface CreateShipment {

    /**
     * Creates a new shipment in the system.
     *
     * <p>Assumes {@link ShipmentId} uses UUID generation with negligible collision risk.
     * If identifier generation strategy changes from UUID, this implementation should add
     * explicit uniqueness checks.
     *
     * @param shipment The shipment to be created with a pre-generated {@link ShipmentId}
     */
    void create(Shipment shipment);
}
