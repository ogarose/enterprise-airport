package com.enterprise.airport.leasing.usecase.aircraft;

import com.enterprise.airport.leasing.domain.aircraft.AircraftId;

public interface BuyAircraft {
    AircraftId execute(BuyAircraftRequest request);
}
