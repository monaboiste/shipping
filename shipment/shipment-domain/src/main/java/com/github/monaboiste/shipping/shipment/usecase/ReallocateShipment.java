package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.CarrierServiceId;
import com.github.monaboiste.shipping.ShipmentId;

public interface ReallocateShipment {

    /**
     * Cancels the allocation and re-allocates the shipment to the new carrier service.
     *
     * @param shipmentId       shipment identifier
     * @param carrierServiceId carrier service identifier
     */
    void reallocate(ShipmentId shipmentId, CarrierServiceId carrierServiceId);
}
