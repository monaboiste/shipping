package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.shipment.error.CannotBeEmptyException;
import com.github.monaboiste.shipping.shipment.error.DomainException;
import com.github.monaboiste.shipping.shipment.event.DomainEvent;
import com.github.monaboiste.shipping.shipment.event.Event;
import com.github.monaboiste.shipping.shipment.event.ShipmentAllocated;
import com.github.monaboiste.shipping.shipment.event.ShipmentReallocated;
import com.github.monaboiste.shipping.shipment.event.ShipmentSnapshot;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.monaboiste.shipping.shipment.ShipmentStatus.ALLOCATED;
import static com.github.monaboiste.shipping.shipment.ShipmentStatus.PENDING;

public class Shipment {
    private final List<Event<ShipmentSnapshot>> pendingEvents = new ArrayList<>();
    private int version;

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
            throw new DomainException("The shipment cannot be allocated as it's %s.".formatted(status));
        }
        status = ALLOCATED;
        allocationContext = new AllocationContext(carrierServiceId);
        appendEvent(new ShipmentAllocated(this));
    }

    public void reallocate(CarrierServiceId carrierServiceId) {
        if (!status.equals(ALLOCATED)) {
            throw new DomainException("The shipment cannot be reallocated as it is not allocated.");
        }
        allocationContext = new AllocationContext(carrierServiceId);
        appendEvent(new ShipmentReallocated(this));
    }

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

    /**
     * Version field to support optimistic locking mechanism
     * - leaked persistence concerns into domain model.
     * TODO: switch to private-package
     */
    public String version() {
        return String.valueOf(version);
    }

    /**
     * Increments {@code this} aggregate's version and flushes pending events
     * effectively marking all uncommited changes as commited.
     * <p>
     * <b>
     * Each transaction, which mutates {@code this} state should invoke
     * {@code flushPendingEvents} method before commiting the changes.
     * </b>
     *
     * @return a collection of the pending events (commited changes).
     */
    public List<Event<ShipmentSnapshot>> flushPendingEvents() {
        incrementVersion();
        var returned = new ArrayList<>(pendingEvents);
        pendingEvents.clear();
        return returned;
    }

    /**
     * Return a view of pending events.
     *
     * @return a collection of the pending events.
     */
    @UnmodifiableView
    public List<Event<ShipmentSnapshot>> peekPendingEvents() {
        return List.copyOf(pendingEvents);
    }

    private void appendEvent(DomainEvent<ShipmentSnapshot> event) {
        if (event == null) {
            throw new IllegalArgumentException();
        }
        pendingEvents.add(event);
    }

    private void incrementVersion() {
        version++;
    }
}
