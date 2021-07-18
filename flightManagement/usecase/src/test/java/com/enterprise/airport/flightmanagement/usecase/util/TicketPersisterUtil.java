package com.enterprise.airport.flightmanagement.usecase.util;

import com.enterprise.airport.flightmanagement.domain.ticket.Ticket;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;
import com.enterprise.airport.flightmanagement.usecase.ticket.TicketExtractor;
import com.enterprise.airport.flightmanagement.usecase.ticket.TicketPersister;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TicketPersisterUtil implements TicketPersister, TicketExtractor {
    public Map<TicketId, Ticket> data = new HashMap<>();

    @Override
    public void save(Ticket ticket) {
        data.put(ticket.getId(), ticket);
    }

    @Override
    public Optional<Ticket> findById(TicketId ticketId) {
        return Optional.ofNullable(data.get(ticketId));
    }
}
