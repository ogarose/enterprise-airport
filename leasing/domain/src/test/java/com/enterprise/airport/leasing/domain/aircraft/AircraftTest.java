package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.common.types.common.AircraftModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class AircraftTest {

    @Test
    void buyAircraft() {
        var contractNumber = new AircraftContractNumber("48/98759/4C");
        var licenseNumber = new AircraftRegistration("Z-ASRG");

        var seats = new AircraftSeats(
                List.of("A1", "B1", "A2", "B2", "A3", "B3")
        );
        var issueDate = LocalDate.of(2019, 6, 12);
        var loadCapacity = new AircraftLoadCapacity(5648.57);
        AircraftModel mark = AircraftModel.BOEING_747;

        var aircraft = Aircraft.buy(
                () -> new AircraftId(254L),
                mark,
                loadCapacity,
                issueDate,
                licenseNumber,
                seats,
                contractNumber
        );

        Assertions.assertEquals(new AircraftId(254L), aircraft.getId());
        Assertions.assertEquals(contractNumber, aircraft.getContractNumber());
        Assertions.assertEquals(issueDate, aircraft.getIssueDate());
        Assertions.assertEquals(licenseNumber, aircraft.getRegistration());
        Assertions.assertEquals(loadCapacity, aircraft.getAircraftLoadCapacity());
        Assertions.assertEquals(mark, aircraft.getModel());
        Assertions.assertEquals(Version.first(), aircraft.getVersion());

        var events = aircraft.popEvents();

        Assertions.assertEquals(1, events.size());
        Assertions.assertEquals(AircraftEvent.Bought.class, events.get(0).getClass());

        var boughtEvent = (AircraftEvent.Bought) events.get(0);
        Assertions.assertEquals(aircraft.getId(), boughtEvent.getAircraftId());
    }
}