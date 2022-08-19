package com.springboot.blog.exceptions;

public class ApiException extends RuntimeException {

    public ApiException(String message){
        super(message);
    }
}
