package com.enterprise.airport.flightmanagement.domain.ticket;

import com.enterprise.airport.common.types.base.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

public class TicketEvent {
    private TicketEvent() {
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static class Published extends DomainEvent {
        TicketId ticketId;
    }
}
