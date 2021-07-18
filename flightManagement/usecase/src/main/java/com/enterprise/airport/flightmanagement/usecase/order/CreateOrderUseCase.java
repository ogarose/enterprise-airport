package com.enterprise.airport.flightmanagement.usecase.order;

import com.enterprise.airport.common.types.exception.DomainException;
import com.enterprise.airport.flightmanagement.domain.order.Order;
import com.enterprise.airport.flightmanagement.domain.order.OrderId;
import com.enterprise.airport.flightmanagement.domain.order.OrderIdGenerator;
import com.enterprise.airport.flightmanagement.domain.order.Passenger;
import com.enterprise.airport.flightmanagement.domain.ticket.Ticket;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketAvailable;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketId;
import com.enterprise.airport.flightmanagement.usecase.ticket.TicketExtractor;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CreateOrderUseCase implements CreateOrder {

    private final TicketAvailable ticketAvailableRule;
    private final OrderIdGenerator orderIdGenerator;
    private final TicketExtractor ticketExtractor;
    private final OrderPersister orderPersister;

    @Override
    public OrderId execute(CreateOrderRequest request) {
        Map<Ticket, Passenger> passengers = request.getPassengers().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> ticketExtractor.findById(entry.getKey())
                                .orElseThrow(() -> IllegalTicketIdException.ofTicket(entry.getKey())),
                        Map.Entry::getValue
                ));

        var newOrder = Order.create(
                ticketAvailableRule,
                orderIdGenerator,
                passengers,
                request.getCustomerEmail()
        );

        orderPersister.save(newOrder);

        return newOrder.getId();
    }

    public static class IllegalTicketIdException extends DomainException {
        public IllegalTicketIdException(String message) {
            super(message);
        }

        public static IllegalTicketIdException ofTicket(TicketId ticketId) {
            return new IllegalTicketIdException(String.format("Wrong ticket id: %s", ticketId.getValue()));
        }
    }
}
