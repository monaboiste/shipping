package com.github.monaboiste.shipping;

import com.github.monaboiste.shipping.event.Event;
import com.github.monaboiste.shipping.event.Payload;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("squid:S119")
public abstract class AggregateRoot<ID, P extends Payload> {

    private final List<Event<P>> pendingEvents = new ArrayList<>();
    private int version;

    protected void appendEvent(Event<P> event) {
        if (event == null) {
            throw new IllegalArgumentException();
        }
        pendingEvents.add(event);
    }

    public abstract ID id();

    /**
     * @return actual version of {@code this} aggregate to support optimistic locking mechanism
     * - leaked persistence concerns into domain model.
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
    public List<Event<P>> flushPendingEvents() {
        incrementVersion();
        var returned = new ArrayList<>(pendingEvents);
        pendingEvents.clear();
        return returned;
    }

    private void incrementVersion() {
        version++;
    }

    /**
     * Return a view of pending events.
     *
     * @return a collection of the pending events.
     */
    @UnmodifiableView
    public List<Event<P>> peekPendingEvents() {
        return List.copyOf(pendingEvents);
    }
}
