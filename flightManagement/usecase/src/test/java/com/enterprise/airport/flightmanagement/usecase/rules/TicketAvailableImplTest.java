package com.enterprise.airport.flightmanagement.usecase.rules;

import com.enterprise.airport.flightmanagement.usecase.Fixtures;
import com.enterprise.airport.flightmanagement.usecase.fake.OrderPersisterFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketAvailableImplTest {

    private OrderPersisterFake orderPersisterFake;
    private TicketAvailableImpl ticketAvailableRule;

    @BeforeEach
    void beforeEachTest() {
        orderPersisterFake = new OrderPersisterFake();
        ticketAvailableRule = new TicketAvailableImpl(orderPersisterFake);
    }

    @Test
    void ticketIsNotAvailableWhenThereIsOrderWithRequestedTicket() {
        var requestedTicket = Fixtures.generateListTicket(1).get(0);

        var order = Fixtures.createdOrderWithTicketId(requestedTicket.getId());
        orderPersisterFake.save(order);

        Assertions.assertFalse(ticketAvailableRule.check(requestedTicket));
    }

    @Test
    void ticketIsAvailableWhenNoOrderWithRequestedTicket() {
        var requestedTicket = Fixtures.generateListTicket(1).get(0);

        Assertions.assertTrue(ticketAvailableRule.check(requestedTicket));
    }
}