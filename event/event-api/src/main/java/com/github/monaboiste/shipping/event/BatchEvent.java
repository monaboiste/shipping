package com.github.monaboiste.shipping.event;

import java.util.List;
import java.util.function.Consumer;

public record BatchEvent<E extends Event<? extends Payload>>(List<E> events) {

    public void forEach(Consumer<E> action) {
        events.forEach(action);
    }

    public List<E> events() {
        return events;
    }
}
