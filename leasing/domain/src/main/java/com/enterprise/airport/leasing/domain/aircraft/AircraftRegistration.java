package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.domain.base.ValueObject;
import com.enterprise.airport.common.types.domain.exception.DomainException;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = false)
@Value
public class AircraftRegistration implements ValueObject {
    String value;

    public AircraftRegistration(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new EmptyException();
        }

        if (!value.matches("^[A-Z]-[A-Z]{4}|[A-Z]{2}-[A-Z]{3}|N[0-9]{1,5}[A-Z]{0,2}$")) {
            throw new WrongFormatException(String.format("wrong value %s", value));
        }
    }

    static class EmptyException extends DomainException {
        public EmptyException() {
            super();
        }
    }

    static class WrongFormatException extends DomainException {
        public WrongFormatException(String message) {
            super(message);
        }
    }
}
