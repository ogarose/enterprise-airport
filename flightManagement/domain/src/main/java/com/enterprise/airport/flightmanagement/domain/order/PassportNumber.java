package com.enterprise.airport.flightmanagement.domain.order;

import com.enterprise.airport.common.types.base.ValueObject;
import com.enterprise.airport.common.types.exception.DomainException;
import lombok.Value;

@Value
public class PassportNumber implements ValueObject {
    String value;

    public PassportNumber(String value) {
        validate(value);

        this.value = value;
    }

    private void validate(String value) {
        if (value == null || !value.matches("^[A-Z]{2}\\d{7}$")) {
            throw new WrongPassportNumberFormatException(String.format("Wrong number: %s", value));
        }
    }

    public static class WrongPassportNumberFormatException extends DomainException {
        public WrongPassportNumberFormatException(String message) {
            super(message);
        }
    }
}
