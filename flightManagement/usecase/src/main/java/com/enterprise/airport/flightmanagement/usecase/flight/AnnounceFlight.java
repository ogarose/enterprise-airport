package com.enterprise.airport.flightmanagement.usecase.flight;

import com.enterprise.airport.flightmanagement.domain.flight.FlightId;

public interface AnnounceFlight {
    FlightId execute(AnnounceFlightRequest request);
}
