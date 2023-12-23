package com.mokatech.mokaGestCom.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApiBaseException{
    public EntityNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
