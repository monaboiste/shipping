package com.github.monaboiste.shipping.event;

public interface EventPublisher {

    <E extends DomainEvent<? extends Payload>> void publish(E event);

    <E extends DomainEvent<? extends Payload>> void publish(BatchEvent<E> events);
}
