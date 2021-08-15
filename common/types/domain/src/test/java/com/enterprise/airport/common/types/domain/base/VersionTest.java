package com.enterprise.airport.common.types.domain.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VersionTest {
    @Test
    void incrementIncreaseVersionNumberOn1() {
        var firstVersion = Version.first();
        var secondVersion = firstVersion.increment();

        Assertions.assertEquals(2L, secondVersion.getValue());
    }

    @Test
    void createVersionFromLongScalar() {
        var version = Version.from(25L);
        Assertions.assertEquals(25L, version.getValue());
    }
}