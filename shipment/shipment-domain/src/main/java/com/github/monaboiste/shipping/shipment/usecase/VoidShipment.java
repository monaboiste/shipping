package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.shipment.ShipmentId;

public interface VoidShipment {

    void cancel(ShipmentId shipmentId);
}
