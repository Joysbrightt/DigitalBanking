package com.Joysbright.digiBank.exceptionClass;

import com.Joysbright.digiBank.dtos.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BankException.class)
    public ResponseEntity<?> bankExceptionHandler(BankException bankException){
         ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                                            .isSuccess(false)
                                            .message(bankException.getMessage())
                                            .StatusCode(bankException.getStatusCode())
                                            .build();
         return new ResponseEntity<>(apiErrorResponse,HttpStatus.BAD_REQUEST);
    }

}
