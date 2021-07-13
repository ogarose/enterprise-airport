package com.enterprise.airport.flightmanagement.domain.order;

import com.enterprise.airport.common.types.exception.DomainException;
import lombok.Value;

@Value
public class Email {
    String value;

    public Email(String value) {
        validateEmail(value);

        this.value = value;
    }

    private void validateEmail(String value) {
        if (value == null || !value.matches("(?i)^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")) {
            throw new WrongFormatException(String.format("Wrong email: %s", value));
        }
    }

    public static class WrongFormatException extends DomainException {
        public WrongFormatException(String message) {
            super(message);
        }
    }
}
