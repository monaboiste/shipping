package com.github.monaboiste.shipping.event;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaEventSerializer implements EventSerializer {

    @Override
    public byte[] serialize(DomainEvent<?> event) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(event);
            oos.flush();
            return baos.toByteArray();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Cannot serialize event %s".formatted(event.eventId()), ex);
        }
    }

    @Override
    public <P extends Payload> DomainEvent<P> deserialize(byte[] content, Class<P> eventType) {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(content))) {
            @SuppressWarnings("unchecked")
            var event = (DomainEvent<P>) ois.readObject();
            return event;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot deserialize event", ex);
        }
    }
}
