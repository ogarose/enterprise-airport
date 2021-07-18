package com.enterprise.airport.flightmanagement.usecase.util;

import com.enterprise.airport.flightmanagement.domain.order.Order;
import com.enterprise.airport.flightmanagement.domain.order.OrderId;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;
import com.enterprise.airport.flightmanagement.usecase.order.OrderExtractor;
import com.enterprise.airport.flightmanagement.usecase.order.OrderPersister;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderPersisterUtil implements OrderExtractor, OrderPersister {
    public Map<OrderId, Order> data = new HashMap<>();

    @Override
    public Optional<Order> getById(OrderId orderId) {
        return Optional.ofNullable(data.get(orderId));
    }

    @Override
    public Optional<Order> getByTicketId(TicketId ticketId) {
        return data.values().stream()
                .filter(order ->
                        order.getOrderItems()
                                .stream()
                                .anyMatch(orderItem -> orderItem.getBookedTicketId().equals(ticketId))
                )
                .findFirst();
    }

    @Override
    public void save(Order order) {
        data.put(order.getId(), order);
    }
}
