package com.example.geo.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCodeType {

    String name();
    String getMessage();
    HttpStatus getHttpStatus();

}
