package com.springboot.blog.exceptions;

public class InvalidCredentials extends RuntimeException {

    public InvalidCredentials(String message){
        super(message);
    }
}
