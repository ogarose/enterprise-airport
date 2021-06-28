package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.base.ValueObject;
import com.enterprise.airport.common.types.exception.DomainException;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = false)
@Value
public class AircraftLicenseNumber extends ValueObject {
    String value;

    public AircraftLicenseNumber(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new EmptyException();
        }

        if (!value.matches("\\d{3}\\s\\d\\s\\w{2}")) {
            throw new WrongFormatException();
        }
    }

    static class EmptyException extends DomainException {
    }

    static class WrongFormatException extends DomainException {
    }
}
