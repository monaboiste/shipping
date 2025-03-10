package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.shipment.event.Event;
import com.github.monaboiste.shipping.shipment.event.Snapshot;

import java.util.List;
import java.util.function.Consumer;

public record BatchEvent<S extends Snapshot>(List<Event<S>> events) {

    public void forEach(Consumer<Event<S>> action) {
        events.forEach(action);
    }

    public List<Event<S>> events() {
        return events;
    }
}
