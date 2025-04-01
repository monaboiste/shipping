package com.github.monaboiste.shipping.event;

public interface EventSerializer {

    <E extends Event<?>> byte[] serialize(E event);

    <E extends Event<?>> E deserialize(byte[] content, Class<E> eventType);
}
