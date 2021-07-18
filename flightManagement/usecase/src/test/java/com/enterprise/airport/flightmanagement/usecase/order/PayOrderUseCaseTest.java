package com.enterprise.airport.flightmanagement.usecase.order;

import com.enterprise.airport.flightmanagement.domain.order.OrderId;
import com.enterprise.airport.flightmanagement.domain.order.OrderStatus;
import com.enterprise.airport.flightmanagement.usecase.Fixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PayOrderUseCaseTest {

    @Mock
    private OrderExtractor orderExtractor;
    @Mock
    private OrderPersister orderPersister;
    @InjectMocks
    private PayOrderUseCase usecase;

    @Test
    void payOrder() {
        var order = Fixtures.createdOrder();

        Mockito.when(orderExtractor.getById(order.getId())).thenReturn(Optional.of(order));

        usecase.execute(order.getId());

        Mockito.verify(orderPersister, Mockito.times(1)).save(order);

        Assertions.assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void throwsExceptionForNotExistedOrder() {
        Assertions.assertThrows(PayOrderUseCase.IllegalOrderIdException.class, () ->
                usecase.execute(new OrderId(5L))
        );
    }
}