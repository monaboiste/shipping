package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.shipment.error.CannotBeEmptyException;
import com.github.monaboiste.shipping.shipment.error.DomainException;

import static com.github.monaboiste.shipping.shipment.ShipmentStatus.ALLOCATED;
import static com.github.monaboiste.shipping.shipment.ShipmentStatus.PENDING;

public class Shipment {

    private final ShipmentId id;
    private Sender sender;
    private Receiver receiver;
    private ShipmentStatus status;
    private AllocationContext allocationContext;

    public Shipment(ShipmentId id, Sender sender, Receiver receiver) {
        if (id == null) {
            throw new CannotBeEmptyException("id");
        }
        if (sender == null) {
            throw new CannotBeEmptyException("sender");
        }
        if (receiver == null) {
            throw new CannotBeEmptyException("receiver");
        }

        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.status = PENDING;
        this.allocationContext = null;
    }

    /**
     * Rehydrated shipment object.
     * TODO: switch to flexible constructor if/when JEP 482 feature is released.
     */
    Shipment(ShipmentId id,
             Sender sender,
             Receiver receiver,
             ShipmentStatus status,
             AllocationContext allocationContext) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
        this.allocationContext = allocationContext;
    }

    public boolean isAllocated() {
        return this.status.afterOrEqual(ALLOCATED);
    }

    public void allocate(CarrierServiceId carrierServiceId) {
        if (status.after(ALLOCATED)) {
            throw new DomainException("The shipment cannot be allocated as it's %s.".formatted(status));
        }
        status = ALLOCATED;
        allocationContext = new AllocationContext(carrierServiceId);
        // TODO: fire an event
    }

    public void reallocate(CarrierServiceId carrierServiceId) {
        if (!status.equals(ALLOCATED)) {
            throw new DomainException("The shipment cannot be reallocated as it is not allocated.");
        }
        allocationContext = new AllocationContext(carrierServiceId);
        // TODO: fire an event
    }
}
