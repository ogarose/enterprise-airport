package com.enterprise.airport.flightmanagement.usecase.ticket;

import com.enterprise.airport.flightmanagement.domain.ticket.Ticket;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;

import java.util.List;
import java.util.Optional;

public interface TicketExtractor {
    Optional<Ticket> findById(TicketId ticketId);

    List<Ticket> findAllById(List<TicketId> ticketIds);
}
