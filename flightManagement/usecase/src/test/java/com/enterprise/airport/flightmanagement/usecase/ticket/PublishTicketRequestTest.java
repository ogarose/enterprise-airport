package com.enterprise.airport.flightmanagement.usecase.ticket;

import com.enterprise.airport.common.types.domain.common.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PublishTicketRequestTest {

    @Test
    void throwExceptionWhenBigDecimalPriceHasDefaultScale() {
        Assertions.assertThrows(Price.WrongScaleException.class, () -> {
            new PublishTicketRequest(
                    987L,
                    BigDecimal.ONE
            );
        });
    }
}