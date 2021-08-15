package com.enterprise.airport.flightmanagement.domain.aircraft;

import com.enterprise.airport.common.types.domain.exception.DomainException;
import lombok.Value;

@Value
public class SeatsCounter {
    Long value;

    public SeatsCounter(Long value) {
        validate(value);

        this.value = value;
    }

    private void validate(Long value) {
        if (value < 1) {
            throw new Min1SeatsException();
        }
    }

    public static class Min1SeatsException extends DomainException {
    }
}
