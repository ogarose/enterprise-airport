package com.enterprise.airport.flightmanagement.usecase.order;

import com.enterprise.airport.flightmanagement.domain.order.Order;

public interface OrderPersister {
    void save(Order order);
}
