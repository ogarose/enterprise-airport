package com.enterprise.airport.flightmanagement.domain.order;

import com.enterprise.airport.flightmanagement.domain.Fixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class OrderTest {

    @Test
    void createNewOrder() {
        //Given we have 3 published tickets
        var tickets = Fixtures.generateListTicket(3);
        //AND 3 passengers who want to buy tickets
        var passenger1 = new Passenger(
                new FullName("Abib", "Abeba"),
                new PassportNumber("KS9875678")
        );
        var passenger2 = new Passenger(
                new FullName("Slk", "Izmailov"),
                new PassportNumber("KS4878787")
        );
        var passenger3 = new Passenger(
                new FullName("Ilk", "Bobo"),
                new PassportNumber("DS9876542")
        );

        //When customer creates order for the tickets
        var order = Order.create(
                ticket -> true,
                () -> new OrderId(8789L),
                new HashMap<>() {{
                    put(tickets.get(0), passenger1);
                    put(tickets.get(1), passenger2);
                    put(tickets.get(2), passenger3);
                }},
                new Email("iiii@google.com")
        );

        //Then order created and OrderCreated Event published
        var events = order.popEvents();
        Assertions.assertEquals(1, events.size());
        Assertions.assertEquals(OrderEvent.OrderCreated.class, events.get(0).getClass());

        var orderCreatedEvent = (OrderEvent.OrderCreated) events.get(0);
        Assertions.assertEquals(order.getId(), orderCreatedEvent.getOrderId());

        //AND order has passengers by ticket's id
        Assertions.assertEquals(order.getPassengers().get(tickets.get(0).getId()), passenger1);
        Assertions.assertEquals(order.getPassengers().get(tickets.get(1).getId()), passenger2);
        Assertions.assertEquals(order.getPassengers().get(tickets.get(2).getId()), passenger3);
    }

    @Test
    void payOrder() {
        var createdOrder = Fixtures.createdOrder();

        createdOrder.pay();

        Assertions.assertEquals(OrderStatus.PAID, createdOrder.getStatus());

        var events = createdOrder.popEvents();
        Assertions.assertEquals(1, events.size());
        Assertions.assertEquals(OrderEvent.OrderPaid.class, events.get(0).getClass());

        var orderCreatedEvent = (OrderEvent.OrderPaid) events.get(0);
        Assertions.assertEquals(createdOrder.getId(), orderCreatedEvent.getOrderId());
    }
}