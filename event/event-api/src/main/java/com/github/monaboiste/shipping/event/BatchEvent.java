package com.github.monaboiste.shipping.event;

import java.util.List;
import java.util.function.Consumer;

public record BatchEvent<P extends Payload>(List<Event<P>> events) {

    public void forEach(Consumer<Event<P>> action) {
        events.forEach(action);
    }

    public List<Event<P>> events() {
        return events;
    }
}
