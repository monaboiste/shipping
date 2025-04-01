package com.github.monaboiste.shipping;

import com.github.monaboiste.shipping.event.Event;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("squid:S119")
public abstract class AggregateRoot<ID, E extends Event<?>> {

    private final List<E> pendingIncomingEvents;
    private final List<E> pendingOutgoingEvents;
    private int version;

    protected AggregateRoot() {
        this.pendingIncomingEvents = new ArrayList<>();
        this.pendingOutgoingEvents = new ArrayList<>();
        this.version = 0;
    }

    protected AggregateRoot(int version) {
        this.pendingIncomingEvents = new ArrayList<>();
        this.pendingOutgoingEvents = new ArrayList<>();
        this.version = version;
    }

    protected void appendIncoming(E event) {
        if (event == null) {
            throw new IllegalArgumentException();
        }
        pendingIncomingEvents.add(event);
        incrementVersion();
    }

    protected void appendOutgoing(E event) {
        if (event == null) {
            throw new IllegalArgumentException();
        }
        pendingOutgoingEvents.add(event);
    }

    public abstract ID id();

    /**
     * @return actual version (sequence number) of {@code this} aggregate to support optimistic locking mechanism
     * - leaked persistence concerns into domain model.
     */
    public int version() {
        return version;
    }

    /**
     * Flushes pending incoming events effectively marking all uncommited changes as commited.
     * <p>
     * <b>
     * Each transaction, which mutates {@code this} state should invoke
     * {@code flushPendingEvents} method before commiting the changes.
     * </b>
     *
     * @return a collection of the pending events (commited changes).
     */
    public List<E> flushPendingIncomingEvents() {
        var returned = new ArrayList<>(pendingIncomingEvents);
        pendingIncomingEvents.clear();
        return returned;
    }

    /**
     * Flushes pending outgoing events.
     *
     * @return a collection of outgoing events.
     */
    public List<E> flushPendingOutgoingEvents() {
        var returned = new ArrayList<>(pendingOutgoingEvents);
        pendingOutgoingEvents.clear();
        return returned;
    }

    private void incrementVersion() {
        version++;
    }
}
