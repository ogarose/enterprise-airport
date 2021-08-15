package com.enterprise.airport.common.types.usecase.error;

import com.enterprise.airport.common.types.domain.error.BusinessError;

public  class GeneralUseCaseError extends UseCaseError {
    private final BusinessError errorObject;

    public GeneralUseCaseError(BusinessError errorObject) {
        this.errorObject = errorObject;
        this.message = String.format("Unexpected Business Error happened. %s", errorObject.getClass().getName());
    }

    public Object getErrorObject() {
        return errorObject;
    }
}
