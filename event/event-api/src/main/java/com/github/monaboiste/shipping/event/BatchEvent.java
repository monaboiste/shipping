package com.github.monaboiste.shipping.event;

import java.util.List;
import java.util.function.Consumer;

public record BatchEvent<P extends Payload>(List<DomainEvent<P>> events) {

    public void forEach(Consumer<DomainEvent<P>> action) {
        events.forEach(action);
    }

    public List<DomainEvent<P>> events() {
        return events;
    }
}
