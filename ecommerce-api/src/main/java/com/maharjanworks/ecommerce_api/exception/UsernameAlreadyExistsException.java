package com.maharjanworks.ecommerce_api.exception;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(String message){
        super(message);
    }
}
