package com.enterprise.airport.maintenance.usecase.flight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.instanceOf;

class FinishFlightRequestTest {

    @Test
    void leftContains2ErrorsWhenWrongAirportAndFlightHours() {
        var eitherResult = FinishFlightRequest.from(
                65L,
                "slkdjlkds",
                -548L
        );

        Assertions.assertTrue(eitherResult.isLeft());
        Assertions.assertEquals(2, eitherResult.getLeft().size());

        assertThat(eitherResult.getLeft(), hasItem(instanceOf(FinishFlightError.WrongAirportError.class)));
        assertThat(eitherResult.getLeft(), hasItem(instanceOf(FinishFlightError.WrongFlightHoursError.class)));
    }

    @Test
    void leftContainsWrongAirportError() {
        var eitherResult = FinishFlightRequest.from(
                65L,
                "slkdjlkds",
                548L
        );

        Assertions.assertTrue(eitherResult.isLeft());
        Assertions.assertEquals(1, eitherResult.getLeft().size());

        assertThat(eitherResult.getLeft(), hasItem(instanceOf(FinishFlightError.WrongAirportError.class)));
    }
}