package com.enterprise.airport.flightmanagement.usecase.order;

import com.enterprise.airport.flightmanagement.domain.order.OrderId;
import com.enterprise.airport.flightmanagement.usecase.Fixtures;
import com.enterprise.airport.flightmanagement.usecase.fake.OrderPersisterFake;
import com.enterprise.airport.flightmanagement.usecase.fake.TicketPersisterFake;
import com.enterprise.airport.flightmanagement.usecase.rules.TicketAvailableImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

class CreateOrderUseCaseTest {

    private OrderPersisterFake orderPersisterFake;
    private TicketAvailableImpl ticketAvailableRule;
    private TicketPersisterFake ticketPersisterFake;

    @BeforeEach
    void beforeEachTest() {
        orderPersisterFake = new OrderPersisterFake();
        ticketAvailableRule = new TicketAvailableImpl(orderPersisterFake);
        ticketPersisterFake = new TicketPersisterFake();
    }

    @Test
    void createdOrder() {
        var tickets = Fixtures.generateListTicket(2);
        tickets.forEach(ticket -> ticketPersisterFake.save(ticket));

        var usecase = new CreateOrderUseCase(
                ticketAvailableRule,
                () -> new OrderId(759L),
                ticketPersisterFake,
                orderPersisterFake
        );

        var request = new CreateOrderRequest(
                Fixtures.generateRawPassengersMap(tickets),
                "custom@email.com"
        );

        var orderId = usecase.execute(request);

        Assertions.assertNotNull(orderId);
        Assertions.assertNotNull(orderPersisterFake.data.get(orderId));
    }

    @Test
    void createdOrderThrowsExceptionForNotExistedTicketId() {
        var tickets = Fixtures.generateListTicket(1);

        var usecase = new CreateOrderUseCase(
                ticketAvailableRule,
                () -> new OrderId(759L),
                ticketPersisterFake,
                orderPersisterFake
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