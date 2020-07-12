package com.polestar.bridges.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Name already Exists")
public class NameAlreadyExistException extends RuntimeException {
    public NameAlreadyExistException(String message){
        super(message);
    }
}
