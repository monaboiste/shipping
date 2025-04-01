package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.shipment.ShipmentId;

public interface DeallocateShipment {

    /**
     * Cancels the allocation of the shipment.
     *
     * @param shipmentId shipment identifier
     */
    void deallocate(ShipmentId shipmentId);
}
