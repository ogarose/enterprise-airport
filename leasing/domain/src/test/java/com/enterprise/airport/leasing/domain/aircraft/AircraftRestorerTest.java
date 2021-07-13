package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.common.types.common.AircraftModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class AircraftRestorerTest {

    @Test
    void restore() {
        var contractNumber = new AircraftContractNumber("48/98759/4C");
        var licenseNumber = new AircraftRegistration("D-IAPK");

        var seats = new AircraftSeats(
                List.of("A1", "B1", "A2", "B2", "A3", "B3")
        );
        var issueDate = LocalDate.of(2019, 6, 12);
        var loadCapacity = new AircraftLoadCapacity(5648.57);
        AircraftModel mark = AircraftModel.BOEING_747;
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
        Assertions.assertEquals(loadCapacity, aircraft.getAircraftLoadCapacity());
        Assertions.assertEquals(mark, aircraft.getModel());

        Assertions.assertEquals(0, aircraft.popEvents().size());
    }
}