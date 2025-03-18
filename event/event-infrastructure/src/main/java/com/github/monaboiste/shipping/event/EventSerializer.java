package com.github.monaboiste.shipping.event;

public interface EventSerializer {

    <E extends DomainEvent<?>> byte[] serialize(E event);

    <E extends DomainEvent<?>> E deserialize(byte[] content, Class<E> eventType);
}
