package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.shipment.CarrierServiceId;
import com.github.monaboiste.shipping.shipment.ShipmentId;

public interface AllocateShipment {

    void allocate(ShipmentId shipmentId, CarrierServiceId carrierServiceId);
}
