package com.enterprise.airport.flightmanagement.usecase.order;

import com.enterprise.airport.flightmanagement.domain.order.Order;
import com.enterprise.airport.flightmanagement.domain.order.OrderId;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;

import java.util.Optional;

public interface OrderExtractor {
    Optional<Order> getById(OrderId orderId);

    Optional<Order> getByTicketId(TicketId ticketId);
}
