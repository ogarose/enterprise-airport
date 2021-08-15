package com.enterprise.airport.flightmanagement.domain.ticket;

import com.enterprise.airport.common.types.domain.base.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

@SuppressWarnings("PMD.MissingStaticMethodInNonInstantiatableClass")
public class TicketEvent {
    private TicketEvent() {
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static class Published extends DomainEvent {
        TicketId ticketId;
    }
}
