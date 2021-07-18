package com.enterprise.airport.flightmanagement.usecase.aircraft;

import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;

public interface TakeNewAircraft {
    AircraftId execute(TakeNewAircraftRequest request);
}
