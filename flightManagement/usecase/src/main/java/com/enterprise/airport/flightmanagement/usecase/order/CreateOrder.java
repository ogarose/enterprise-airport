package com.enterprise.airport.flightmanagement.usecase.order;

import com.enterprise.airport.flightmanagement.domain.order.OrderId;

public interface CreateOrder {
    OrderId execute(CreateOrderRequest request);
}
