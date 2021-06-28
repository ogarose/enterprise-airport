package com.enterprise.airport.common.types.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DomainEventTest {
    @Test
    void eventHasItsOwnIdAndCreateTime() {
        var event = new DomainEvent();
        Assertions.assertNotNull(event.getId());
        Assertions.assertNotNull(event.getCreatedAt());
    }
}
