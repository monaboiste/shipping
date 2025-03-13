package com.github.monaboiste.shipping.event;

import java.io.Serializable;
import java.time.Instant;

public interface Event<S extends Payload> extends Serializable {

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
    S payload();
}
