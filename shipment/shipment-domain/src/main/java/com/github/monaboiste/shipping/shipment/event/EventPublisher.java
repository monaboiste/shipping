package com.github.monaboiste.shipping.shipment.event;

import com.github.monaboiste.shipping.shipment.BatchEvent;

public interface EventPublisher<S extends Snapshot> {

    void publish(Event<S> events);

    void publish(BatchEvent<S> events);
}
