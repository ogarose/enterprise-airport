package com.enterprise.airport.flightmanagement.domain.order;

import com.enterprise.airport.common.types.domain.base.Version;

import java.util.List;

public final class OrderRestorer {
    private OrderRestorer() {
    }

    public static Order restore(
            OrderId id,
            Version first,
            List<OrderItem> orderItems,
            Email customerEmail,
            OrderStatus status
    ) {
        return new Order(id, first, orderItems, customerEmail, status);
    }
}
