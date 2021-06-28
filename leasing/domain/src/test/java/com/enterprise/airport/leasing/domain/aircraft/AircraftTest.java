package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.base.Version;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class AircraftTest {

    @Test
    void buyAircraft() {
        var contractNumber = new AircraftContractNumber("48/98759/4C");
        var licenseNumber = new AircraftLicenseNumber("897 9 FD");

        boolean[][] seatsMap = {
                {true, true, false, true, true},
                {true, true, false, true, true},
                {true, true, false, false, false},
                {true, true, false, true, true},
        };
        var seats = new AircraftSeats(seatsMap);
        var issueDate = LocalDate.of(2019, 6, 12);
        double loadCapacity = 5648.57;
        AircraftMark mark = AircraftMark.BOEING_747;

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
        Assertions.assertEquals(licenseNumber, aircraft.getLicenseNumber());
        Assertions.assertEquals(loadCapacity, aircraft.getLoadCapacity());
        Assertions.assertEquals(mark, aircraft.getMark());
        Assertions.assertEquals(Version.first(), aircraft.getVersion());

        var events = aircraft.popEvents();

        Assertions.assertEquals(1, events.size());
        Assertions.assertEquals(AircraftEvent.Bought.class, events.get(0).getClass());

        var boughtEvent = (AircraftEvent.Bought) events.get(0);
        Assertions.assertEquals(aircraft.getId(), boughtEvent.getAircraftId());
    }
}