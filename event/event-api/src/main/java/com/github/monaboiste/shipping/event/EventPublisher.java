package com.github.monaboiste.shipping.event;

public interface EventPublisher<S extends Snapshot> {

    void publish(Event<S> events);

    void publish(BatchEvent<S> events);
}
