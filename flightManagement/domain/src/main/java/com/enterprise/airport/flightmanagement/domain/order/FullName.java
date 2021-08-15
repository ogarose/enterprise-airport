package com.enterprise.airport.flightmanagement.domain.order;

import com.enterprise.airport.common.types.domain.base.ValueObject;
import com.enterprise.airport.common.types.domain.exception.DomainException;
import lombok.Value;

@Value
public class FullName implements ValueObject {
    String firstName;
    String lastName;
    String middleName;


    public FullName(String firstName, String lastName, String middleName) {
        validateFirstName(firstName);
        validateLastName(lastName);

        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public FullName(String firstName, String lastName) {
        this(firstName, lastName, null);
    }

    private void validateLastName(String lastName) {
        if (lastName == null || lastName.length() == 0) {
            throw new EmptyLastNameException();
        }
    }

    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.length() == 0) {
            throw new EmptyFirstNameException();
        }
    }

    public static class EmptyFirstNameException extends DomainException {
    }

    public static class EmptyLastNameException extends DomainException {
    }
}
