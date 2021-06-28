package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.base.Version;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class AircraftRestorerTest {

    @Test
    void restore() {
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
        Version version = Version.from(25L);
        AircraftId aircraftId = new AircraftId(658L);

        var aircraft = AircraftRestorer.restore(
                aircraftId,
                version,
                mark,
                loadCapacity,
                issueDate,
                licenseNumber,
                seats,
                contractNumber
        );

        Assertions.assertEquals(aircraftId, aircraft.getId());
        Assertions.assertEquals(version, aircraft.getVersion());
        Assertions.assertEquals(contractNumber, aircraft.getContractNumber());
        Assertions.assertEquals(issueDate, aircraft.getIssueDate());
        Assertions.assertEquals(licenseNumber, aircraft.getLicenseNumber());
        Assertions.assertEquals(loadCapacity, aircraft.getLoadCapacity());
        Assertions.assertEquals(mark, aircraft.getMark());

        Assertions.assertEquals(0, aircraft.popEvents().size());
    }
}