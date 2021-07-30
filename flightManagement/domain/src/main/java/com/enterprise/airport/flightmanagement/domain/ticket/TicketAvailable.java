package com.enterprise.airport.flightmanagement.domain.ticket;

@FunctionalInterface
public interface TicketAvailable {
    boolean check(Ticket ticket);
}
