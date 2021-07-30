package com.enterprise.airport.leasing.usecase.aircraft;

import com.enterprise.airport.common.types.common.AircraftModel;
import com.enterprise.airport.leasing.domain.aircraft.AircraftContractNumber;
import com.enterprise.airport.leasing.domain.aircraft.AircraftLoadCapacity;
import com.enterprise.airport.leasing.domain.aircraft.AircraftRegistration;
import com.enterprise.airport.leasing.domain.aircraft.AircraftSeats;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BuyAircraftRequest {
    private AircraftModel model;
    private AircraftLoadCapacity aircraftLoadCapacity;
    private LocalDate issueDate;
    private AircraftRegistration registrationNumber;
    private AircraftSeats seats;
    private AircraftContractNumber contractNumber;

    public BuyAircraftRequest(
            String model,
            Double loadCapacity,
            LocalDate issueDate,
            String registrationNumber,
            List<String> seats,
            String contractNumber
    ) {
        this.model = AircraftModel.valueOf(model);
        this.aircraftLoadCapacity = new AircraftLoadCapacity(loadCapacity);
        this.issueDate = issueDate;
        this.registrationNumber = new AircraftRegistration(registrationNumber);
        this.seats = new AircraftSeats(seats);
        this.contractNumber = new AircraftContractNumber(contractNumber);
    }
}
