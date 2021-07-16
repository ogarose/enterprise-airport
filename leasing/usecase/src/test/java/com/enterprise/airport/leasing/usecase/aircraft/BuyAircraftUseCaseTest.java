package com.enterprise.airport.leasing.usecase.aircraft;

import com.enterprise.airport.leasing.domain.aircraft.AircraftId;
import com.enterprise.airport.leasing.usecase.util.AircraftPersisterUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class BuyAircraftUseCaseTest {
    @Test
    void buyAircraft() {
        var persister = new AircraftPersisterUtil();
        AircraftId expectedId = new AircraftId(987L);

        var usecase = new BuyAircraftUseCase(
                () -> expectedId,
                persister
        );

        var request = new BuyAircraftRequest(
                "AIRBUS_A220",
                45D,
                LocalDate.of(2021, 7, 25),
                "H-ASFW",
                List.of("A1", "A2", "A4", "B1"),
                "asd56as4d"
        );

        var newAircraftId = usecase.execute(request);

        Assertions.assertEquals(expectedId, newAircraftId);
        Assertions.assertNotNull(persister.data.get(expectedId));

        var boughtAircraft = persister.data.get(expectedId);

        Assertions.assertEquals(boughtAircraft.getAircraftLoadCapacity(), request.getAircraftLoadCapacity());
        Assertions.assertEquals(boughtAircraft.getContractNumber(), request.getContractNumber());
        Assertions.assertEquals(boughtAircraft.getIssueDate(), request.getIssueDate());
        Assertions.assertEquals(boughtAircraft.getModel(), request.getModel());
        Assertions.assertEquals(boughtAircraft.getRegistration(), request.getRegistrationNumber());
        Assertions.assertEquals(boughtAircraft.getSeats(), request.getSeats());
    }
}