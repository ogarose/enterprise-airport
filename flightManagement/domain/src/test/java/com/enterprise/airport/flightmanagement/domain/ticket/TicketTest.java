package com.enterprise.airport.flightmanagement.domain.ticket;


import com.enterprise.airport.common.types.domain.common.Price;
import com.enterprise.airport.flightmanagement.domain.Fixtures;
import com.enterprise.airport.flightmanagement.domain.flight.Flight;
import com.enterprise.airport.flightmanagement.domain.flight.FlightIsAnnounced;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class TicketTest {

    @Test
    void announcedTicket() {
        var ticket = Ticket.publish(
                new FlightIsAnnounced() {
                    @Override
                    public boolean check(Flight flight) {
                        return true;
                    }
                },
                () -> new TicketId(6548L),
                Fixtures.flightWithDepartureTime(LocalDateTime.now().plusMinutes(120L)),
                Price.from(BigDecimal.valueOf(65L).setScale(2))
        );

        var events = ticket.popEvents();
        Assertions.assertEquals(1, events.size());
        Assertions.assertEquals(TicketEvent.Published.class, events.get(0).getClass());

        var publishedEvent = (TicketEvent.Published) events.get(0);
        Assertions.assertEquals(ticket.getId(), publishedEvent.getTicketId());
    }

    @Test
    void errorWhenItIsLessThat1HourToDeparture() {
        Assertions.assertThrows(Ticket.LessThan1HourToDepartureException.class, () -> {
            Ticket.publish(
                    new FlightIsAnnounced() {
                        @Override
                        public boolean check(Flight flight) {
                            return true;
                        }
                    },
                    () -> new TicketId(6548L),
                    Fixtures.flightWithDepartureTime(LocalDateTime.now().plusMinutes(50L)),
                    Price.from(BigDecimal.valueOf(65L).setScale(2))
            );
        });
    }

    @Test
    void errorWhenFlightIsNotAnnounced() {
        Assertions.assertThrows(Ticket.FlightIsNotAnnouncedException.class, () -> {
            Ticket.publish(
                    new FlightIsAnnounced() {
                        @Override
                        public boolean check(Flight flight) {
                            return false;
                        }
                    },
                    () -> new TicketId(6548L),
                    Fixtures.flightWithDepartureTime(LocalDateTime.now().plusMinutes(120L)),
                    Price.from(BigDecimal.valueOf(65L).setScale(2))
            );
        });
    }
}