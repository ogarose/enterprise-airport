package com.enterprise.airport.flightmanagement.usecase.order;

import com.enterprise.airport.flightmanagement.domain.order.Email;
import com.enterprise.airport.flightmanagement.domain.order.FullName;
import com.enterprise.airport.flightmanagement.domain.order.Passenger;
import com.enterprise.airport.flightmanagement.domain.order.PassportNumber;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.stream.Collectors;

@Data
public class CreateOrderRequest {
    Map<TicketId, Passenger> passengers;
    Email customerEmail;

    public CreateOrderRequest(
            Map<Long, PassengerDto> rawPassengers,
            String rawCustomerEmail
    ) {
        this.passengers = rawPassengers.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> new TicketId(entry.getKey()),
                        entry -> new Passenger(
                                new FullName(
                                        entry.getValue().getFirstName(),
                                        entry.getValue().getLastName(),
                                        entry.getValue().getMiddleName()
                                ),
                                new PassportNumber(entry.getValue().getPassportNumber())

                        )
                ));
        this.customerEmail = new Email(rawCustomerEmail);
    }

    @Data
    @AllArgsConstructor
    public static class PassengerDto {
        private String firstName;
        private String lastName;
        private String middleName;
        private String passportNumber;
    }
}
