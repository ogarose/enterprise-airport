package com.enterprise.airport.flightmanagement.domain.order;

import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;
import lombok.Value;

@Value
public class OrderItem {
    TicketId bookedTicketId;
    Passenger passenger;
}
