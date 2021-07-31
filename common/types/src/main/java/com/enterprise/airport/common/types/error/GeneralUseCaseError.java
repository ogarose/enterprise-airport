package com.enterprise.airport.common.types.error;

public  class GeneralUseCaseError extends UseCaseError {
    private final Object errorObject;

    public GeneralUseCaseError(Object errorObject) {
        this.errorObject = errorObject;
        this.message = String.format("Unexpected Error happened. %s", errorObject.getClass().getName());
    }

    public Object getErrorObject() {
        return errorObject;
    }
}
