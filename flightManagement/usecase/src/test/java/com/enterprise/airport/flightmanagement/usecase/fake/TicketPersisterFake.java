package com.enterprise.airport.flightmanagement.usecase.fake;

import com.enterprise.airport.flightmanagement.domain.ticket.Ticket;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;
import com.enterprise.airport.flightmanagement.usecase.ticket.TicketExtractor;
import com.enterprise.airport.flightmanagement.usecase.ticket.TicketPersister;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketPersisterFake implements TicketPersister, TicketExtractor {
    public Map<TicketId, Ticket> data = new HashMap<>();

    @Override
    public void save(Ticket ticket) {
        data.put(ticket.getId(), ticket);
    }

    @Override
    public Optional<Ticket> findById(TicketId ticketId) {
        return Optional.ofNullable(data.get(ticketId));
    }

    @Override
    public List<Ticket> findAllById(List<TicketId> ticketIds) {
        var foundResult = data.entrySet().stream()
                .filter(entry -> ticketIds.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        if (foundResult.size() != ticketIds.size()) {
            throw new IllegalArgumentException();
        }

        return foundResult;
    }
}
