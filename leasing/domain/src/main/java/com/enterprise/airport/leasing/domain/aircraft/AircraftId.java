package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.base.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = false)
@Value
public class AircraftId extends ValueObject {
    Long value;
}