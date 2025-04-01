package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.AggregateRoot;
import com.github.monaboiste.shipping.error.CannotBeEmptyException;
import com.github.monaboiste.shipping.shipment.event.ShipmentAllocated;
import com.github.monaboiste.shipping.shipment.event.ShipmentCreated;
import com.github.monaboiste.shipping.shipment.event.ShipmentEvent;
import com.github.monaboiste.shipping.shipment.event.ShipmentEventVisitor;

import java.util.List;
import java.util.Optional;

import static com.github.monaboiste.shipping.shipment.ShipmentStatus.ALLOCATED;
import static com.github.monaboiste.shipping.shipment.ShipmentStatus.PENDING;
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
    static Shipment recreate(ShipmentId shipmentId, int version, List<ShipmentEvent> events) {
        return new Shipment(shipmentId, version, events);
    }

    private Shipment(ShipmentId id, int version, List<ShipmentEvent> events) {
        super(version);
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

        appendIncoming(new ShipmentCreated(this));
    }

    public void allocate(CarrierServiceId carrierServiceId) {
        status = ALLOCATED;
        allocationContext = new AllocationContext(carrierServiceId);
        appendIncoming(new ShipmentAllocated(this));
        appendOutgoing(new ShipmentAllocated(this));
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
                apply(Shipment.this, event);
            }

            @Override
            public void visit(ShipmentAllocated event) {
                apply(Shipment.this, event);
            }
        });
    }

    private static void apply(Shipment shipment, ShipmentCreated event) {

    }

    private static void apply(Shipment shipment, ShipmentAllocated event) {

    }
}
