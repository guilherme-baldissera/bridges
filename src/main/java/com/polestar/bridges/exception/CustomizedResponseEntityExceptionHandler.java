package com.polestar.bridges.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@CrossOrigin(origins = "*")
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NameAlreadyExistException.class)
    public final ResponseEntity<Object> handleUserAlreadyExistException(NameAlreadyExistException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));

        System.out.println(ex.getMessage());
        ex.printStackTrace(System.err);

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LatAndLongAlreadyExistException.class)
    public final ResponseEntity<Object> handleUserAlreadyExistException(LatAndLongAlreadyExistException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));

        System.out.println(ex.getMessage());
        ex.printStackTrace(System.err);

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
