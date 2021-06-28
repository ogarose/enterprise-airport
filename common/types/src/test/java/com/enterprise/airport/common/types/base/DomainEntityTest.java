package com.enterprise.airport.common.types.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class DomainEntityTest {
    @Test
    void entityResetEventsOnEventPop() {
        var entity = new TestDomainEntity(UUID.randomUUID(), Version.first());
        entity.performAction();

        var events = entity.popEvents();
        Assertions.assertEquals(1, events.size());

        Assertions.assertEquals(0, entity.popEvents().size());
    }

    @Test
    void entityWithSuppressedEventsDoesNotAddNewEvents() {
        var entity = new TestDomainEntity(UUID.randomUUID(), Version.first());
        entity.suppressEvent();

        entity.performAction();

        Assertions.assertEquals(0, entity.popEvents().size());
    }

    @Test
    void unsuppressedEventsAddsNewEvents() {
        var entity = new TestDomainEntity(UUID.randomUUID(), Version.first());
        entity.suppressEvent();

        entity.performAction();
        Assertions.assertEquals(0, entity.popEvents().size());

        entity.unSuppressedEvent();

        entity.performAction();
        Assertions.assertEquals(1, entity.popEvents().size());
    }


    private static class TestDomainEntity extends DomainEntity<UUID> {
        public TestDomainEntity(UUID id, Version first) {
            super(id, first);
        }

        public void performAction() {
            addEvent(new DomainEvent());
        }
    }

}