package com.github.monaboiste.shipping.usecase;

import com.github.monaboiste.shipping.ShipmentId;

public interface VoidShipment {

    void cancel(ShipmentId shipmentId);
}
