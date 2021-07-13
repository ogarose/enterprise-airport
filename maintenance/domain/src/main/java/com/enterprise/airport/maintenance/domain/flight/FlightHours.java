package com.enterprise.airport.maintenance.domain.flight;

import com.enterprise.airport.common.types.base.ValueObject;
import com.enterprise.airport.common.types.exception.DomainException;
import lombok.Value;

@Value
public class FlightHours implements ValueObject {
    Long value;

    public FlightHours(Long value) {
        validate(value);

        this.value = value;
    }

    private void validate(Long value) {
        if (value == null || value < 0) {
            throw new WrongFlightHoursException(String.format("Income value: %d", value));
        }
    }

    public static class WrongFlightHoursException extends DomainException {
        public WrongFlightHoursException(String message) {
            super(message);
        }
    }
}
