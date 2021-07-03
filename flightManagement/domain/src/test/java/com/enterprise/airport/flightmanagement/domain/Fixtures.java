package com.enterprise.airport.flightmanagement.domain;

import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.common.types.common.AircraftModel;
import com.enterprise.airport.flightmanagement.domain.aircraft.Aircraft;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftRestorer;
import com.enterprise.airport.flightmanagement.domain.airoport.Airport;
import com.enterprise.airport.flightmanagement.domain.flight.Flight;
import com.enterprise.airport.flightmanagement.domain.flight.FlightId;
import com.enterprise.airport.flightmanagement.domain.flight.FlightRestorer;
import com.enterprise.airport.flightmanagement.domain.order.Email;
import com.enterprise.airport.flightmanagement.domain.order.FullName;
import com.enterprise.airport.flightmanagement.domain.order.Order;
import com.enterprise.airport.flightmanagement.domain.order.OrderId;
import com.enterprise.airport.flightmanagement.domain.order.OrderRestorer;
import com.enterprise.airport.flightmanagement.domain.order.OrderStatus;
import com.enterprise.airport.flightmanagement.domain.order.Passenger;
import com.enterprise.airport.flightmanagement.domain.order.PassportNumber;
import com.enterprise.airport.flightmanagement.domain.ticket.Price;
import com.enterprise.airport.flightmanagement.domain.ticket.Ticket;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketRestorer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Fixtures {
    private static final Random random = new Random();

    public static Aircraft newAircraft() {
        return AircraftRestorer.restore(
                new AircraftId(987L),
                Version.from(45L),
                AircraftModel.BOEING_747,
                48L
        );
    }

    public static Flight flightWithDepartureTime(LocalDateTime departureTime) {
        return FlightRestorer.restore(
                new FlightId(548L),
                Version.from(47L),
                Airport.AC,
                Airport.HM,
                departureTime,
                new AircraftId(987L)
        );
    }

    public static List<Ticket> generateListTicket(int count) {
        var flightId = new FlightId(random.nextLong());

        var list = new ArrayList<Ticket>();

        for (int i = 0; i < count; i++) {
            list.add(
                    TicketRestorer.restore(
                            new TicketId(random.nextLong()),
                            Version.from(random.nextLong()),
                            flightId,
                            Price.from(BigDecimal.ONE.setScale(2))
                    )
            );
        }

        return list;
    }

    public static Order createdOrder() {
        return OrderRestorer.restore(
                new OrderId(123L),
                Version.from(654L),
                new HashMap<>() {{
                    put(new TicketId(854L), new Passenger(
                            new FullName("Antit", "Baititi"),
                            new PassportNumber("AS8879645")
                    ));
                }},
                new Email("qwer@tutut.ba"),
                OrderStatus.CREATED
        );
    }
}
