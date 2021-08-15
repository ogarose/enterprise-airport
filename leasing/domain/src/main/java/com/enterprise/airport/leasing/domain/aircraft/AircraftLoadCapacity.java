package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.domain.base.ValueObject;
import com.enterprise.airport.common.types.domain.exception.DomainException;
import lombok.Value;

@Value
public class AircraftLoadCapacity implements ValueObject {
    Double value;

    public AircraftLoadCapacity(Double value) {
        validate(value);
        this.value = value;
    }

    private void validate(Double value) {
        if (value == null) {
            throw new AircraftLoadCapacity.EmptyException();
        }
    }


    static class EmptyException extends DomainException {
    }
}
