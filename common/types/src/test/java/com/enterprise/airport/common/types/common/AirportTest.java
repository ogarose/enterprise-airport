package com.enterprise.airport.common.types.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AirportTest {
    @Test
    void testEnum() {
        Assertions.assertNotNull(Airport.TN);
    }
}