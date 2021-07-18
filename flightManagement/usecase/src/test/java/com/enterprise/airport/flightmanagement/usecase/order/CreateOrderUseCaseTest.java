package com.enterprise.airport.flightmanagement.usecase.order;

import com.enterprise.airport.flightmanagement.domain.order.OrderId;
import com.enterprise.airport.flightmanagement.usecase.Fixtures;
import com.enterprise.airport.flightmanagement.usecase.rules.TicketAvailableImpl;
import com.enterprise.airport.flightmanagement.usecase.util.OrderPersisterUtil;
import com.enterprise.airport.flightmanagement.usecase.util.TicketPersisterUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

class CreateOrderUseCaseTest {

    private OrderPersisterUtil orderPersisterUtil;
    private TicketAvailableImpl ticketAvailableRule;
    private TicketPersisterUtil ticketPersisterUtil;

    @BeforeEach
    void beforeEachTest() {
        orderPersisterUtil = new OrderPersisterUtil();
        ticketAvailableRule = new TicketAvailableImpl(orderPersisterUtil);
        ticketPersisterUtil = new TicketPersisterUtil();
    }

    @Test
    void createdOrder() {
        var tickets = Fixtures.generateListTicket(2);
        tickets.forEach(ticket -> ticketPersisterUtil.save(ticket));

        var usecase = new CreateOrderUseCase(
                ticketAvailableRule,
                () -> new OrderId(759L),
                ticketPersisterUtil,
                orderPersisterUtil
        );

        var request = new CreateOrderRequest(
                Map.of(
                        tickets.get(0).getId().getValue(),
                        new CreateOrderRequest.PassengerDto("fname", "lname", null, "SP6548778"),
                        tickets.get(1).getId().getValue(),
                        new CreateOrderRequest.PassengerDto("fname2", "lname2", "mname2", "GD4548778")
                ),
                "custom@email.com"
        );

        var orderId = usecase.execute(request);

        Assertions.assertNotNull(orderId);
        Assertions.assertNotNull(orderPersisterUtil.data.get(orderId));
    }

    @Test
    void createdOrderThrowsExceptionForNotExistedTicketId() {
        var tickets = Fixtures.generateListTicket(1);

        var usecase = new CreateOrderUseCase(
                ticketAvailableRule,
                () -> new OrderId(759L),
                ticketPersisterUtil,
                orderPersisterUtil
        );

        var request = new CreateOrderRequest(
                Map.of(
                        tickets.get(0).getId().getValue(),
                        new CreateOrderRequest.PassengerDto("fname", "lname", null, "SP6548778")
                ),
                "custom@email.com"
        );

        Assertions.assertThrows(CreateOrderUseCase.IllegalTicketIdException.class, () -> usecase.execute(request));
    }


}