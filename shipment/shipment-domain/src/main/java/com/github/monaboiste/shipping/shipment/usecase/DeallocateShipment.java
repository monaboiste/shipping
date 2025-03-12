package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.ShipmentId;

public interface DeallocateShipment {

    void deallocate(ShipmentId shipmentId);
}
