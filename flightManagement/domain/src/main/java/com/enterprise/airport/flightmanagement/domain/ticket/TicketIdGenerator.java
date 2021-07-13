package com.enterprise.airport.flightmanagement.domain.ticket;

@FunctionalInterface
public interface TicketIdGenerator {
    TicketId generate();
}
