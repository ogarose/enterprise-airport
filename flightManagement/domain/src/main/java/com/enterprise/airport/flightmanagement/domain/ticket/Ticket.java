package com.enterprise.airport.flightmanagement.domain.ticket;

import com.enterprise.airport.common.types.base.AggregateRoot;
import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.common.types.exception.DomainException;
import com.enterprise.airport.flightmanagement.domain.flight.Flight;
import com.enterprise.airport.flightmanagement.domain.flight.FlightId;
import com.enterprise.airport.flightmanagement.domain.flight.FlightIsAnnounced;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
public class Ticket extends AggregateRoot<TicketId> {
    private FlightId flightId;
    private Price price;

    protected Ticket(
            TicketId id,
            Version first,
            FlightId flightId,
            Price price
    ) {
        super(id, first);
        this.flightId = flightId;
        this.price = price;
    }

    public static Ticket publish(
            FlightIsAnnounced flightIsAnnounced,
            TicketIdGenerator idGenerator,
            Flight flight,
            Price price
    ) {
        if (lessThan1HourToDeparture(flight)) {
            throw new LessThan1HourToDepartureException();
        }

        if (!flightIsAnnounced.check(flight)) {
            throw new FlightIsNotAnnouncedException();
        }

        var publishedTicket = new Ticket(
                idGenerator.generate(),
                Version.first(),
                flight.getId(),
                price
        );

        publishedTicket.addEvent(new TicketEvent.Published(publishedTicket.getId()));

        return publishedTicket;
    }

    private static boolean lessThan1HourToDeparture(Flight flight) {
        return ChronoUnit.MINUTES.between(LocalDateTime.now(), flight.getDepartureTime()) < 60;
    }

    public static class LessThan1HourToDepartureException extends DomainException {
    }

    public static class FlightIsNotAnnouncedException extends DomainException {
    }
}
