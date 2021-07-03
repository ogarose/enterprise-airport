package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.base.ValueObject;
import lombok.Value;

import java.util.List;

@Value
public class AircraftSeats implements ValueObject {
    List<String> value;
}
