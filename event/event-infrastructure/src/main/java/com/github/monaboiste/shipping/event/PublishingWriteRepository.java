package com.github.monaboiste.shipping.event;

import com.github.monaboiste.shipping.AggregateRoot;
import com.github.monaboiste.shipping.WriteRepository;

import java.util.List;

/**
 * This class delegates write operations to the underlying repository and subsequently publishes
 * the pending events. The events are published in a batch to optimize processes in downstream services.
 * Additionally, individual (non-batched) events are also published, allowing subscribers to listen
 * for specific events of interest.
 * <p>
 * If a subscriber is listening to both batched and single events, it is important to avoid duplication
 * in event processing. To prevent duplication, a consumer should handle either batched or single events,
 * but not both.
 * <p>
 * Use Spring's {@code org.springframework.transaction.event.TransactionalEventListener#phase}
 * or manual transaction management to control when events are handled: either within the same transaction
 * (e.g., {@code BEFORE_COMMIT}) or outside of it. Avoid mixing database operations with external TCP calls
 * within a single transaction.
 */
@SuppressWarnings("squid:S119")
public class PublishingWriteRepository<
        ID,
        T extends AggregateRoot<ID, P>,
        P extends Payload
        > implements WriteRepository<ID, T> {

    private final EventPublisher<P> eventPublisher;
    private final WriteRepository<ID, T> delegate;

    public PublishingWriteRepository(EventPublisher<P> eventPublisher,
                                     WriteRepository<ID, T> delegate) {
        this.eventPublisher = eventPublisher;
        this.delegate = delegate;
    }

    @Override
    public void save(T t) {
        var events = t.flushPendingEvents();
        delegate.save(t);

        publish(events);
    }

    /**
     * Publishes a batch of events followed by publishing each event individually.
     *
     * @param events events to publish
     */
    private void publish(List<Event<P>> events) {
        eventPublisher.publish(new BatchEvent<>(events));
        events.forEach(eventPublisher::publish);
    }
}
