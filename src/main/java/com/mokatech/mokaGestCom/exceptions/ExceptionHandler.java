package com.mokatech.mokaGestCom.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ApiBaseException.class)
    public ResponseEntity<ExceptionResponse> handleApiExceptions(ApiBaseException ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getMessage(),
                ex.getStatus(),
                ex.getStatus().value(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(exceptionResponse,ex.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setUri(request.getDescription(false));
        validationResponse.setResponse(HttpStatus.BAD_REQUEST);
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for(FieldError f:fieldErrors){
            validationResponse.addError(f.getDefaultMessage());
        }
        return new ResponseEntity<>(validationResponse,HttpStatus.BAD_REQUEST);
    }
}
