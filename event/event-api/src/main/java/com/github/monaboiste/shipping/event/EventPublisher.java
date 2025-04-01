package com.github.monaboiste.shipping.event;

public interface EventPublisher {

    <E extends Event<? extends Payload>> void publish(E event);

    <E extends Event<? extends Payload>> void publish(BatchEvent<E> events);
}
