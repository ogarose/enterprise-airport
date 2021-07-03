package com.enterprise.airport.maintenance.domain.flight;

import com.enterprise.airport.common.types.base.ValueObject;
import lombok.Value;

@Value
public class FlightId implements ValueObject {
    Long value;
}
