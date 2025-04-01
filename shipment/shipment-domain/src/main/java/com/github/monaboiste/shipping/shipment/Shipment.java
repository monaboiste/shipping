package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.AggregateRoot;
import com.github.monaboiste.shipping.error.CannotBeEmptyException;
import com.github.monaboiste.shipping.shipment.error.ShipmentStatusException;
import com.github.monaboiste.shipping.shipment.event.ShipmentAllocated;
import com.github.monaboiste.shipping.shipment.event.ShipmentCreated;
import com.github.monaboiste.shipping.shipment.event.ShipmentDeallocated;
import com.github.monaboiste.shipping.shipment.event.ShipmentEvent;
import com.github.monaboiste.shipping.shipment.event.ShipmentEventVisitor;
import com.github.monaboiste.shipping.shipment.event.ShipmentReallocated;

import java.util.List;
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

public class Shipment extends AggregateRoot<ShipmentId, ShipmentEvent> {

    private ShipmentId id;
    private Party sender;
    private Party receiver;
    private ShipmentStatus status;

    private AllocationContext allocationContext;

    /**
     * Rehydrate {@code Shipment} from the event history.
     */
    static Shipment recreate(ShipmentId shipmentId, String version, List<ShipmentEvent> events) {
        return new Shipment(shipmentId, version, events);
    }

    private Shipment(ShipmentId id, String version, List<ShipmentEvent> events) {
        super(Integer.parseInt(version));
        this.id = id;
        events.forEach(this::apply);
    }

    /**
     * The constructor introduces the side effects - it should NOT be used
     * for the rehydration.
     */
    public Shipment(ShipmentId id, Party sender, Party receiver) {
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

        appendEvent(new ShipmentCreated(this));
    }

    public boolean isAllocated() {
        return this.status.afterOrEqual(ALLOCATED);
    }

    public void allocate(CarrierServiceId carrierServiceId) {
        if (status.after(ALLOCATED)) {
            throw new ShipmentStatusException(CANNOT_ALLOCATE_ALLOCATED,
                    "The shipment cannot be allocated as it's %s.".formatted(status));
        }
        status = ALLOCATED;
        allocationContext = new AllocationContext(carrierServiceId);
        appendEvent(new ShipmentAllocated(this));
    }

    public void reallocate(CarrierServiceId carrierServiceId) {
        if (!status.equals(ALLOCATED)) {
            throw new ShipmentStatusException(CANNOT_REALLOCATE_NOT_ALLOCATED,
                    "The shipment cannot be reallocated as it is not allocated.");
        }
        var previousThisState = this;
        allocationContext = new AllocationContext(carrierServiceId);
        appendEvent(new ShipmentReallocated(this, previousThisState));
    }

    public void deallocate() {
        if (status.afterOrEqual(MANIFESTED)) {
            throw new ShipmentStatusException(CANNOT_DEALLOCATE_MANIFESTED,
                    "The shipment cannot be deallocated as it has been already manifested.");
        }
        allocationContext = null;
        appendEvent(new ShipmentDeallocated(this));
    }

    @Override
    public ShipmentId id() {
        return id;
    }

    public Party sender() {
        return sender;
    }

    public Party receiver() {
        return receiver;
    }

    public Optional<AllocationContext> getAllocation() {
        return Optional.ofNullable(allocationContext);
    }

    private void apply(ShipmentEvent event) {
        event.accept(new ShipmentEventVisitor() {

            @Override
            public void visit(ShipmentCreated event) {
                applyShipmentCreated(event);
            }

            @Override
            public void visit(ShipmentAllocated event) {
                applyShipmentAllocated(event);
            }

            @Override
            public void visit(ShipmentReallocated event) {
                applyShipmentReallocated(event);
            }

            @Override
            public void visit(ShipmentDeallocated event) {
                applyShipmentDeallocated(event);
            }
        });
    }

    private void applyShipmentCreated(ShipmentCreated event) {

    }

    private void applyShipmentAllocated(ShipmentAllocated event) {

    }

    private void applyShipmentReallocated(ShipmentReallocated event) {

    }

    private void applyShipmentDeallocated(ShipmentDeallocated event) {

    }
}
