package com.enterprise.airport.flightmanagement.usecase.ticket;


import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;
import com.enterprise.airport.flightmanagement.usecase.Fixtures;
import com.enterprise.airport.flightmanagement.usecase.fake.FlightPersisterFake;
import com.enterprise.airport.flightmanagement.usecase.fake.TicketPersisterFake;
import com.enterprise.airport.flightmanagement.usecase.rules.FlightIsAnnouncedImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class PublishTicketUseCaseTest {

    public static final int PRICE_SCALE = 2;
    private FlightPersisterFake flightExtractor;
    private FlightIsAnnouncedImpl flightIsAnnounceRule;
    private TicketPersisterFake ticketPersister;

    @BeforeEach
    void beforeTest() {
        flightExtractor = new FlightPersisterFake();
        flightIsAnnounceRule = new FlightIsAnnouncedImpl();
        ticketPersister = new TicketPersisterFake();
    }

    @Test
    void publishTicket() {
        var forFlight = Fixtures.flightWithDepartureTime(LocalDateTime.of(2024, 1, 5, 4, 7));
        flightExtractor.save(forFlight);


        var usecase = new PublishTicketUseCase(
                flightIsAnnounceRule,
                () -> new TicketId(654L),
                flightExtractor,
                ticketPersister
        );

        var request = new PublishTicketRequest(
                forFlight.getId().getValue(),
                BigDecimal.ONE.setScale(PRICE_SCALE)
        );

        var publishedTicketId = usecase.execute(request);

        Assertions.assertNotNull(publishedTicketId);
        Assertions.assertTrue(ticketPersister.data.containsKey(publishedTicketId));
    }

    @Test
    void throwExceptionWhenFlightDoesNotExist() {
        var exception = Assertions.assertThrows(PublishTicketUseCase.IllegalFlightIdException.class, () -> {
            var usecase = new PublishTicketUseCase(
                    flightIsAnnounceRule,
                    () -> new TicketId(654L),
                    flightExtractor,
                    ticketPersister
            );

            var request = new PublishTicketRequest(
                    6548L,
                    BigDecimal.ONE.setScale(PRICE_SCALE)
            );

            usecase.execute(request);
        });

        Assertions.assertTrue(exception.getMessage().contains("654"));
    }
}