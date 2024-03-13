package com.sena.arepasRJ.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsCustomer extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(PersonalExceptions.class)
    public ResponseEntity<ErrorResponse> handlePersonalException(PersonalExceptions pe) {
        ErrorResponse response = new ErrorResponse(pe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
