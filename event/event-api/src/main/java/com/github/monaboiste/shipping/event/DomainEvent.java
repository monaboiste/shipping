package com.github.monaboiste.shipping.event;

/**
 * Represents internal (pure) domain event.
 */
public interface DomainEvent<S extends Snapshot> extends Event<S> {

    String aggregateId();

    /**
     * @return the class of this event
     */
    Class<? extends Event<S>> type();
}
