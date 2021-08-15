package com.enterprise.airport.flightmanagement.domain.flight;

import com.enterprise.airport.common.types.domain.base.ValueObject;
import lombok.Value;

@Value
public class FlightId implements ValueObject {
    Long value;
}
