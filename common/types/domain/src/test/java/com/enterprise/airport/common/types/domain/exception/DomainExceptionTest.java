package com.enterprise.airport.common.types.domain.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DomainExceptionTest {

    @Test
    void domainExceptionMessageHasInputText() {
        String expectedText = "with text";
        var domainException = new DomainException(expectedText);

        Assertions.assertTrue(domainException.toString().contains(expectedText));
    }
}