package com.github.monaboiste.shipping.event;

/**
 * Represents internal (pure) domain event.
 */
public interface DomainEvent<P extends Payload> extends Event<P> {

    String aggregateId();

    /**
     * @return the class of this event
     */
    Class<? extends Event<P>> type();
}
