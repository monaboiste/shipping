package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.shipment.CarrierServiceId;
import com.github.monaboiste.shipping.shipment.ShipmentId;

public interface ReallocateShipment {

    void reallocate(ShipmentId shipmentId, CarrierServiceId carrierServiceId);
}
