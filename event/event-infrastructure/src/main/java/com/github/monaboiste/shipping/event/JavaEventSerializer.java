package com.github.monaboiste.shipping.event;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Built-in Java based serializer.
 * <p>
 * Cons:
 * <ul>
 * <li>very slow - for better performance consider using different method of serialization, e.g. Protobuf
 * <li>large binary output (writes metadata)
 * <li>not cross-platform
 * <li>requires objects to implement {@link java.io.Serializable}
 * </ul>
 */
public class JavaEventSerializer implements EventSerializer {

    @Override
    public <E extends Event<?>> byte[] serialize(E event) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(event);
            oos.flush();
            return baos.toByteArray();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Cannot serialize %s event %s"
                    .formatted(event.getClass().getSimpleName(), event.eventId()), ex);
        }
    }

    @Override
    public <E extends Event<?>> E deserialize(byte[] content, Class<E> eventType) {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(content))) {
            return eventType.cast(ois.readObject());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot deserialize %s event"
                    .formatted(eventType.getSimpleName()), ex);
        }
    }
}
