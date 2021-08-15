package com.enterprise.airport.common.types.domain.base;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
public final class Version implements ValueObject {
    private final Long value;

    private Version(Long value) {
        this.value = value;
    }

    public static Version first() {
        return new Version(1L);
    }

    public static Version from(Long value) {
        return new Version(value);
    }

    public Version increment() {
        return new Version(value + 1);
    }

    public Long getValue() {
        return value;
    }
}
