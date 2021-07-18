package com.enterprise.airport.flightmanagement.usecase.rules;

import com.enterprise.airport.flightmanagement.domain.ticket.Ticket;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketAvailable;
import com.enterprise.airport.flightmanagement.usecase.order.OrderExtractor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TicketAvailableImpl implements TicketAvailable {
    private final OrderExtractor orderExtractor;

    @Override
    public boolean check(Ticket ticket) {
        return orderExtractor.getByTicketId(ticket.getId()).isEmpty();
    }
}
