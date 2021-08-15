package com.enterprise.airport.flightmanagement.domain.ticket;

import com.enterprise.airport.common.types.domain.base.ValueObject;
import lombok.Value;

@Value
public class TicketId implements ValueObject {
    Long value;
}
