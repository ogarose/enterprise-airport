package com.enterprise.airport.flightmanagement.domain.airoport;

import java.time.LocalDateTime;

public interface AirportAllowDepartureTime {
    boolean check(Airport airport, LocalDateTime departureTime);
}
