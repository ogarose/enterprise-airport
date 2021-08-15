package com.enterprise.airport.common.types.common;

import com.enterprise.airport.common.types.domain.exception.DomainException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value()
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Price {
    private static final int SCALE = 2;

    BigDecimal value;

    public static Price zero() {
        return new Price(BigDecimal.ZERO.setScale(Price.SCALE));
    }

    public static Price from(BigDecimal fromValue) {
        if (fromValue.scale() != Price.SCALE) {
            throw new WrongScaleException();
        }

        if (fromValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeException();
        }

        return new Price(fromValue);
    }

    public static class WrongScaleException extends DomainException {
    }

    public static class NegativeException extends DomainException {
    }
}
