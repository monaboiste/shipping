package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.ShipmentId;
import com.github.monaboiste.shipping.shipment.event.ShipmentEvent;

import java.util.List;

class ShipmentEventStream {

    private final ShipmentId shipmentId;
    private final List<ShipmentEvent> events;

    private int version;

    ShipmentEventStream(ShipmentId shipmentId, List<ShipmentEvent> events) {
        this.shipmentId = shipmentId;
        this.events = events;
    }

    ShipmentEventStream(ShipmentId shipmentId,
                        List<ShipmentEvent> events,
                        int version) {
        this.shipmentId = shipmentId;
        this.events = events;
        this.version = version;
    }

    ShipmentId shipmentId() {
        return shipmentId;
    }

    List<ShipmentEvent> events() {
        return events;
    }

    String version() {
        return Integer.toString(version);
    }

    void incrementVersion() {
        version++;
    }

    void append(List<ShipmentEvent> events) {
        this.events.addAll(events);
    }
}
