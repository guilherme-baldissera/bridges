package com.polestar.bridges.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
public class ErrorDetails {

    @Getter
    private Date timestamp;
    @Getter
    private String message;
    @Getter
    private String details;
}

