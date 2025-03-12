package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.AggregateRoot;
import com.github.monaboiste.shipping.CarrierServiceId;
import com.github.monaboiste.shipping.ShipmentId;
import com.github.monaboiste.shipping.error.CannotBeEmptyException;
import com.github.monaboiste.shipping.error.DomainException;
import com.github.monaboiste.shipping.shipment.event.ShipmentAllocated;
import com.github.monaboiste.shipping.shipment.event.ShipmentReallocated;
import com.github.monaboiste.shipping.shipment.event.ShipmentSnapshot;
import com.github.monaboiste.shipping.shipment.event.ShipmentVoided;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static com.github.monaboiste.shipping.shipment.ShipmentStatus.ALLOCATED;
import static com.github.monaboiste.shipping.shipment.ShipmentStatus.MANIFESTED;
import static com.github.monaboiste.shipping.shipment.ShipmentStatus.PENDING;
import static com.github.monaboiste.shipping.shipment.error.ShipmentErrorCodes.CANNOT_ALLOCATE_ALLOCATED;
import static com.github.monaboiste.shipping.shipment.error.ShipmentErrorCodes.CANNOT_DEALLOCATE_MANIFESTED;
import static com.github.monaboiste.shipping.shipment.error.ShipmentErrorCodes.CANNOT_REALLOCATE_NOT_ALLOCATED;
import static com.github.monaboiste.shipping.shipment.error.ShipmentErrorCodes.EMPTY_SHIPMENT_ID;
import static com.github.monaboiste.shipping.shipment.error.ShipmentErrorCodes.EMPTY_SHIPMENT_RECEIVER;
import static com.github.monaboiste.shipping.shipment.error.ShipmentErrorCodes.EMPTY_SHIPMENT_SENDER;

public class Shipment extends AggregateRoot<ShipmentId, ShipmentSnapshot> {

    private final ShipmentId id;
    private Sender sender;
    private Receiver receiver;
    private ShipmentStatus status;

    private AllocationContext allocationContext;

    public Shipment(ShipmentId id, Sender sender, Receiver receiver) {
        if (id == null) {
            throw new CannotBeEmptyException(EMPTY_SHIPMENT_ID);
        }
        if (sender == null) {
            throw new CannotBeEmptyException(EMPTY_SHIPMENT_SENDER);
        }
        if (receiver == null) {
            throw new CannotBeEmptyException(EMPTY_SHIPMENT_RECEIVER);
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
             @Nullable AllocationContext allocationContext) {
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
            throw new DomainException(CANNOT_ALLOCATE_ALLOCATED,
                    "The shipment cannot be allocated as it's %s.".formatted(status));
        }
        status = ALLOCATED;
        allocationContext = new AllocationContext(carrierServiceId);
        appendEvent(new ShipmentAllocated(this));
    }

    public void reallocate(CarrierServiceId carrierServiceId) {
        if (!status.equals(ALLOCATED)) {
            throw new DomainException(CANNOT_REALLOCATE_NOT_ALLOCATED,
                    "The shipment cannot be reallocated as it is not allocated.");
        }
        allocationContext = new AllocationContext(carrierServiceId);
        appendEvent(new ShipmentReallocated(this));
    }

    public void cancel() {
        if (status.afterOrEqual(MANIFESTED)) {
            throw new DomainException(CANNOT_DEALLOCATE_MANIFESTED,
                    "The shipment cannot be cancelled as it has been already manifested.");
        }
        allocationContext = null;
        appendEvent(new ShipmentVoided(this));
    }

    @Override
    public ShipmentId id() {
        return id;
    }

    public Sender sender() {
        return sender;
    }

    public Receiver receiver() {
        return receiver;
    }

    public Optional<AllocationContext> getAllocation() {
        return Optional.ofNullable(allocationContext);
    }
}
