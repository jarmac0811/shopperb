package com.recruitment.beerRestApiTask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { HttpStatusCodeException.class })
    protected ResponseEntity<Object> handleConflict(HttpStatusCodeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getResponseBodyAsString(),
                ex.getResponseHeaders(), HttpStatus.NOT_FOUND, request);
    }
}

