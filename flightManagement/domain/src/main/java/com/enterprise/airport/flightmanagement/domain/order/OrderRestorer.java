package com.enterprise.airport.flightmanagement.domain.order;

import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;

import java.util.Map;

public class OrderRestorer {
    private OrderRestorer() {
    }

    public static Order restore(
            OrderId id,
            Version first,
            Map<TicketId, Passenger> passengers,
            Email customerEmail,
            OrderStatus status
    ) {
        return new Order(id, first, passengers, customerEmail, status);
    }
}
