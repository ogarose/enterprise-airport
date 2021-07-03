package com.enterprise.airport.flightmanagement.domain.order;

import com.enterprise.airport.common.types.base.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

public class OrderEvent {
    private OrderEvent() {
    }

    @EqualsAndHashCode(callSuper = true)
    @Value
    public static class OrderCreated extends DomainEvent {
        OrderId orderId;
    }

    @EqualsAndHashCode(callSuper = true)
    @Value
    public static class OrderPaid extends DomainEvent {
        OrderId orderId;
    }
}
