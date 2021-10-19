package org.resilience4j.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomRestExceptionHandler {

    Logger LOG = LoggerFactory.getLogger("CustomRestExceptionHandler");

    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<Object> handleException(
            Exception ex,
            WebRequest request) {
        LOG.info("Exception handler is invoked for "+ ex.getMessage());
        List<String> errors = new ArrayList<>();
        errors.add("Booking failed!, " + ex.getMessage());

        ApiError apiError =
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), errors);
        return new ResponseEntity<Object>(apiError,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}