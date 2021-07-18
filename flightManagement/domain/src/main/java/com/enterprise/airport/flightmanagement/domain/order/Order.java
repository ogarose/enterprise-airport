package com.enterprise.airport.flightmanagement.domain.order;

import com.enterprise.airport.common.types.base.AggregateRoot;
import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.common.types.exception.DomainException;
import com.enterprise.airport.flightmanagement.domain.ticket.Ticket;
import com.enterprise.airport.flightmanagement.domain.ticket.TicketAvailable;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class Order extends AggregateRoot<OrderId> {
    private List<OrderItem> orderItems;
    private Email customerEmail;
    private OrderStatus status;

    public Order(
            OrderId id,
            Version first,
            List<OrderItem> orderItems,
            Email customerEmail,
            OrderStatus status
    ) {
        super(id, first);
        this.orderItems = orderItems;
        this.customerEmail = customerEmail;
        this.status = status;
    }

    public void pay() {
        if (status == OrderStatus.PAID) {
            return;
        }

        if (!status.canBeChangeTo(OrderStatus.PAID)) {
            throw new CanNotSetToPaidException();
        }

        status = OrderStatus.PAID;

        addEvent(new OrderEvent.OrderPaid(getId()));
    }

    public static Order create(
            TicketAvailable isTicketAvailable,
            OrderIdGenerator idGenerator,
            Map<Ticket, Passenger> passengers,
            Email customerEmail
    ) {
        passengers.forEach((ticket, passenger) -> {
            if (!isTicketAvailable.check(ticket)) {
                throw new TicketIsNotAvailableException(
                        String.format("Ticket is not available ticket id: %s", ticket.getId()));
            }
        });

        var createdOrder = new Order(
                idGenerator.generate(),
                Version.first(),
                passengers.entrySet().stream()
                        .map(entry -> new OrderItem(entry.getKey().getId(), entry.getValue()))
                        .collect(Collectors.toList()
                ),
                customerEmail,
                OrderStatus.CREATED
        );

        createdOrder.addEvent(new OrderEvent.OrderCreated(createdOrder.getId()));

        return createdOrder;
    }

    public static class TicketIsNotAvailableException extends DomainException {
        public TicketIsNotAvailableException(String message) {
            super(message);
        }
    }

    public static class CanNotSetToPaidException extends DomainException {
    }

}
