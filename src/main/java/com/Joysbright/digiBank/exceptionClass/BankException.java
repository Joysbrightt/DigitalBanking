package com.Joysbright.digiBank.exceptionClass;

import lombok.Getter;

@Getter
public class BankException extends RuntimeException{
private String message;
private int statusCode;
    public BankException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
