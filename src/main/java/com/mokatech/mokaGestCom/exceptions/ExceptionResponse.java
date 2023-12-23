package com.mokatech.mokaGestCom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private final String message;
    private final HttpStatus title;
    private final int status;
    private final String instance;
}
