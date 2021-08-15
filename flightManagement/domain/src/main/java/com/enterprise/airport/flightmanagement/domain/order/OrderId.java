package com.enterprise.airport.flightmanagement.domain.order;

import com.enterprise.airport.common.types.domain.base.ValueObject;
import lombok.Value;

@Value
public class OrderId implements ValueObject {
    Long value;
}
