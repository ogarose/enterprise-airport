package com.enterprise.airport.flightmanagement.domain.aircraft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SeatsCounterTest {

    @Test
    void testPositiveCase() {
        long expectedValue = 54L;
        var seatsCounter = new SeatsCounter(expectedValue);

        Assertions.assertEquals(expectedValue, seatsCounter.getValue());
    }

    @Test
    void testZeroCase() {
        Assertions.assertThrows(SeatsCounter.Min1SeatsException.class, () -> {
            new SeatsCounter(0L);
        });
    }

    @Test
    void testNegativeCase() {
        Assertions.assertThrows(SeatsCounter.Min1SeatsException.class, () -> {
            new SeatsCounter(-25L);
        });
    }
}