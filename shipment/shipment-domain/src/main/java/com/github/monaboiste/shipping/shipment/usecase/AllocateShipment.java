package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.CarrierServiceId;
import com.github.monaboiste.shipping.ShipmentId;

public interface AllocateShipment {

    void allocate(ShipmentId shipmentId, CarrierServiceId carrierServiceId);
}
