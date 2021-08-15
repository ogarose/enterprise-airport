package com.enterprise.airport.common.types.domain.base;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DomainEntity<T> {
    @Getter
    protected T id;
    @Getter
    protected Version version;
    protected List<DomainEvent> domainEvents = new ArrayList<>();
    private boolean eventSupressed;

    public DomainEntity(T id, Version first) {
        this.id = id;
        version = first;
    }

    public List<DomainEvent> popEvents() {
        List<DomainEvent> returnEvents = domainEvents;
        domainEvents = new ArrayList<>();
        return returnEvents;
    }

    protected void addEvent(DomainEvent event) {
        if (eventSupressed) {
            return;
        }
        this.domainEvents.add(event);
    }

    protected void suppressEvent() {
        eventSupressed = true;
    }

    protected void unSuppressedEvent() {
        eventSupressed = false;
    }


}
