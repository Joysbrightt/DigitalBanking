package com.Joysbright.digiBank.exceptionClass;

public class BankException extends RuntimeException{
private String message;

    public BankException(String message) {
        super(message);
    }
}
