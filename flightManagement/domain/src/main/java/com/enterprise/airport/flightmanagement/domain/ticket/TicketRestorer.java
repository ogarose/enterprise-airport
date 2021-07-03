package com.enterprise.airport.flightmanagement.domain.ticket;

import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.flightmanagement.domain.flight.FlightId;

public final class TicketRestorer {
    private TicketRestorer() {
    }

    public static Ticket restore(
            TicketId id,
            Version first,
            FlightId flightId,
            Price price
    ) {
        return new Ticket(id, first, flightId, price);
    }
}
