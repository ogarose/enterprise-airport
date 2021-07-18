package com.enterprise.airport.flightmanagement.usecase.rules;

import com.enterprise.airport.flightmanagement.usecase.Fixtures;
import com.enterprise.airport.flightmanagement.usecase.util.OrderPersisterUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketAvailableImplTest {

    private OrderPersisterUtil orderPersisterUtil;
    private TicketAvailableImpl ticketAvailableRule;

    @BeforeEach
    void beforeEachTest() {
        orderPersisterUtil = new OrderPersisterUtil();
        ticketAvailableRule = new TicketAvailableImpl(orderPersisterUtil);
    }

    @Test
    void ticketIsNotAvailableWhenThereIsOrderWithRequestedTicket() {
        var requestedTicket = Fixtures.generateListTicket(1).get(0);

        var order = Fixtures.createdOrderWithTicketId(requestedTicket.getId());
        orderPersisterUtil.save(order);

        Assertions.assertFalse(ticketAvailableRule.check(requestedTicket));
    }

    @Test
    void ticketIsAvailableWhenNoOrderWithRequestedTicket() {
        var requestedTicket = Fixtures.generateListTicket(1).get(0);

        Assertions.assertTrue(ticketAvailableRule.check(requestedTicket));
    }
}