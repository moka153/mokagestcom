package com.mokatech.mokaGestCom.exceptions;

import org.springframework.http.HttpStatus;

public abstract class ApiBaseException extends RuntimeException{

    public ApiBaseException(String message) {
        super(message);
    }

    public abstract HttpStatus getStatus();
}
