package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.event.EventPublisher;
import com.github.monaboiste.shipping.event.ShipmentSnapshot;
import com.github.monaboiste.shipping.shipment.PublishingWriteRepository;
import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.shipment.ShipmentId;
import com.github.monaboiste.shipping.shipment.WriteRepository;

class PublishingShipmentWriteRepository extends PublishingWriteRepository<ShipmentId, Shipment, ShipmentSnapshot> {

    public PublishingShipmentWriteRepository(EventPublisher<ShipmentSnapshot> eventPublisher,
                                             WriteRepository<ShipmentId, Shipment> delegate) {
        super(eventPublisher, delegate);
    }
}
