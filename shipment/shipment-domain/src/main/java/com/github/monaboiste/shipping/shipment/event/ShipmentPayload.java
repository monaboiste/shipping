package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.event.Payload;

public interface ShipmentPayload extends Payload {

    String shipmentId();
}
