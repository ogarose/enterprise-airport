package com.enterprise.airport.flightmanagement.usecase.ticket;

import com.enterprise.airport.common.types.exception.DomainException;
import com.enterprise.airport.flightmanagement.domain.flight.FlightId;
import com.enterprise.airport.flightmanagement.domain.flight.FlightIsAnnounced;
import com.enterprise.airport.flightmanagement.domain.ticket.Ticket;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketIdGenerator;
import com.enterprise.airport.flightmanagement.usecase.flight.FlightExtractor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PublishTicketUseCase implements PublishTicket {
    private final FlightIsAnnounced flightIsAnnounceRule;
    private final TicketIdGenerator ticketIdGenerator;
    private final FlightExtractor flightExtractor;
    private final TicketPersister ticketPersister;

    @Override
    public TicketId execute(PublishTicketRequest request) {
        var flightOfTicket = flightExtractor.fidById(request.getFlightId())
                .orElseThrow(() -> IllegalFlightIdException.ofFlightId(request.getFlightId()));

        var publishedTicket = Ticket.publish(
                flightIsAnnounceRule,
                ticketIdGenerator,
                flightOfTicket,
                request.getPrice()
        );

        ticketPersister.save(publishedTicket);

        return publishedTicket.getId();
    }

    public static class IllegalFlightIdException extends DomainException {
        public IllegalFlightIdException(String message) {
            super(message);
        }

        public static IllegalFlightIdException ofFlightId(FlightId flightId) {
            return new IllegalFlightIdException(String.format("Wrong flight id: %s", flightId.getValue()));
        }
    }
}
