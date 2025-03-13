package com.github.monaboiste.shipping.event;

public interface EventPublisher<P extends Payload> {

    void publish(Event<P> events);

    void publish(BatchEvent<P> events);
}
