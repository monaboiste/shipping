package com.github.monaboiste.shipping.event;

public interface EventSerializer {

    byte[] serialize(DomainEvent<?> event);

    <P extends Payload> DomainEvent<P> deserialize(byte[] content, Class<P> eventType);
}
