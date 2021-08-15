package com.enterprise.airport.flightmanagement.domain.airoport;

import com.enterprise.airport.common.types.domain.common.Airport;

import java.time.LocalDateTime;

public interface AirportAllowDepartureTime {
    boolean check(Airport airport, LocalDateTime departureTime);
}
