package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.domain.base.ValueObject;
import com.enterprise.airport.common.types.domain.exception.DomainException;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = false)
@Value
public class AircraftContractNumber implements ValueObject {
    String value;

    public AircraftContractNumber(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new EmptyException();
        }
    }

    static class EmptyException extends DomainException {

    }
}
