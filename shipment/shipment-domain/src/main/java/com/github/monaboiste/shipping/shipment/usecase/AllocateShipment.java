package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.CarrierServiceId;
import com.github.monaboiste.shipping.ShipmentId;

public interface AllocateShipment {

    /**
     * Allocates the shipment to the specified carrier service.
     *
     * @param shipmentId       shipment identifier
     * @param carrierServiceId assigned carrier service identifier
     */
    void allocate(ShipmentId shipmentId, CarrierServiceId carrierServiceId);
}
