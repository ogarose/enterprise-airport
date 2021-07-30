package com.enterprise.airport.flightmanagement.usecase.ticket;

import com.enterprise.airport.flightmanagement.domain.ticket.Ticket;

public interface TicketPersister {
    void save(Ticket ticket);
}
