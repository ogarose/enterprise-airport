package com.enterprise.airport.flightmanagement.usecase.order;

import com.enterprise.airport.common.types.exception.DomainException;
import com.enterprise.airport.flightmanagement.domain.order.OrderId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PayOrderUseCase implements PayOrder {

    private final OrderExtractor orderExtractor;
    private final OrderPersister orderPersister;

    @Override
    public void execute(OrderId orderId) {
        var order = orderExtractor.getById(orderId).orElseThrow(() -> IllegalOrderIdException.ofOrderId(orderId));

        order.pay();

        orderPersister.save(order);
    }

    public static class IllegalOrderIdException extends DomainException {
        public IllegalOrderIdException(String message) {
            super(message);
        }

        public static IllegalOrderIdException ofOrderId(OrderId orderId) {
            return new IllegalOrderIdException(String.format("Wrong order id: %s", orderId.getValue()));
        }
    }
}
