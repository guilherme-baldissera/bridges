package com.polestar.bridges.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Lat and Long already Exists")
public class LatAndLongAlreadyExistException extends RuntimeException {
    public LatAndLongAlreadyExistException(String message){
        super(message);
    }
}
