package com.github.monaboiste.shipping.shipment.event;

public interface ShipmentEventVisitor {

    void visit(ShipmentCreated event);

    void visit(ShipmentAllocated event);

}