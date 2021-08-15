package com.enterprise.airport.flightmanagement.domain.aircraft;

import com.enterprise.airport.common.types.domain.base.ValueObject;
import lombok.Value;

@Value
public class AircraftId implements ValueObject {
    Long value;
}