package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.ShipmentId;
import com.github.monaboiste.shipping.event.DomainEvent;
import com.github.monaboiste.shipping.shipment.event.ShipmentPayload;

import java.util.List;

class ShipmentEventStream {

    private final ShipmentId shipmentId;
    private final List<DomainEvent<ShipmentPayload>> events;

    private int version;

    ShipmentEventStream(ShipmentId shipmentId, List<DomainEvent<ShipmentPayload>> events) {
        this.shipmentId = shipmentId;
        this.events = events;
    }

    ShipmentEventStream(ShipmentId shipmentId,
                        List<DomainEvent<ShipmentPayload>> events,
                        int version) {
        this.shipmentId = shipmentId;
        this.events = events;
        this.version = version;
    }

    ShipmentId shipmentId() {
        return shipmentId;
    }

    List<DomainEvent<ShipmentPayload>> events() {
        return events;
    }

    String version() {
        return Integer.toString(version);
    }

    void incrementVersion() {
        version++;
    }

    void append(List<DomainEvent<ShipmentPayload>> events) {
        this.events.addAll(events);
    }
}
