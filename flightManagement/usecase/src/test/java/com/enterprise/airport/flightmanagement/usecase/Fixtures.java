package com.enterprise.airport.flightmanagement.usecase;

import com.enterprise.airport.common.types.domain.base.Version;
import com.enterprise.airport.common.types.domain.common.AircraftModel;
import com.enterprise.airport.common.types.domain.common.Airport;
import com.enterprise.airport.flightmanagement.domain.aircraft.Aircraft;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftRestorer;
import com.enterprise.airport.flightmanagement.domain.flight.Flight;
import com.enterprise.airport.flightmanagement.domain.flight.FlightId;
import com.enterprise.airport.flightmanagement.domain.flight.FlightRestorer;
import com.enterprise.airport.flightmanagement.domain.order.Email;
import com.enterprise.airport.flightmanagement.domain.order.FullName;
import com.enterprise.airport.flightmanagement.domain.order.Order;
import com.enterprise.airport.flightmanagement.domain.order.OrderId;
import com.enterprise.airport.flightmanagement.domain.order.OrderItem;
import com.enterprise.airport.flightmanagement.domain.order.OrderRestorer;
import com.enterprise.airport.flightmanagement.domain.order.OrderStatus;
import com.enterprise.airport.flightmanagement.domain.order.Passenger;
import com.enterprise.airport.flightmanagement.domain.order.PassportNumber;
import com.enterprise.airport.flightmanagement.domain.ticket.Price;
import com.enterprise.airport.flightmanagement.domain.ticket.Ticket;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketRestorer;
import com.enterprise.airport.flightmanagement.usecase.order.CreateOrderRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Fixtures {
    private static final Random RANDOM = new Random();

    public static Aircraft newAircraft() {
        return AircraftRestorer.restore(
                new AircraftId(RANDOM.nextLong()),
                Version.from(RANDOM.nextLong()),
                AircraftModel.BOEING_747,
                RANDOM.nextLong()
        );
    }

    public static Flight generateFlight() {
        return FlightRestorer.restore(
                new FlightId(RANDOM.nextLong()),
                Version.from(RANDOM.nextLong()),
                Airport.AC,
                Airport.HM,
                LocalDateTime.of(2021, 5, 7, 8, 7),
                new AircraftId(RANDOM.nextLong())
        );
    }

    public static Flight flightWithDepartureTime(LocalDateTime departureTime) {
        return FlightRestorer.restore(
                new FlightId(RANDOM.nextLong()),
                Version.from(47L),
                Airport.AC,
                Airport.HM,
                departureTime,
                new AircraftId(RANDOM.nextLong())
        );
    }

    public static Flight flightWithAircraftId(AircraftId aircraftId) {
        return FlightRestorer.restore(
                new FlightId(RANDOM.nextLong()),
                Version.from(47L),
                Airport.AC,
                Airport.HM,
                LocalDateTime.of(2021, 5, 7, 8, 7),
                aircraftId
        );
    }

    public static List<Ticket> generateListTicket(int count) {
        var flightId = new FlightId(RANDOM.nextLong());

        var list = new ArrayList<Ticket>();

        for (int i = 0; i < count; i++) {
            list.add(
                    TicketRestorer.restore(
                            new TicketId(RANDOM.nextLong()),
                            Version.from(RANDOM.nextLong()),
                            flightId,
                            Price.from(BigDecimal.ONE.setScale(2))
                    )
            );
        }

        return list;
    }

    public static Order createdOrder() {
        return OrderRestorer.restore(
                new OrderId(RANDOM.nextLong()),
                Version.from(RANDOM.nextLong()),
                new ArrayList<>() {{
                    add(new OrderItem(
                            new TicketId(RANDOM.nextLong()),
                            new Passenger(
                                    new FullName("Antit", "Baititi"),
                                    new PassportNumber("AS8879645")
                            )
                    ));
                }},
                new Email("qwer@tutut.ba"),
                OrderStatus.CREATED
        );
    }

    public static Order createdOrderWithTicketId(TicketId ticketId) {
        return OrderRestorer.restore(
                new OrderId(RANDOM.nextLong()),
                Version.from(RANDOM.nextLong()),
                new ArrayList<>() {{
                    add(new OrderItem(
                            ticketId,
                            new Passenger(
                                    new FullName("Antit", "Baititi"),
                                    new PassportNumber("AS8879645")
                            )
                    ));
                }},
                new Email("qwer@tutut.ba"),
                OrderStatus.CREATED
        );
    }

    public static Passenger generatePassenger() {
        var passengerDto = Fixtures.generatePassengerDto();

        return new Passenger(
                new FullName(passengerDto.getFirstName(), passengerDto.getLastName()),
                new PassportNumber(passengerDto.getPassportNumber())
        );
    }

    public static CreateOrderRequest.PassengerDto generatePassengerDto() {
        return new CreateOrderRequest.PassengerDto(
                "Abib" + RANDOM.nextInt(),
                "Abeba" + RANDOM.nextInt(),
                null,
                "KS" + (1_000_000 + RANDOM.nextInt(999_999))
        );
    }

    public static Map<Long, CreateOrderRequest.PassengerDto> generateRawPassengersMap(List<Ticket> tickets) {
        return tickets.stream()
                .collect(Collectors.<Ticket, Long, CreateOrderRequest.PassengerDto>toMap(
                        ticket -> ticket.getId().getValue(),
                        ticket -> Fixtures.generatePassengerDto()
                ));
    }
}
