package com.github.monaboiste.shipping.event;

import java.io.Serializable;
import java.time.Instant;

/**
 * Represents internal (pure) domain event.
 */
public interface DomainEvent<P extends Payload> extends Serializable {

    /**
     * @return event identifier
     */
    String eventId();

    /**
     * @return the name of the event
     */
    String name();

    /**
     * @return the precise moment we first observed the event
     */
    Instant occurredAt();

    /**
     * @return a serializable payload
     */
    P payload();

    /**
     * @return aggregate identifier
     */
    String aggregateId();

    /**
     * @return the class of this event
     */
    Class<? extends DomainEvent<P>> type();
}
