package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.shipment.PublishingWriteRepository;
import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.shipment.ShipmentId;
import com.github.monaboiste.shipping.shipment.WriteRepository;
import com.github.monaboiste.shipping.shipment.event.EventPublisher;
import com.github.monaboiste.shipping.shipment.event.ShipmentSnapshot;

public class PublishingShipmentWriteRepository
        extends PublishingWriteRepository<ShipmentId, Shipment, ShipmentSnapshot> {

    public PublishingShipmentWriteRepository(EventPublisher<ShipmentSnapshot> eventPublisher,
                                             WriteRepository<ShipmentId, Shipment> delegate) {
        super(eventPublisher, delegate);
    }
}
