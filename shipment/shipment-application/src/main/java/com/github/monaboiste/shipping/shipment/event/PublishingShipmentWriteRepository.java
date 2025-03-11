package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.event.EventPublisher;
import com.github.monaboiste.shipping.event.PublishingWriteRepository;
import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.ShipmentId;
import com.github.monaboiste.shipping.WriteRepository;

class PublishingShipmentWriteRepository extends PublishingWriteRepository<ShipmentId, Shipment, ShipmentSnapshot> {

    public PublishingShipmentWriteRepository(EventPublisher<ShipmentSnapshot> eventPublisher,
                                             WriteRepository<ShipmentId, Shipment> delegate) {
        super(eventPublisher, delegate);
    }
}
