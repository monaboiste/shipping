package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.event.DomainEvent;

public interface ShipmentEvent extends DomainEvent<ShipmentPayload> {

    void accept(ShipmentEventVisitor visitor);
}
