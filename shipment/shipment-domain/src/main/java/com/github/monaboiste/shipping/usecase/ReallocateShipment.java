package com.github.monaboiste.shipping.usecase;

import com.github.monaboiste.shipping.CarrierServiceId;
import com.github.monaboiste.shipping.ShipmentId;

public interface ReallocateShipment {

    void reallocate(ShipmentId shipmentId, CarrierServiceId carrierServiceId);
}
