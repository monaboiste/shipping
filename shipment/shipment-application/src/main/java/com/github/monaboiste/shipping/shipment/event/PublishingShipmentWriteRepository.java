package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.ShipmentId;
import com.github.monaboiste.shipping.WriteRepository;
import com.github.monaboiste.shipping.event.EventPublisher;
import com.github.monaboiste.shipping.event.PublishingWriteRepository;
import com.github.monaboiste.shipping.shipment.Shipment;

class PublishingShipmentWriteRepository extends PublishingWriteRepository<ShipmentId, Shipment, ShipmentPayload> {

    public PublishingShipmentWriteRepository(EventPublisher<ShipmentPayload> eventPublisher,
                                             WriteRepository<ShipmentId, Shipment> delegate) {
        super(eventPublisher, delegate);
    }
}
