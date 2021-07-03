package com.enterprise.airport.flightmanagement.domain.order;

@FunctionalInterface
public interface OrderIdGenerator {
    OrderId generate();
}
