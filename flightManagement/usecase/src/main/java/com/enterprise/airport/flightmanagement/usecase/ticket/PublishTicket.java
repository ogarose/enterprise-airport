package com.enterprise.airport.flightmanagement.usecase.ticket;

import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;

public interface PublishTicket {
    TicketId execute(PublishTicketRequest request);
}
