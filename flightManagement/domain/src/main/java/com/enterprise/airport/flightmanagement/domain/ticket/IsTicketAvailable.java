package com.enterprise.airport.flightmanagement.domain.ticket;

@FunctionalInterface
public interface IsTicketAvailable {
    boolean check(Ticket ticket);
}
