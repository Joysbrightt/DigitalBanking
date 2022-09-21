package com.Joysbright.digiBank.controller;

import com.Joysbright.digiBank.dtos.request.RegisterUserRequest;
import com.Joysbright.digiBank.dtos.request.UserLoginRequest;
import com.Joysbright.digiBank.dtos.request.UserTransferRequest;
import com.Joysbright.digiBank.dtos.response.RegisterUserResponse;
import com.Joysbright.digiBank.dtos.response.UserLoginResponse;
import com.Joysbright.digiBank.dtos.response.UserTransferResponse;
import com.Joysbright.digiBank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/{userRegister}")
    public ResponseEntity<?> userBankRegister(@PathVariable("userRegister") @RequestBody RegisterUserRequest userRequest){
        RegisterUserResponse registerUserResponse = bankService.register(userRequest);

        return new ResponseEntity<>(registerUserResponse, HttpStatus.CREATED);
    }

    @PostMapping("/{bankTransfer}")
    public ResponseEntity<?> userBankTransfer(@PathVariable("bankTransfer") @RequestBody UserTransferRequest transferRequest){
        UserTransferResponse transferResponse = bankService.transfer(transferRequest);

        return new ResponseEntity<>(transferResponse, HttpStatus.CREATED);

    }

    @GetMapping("/{userLogin}")
    public ResponseEntity<?> userBankLogin(@PathVariable("userLogin") @RequestBody UserLoginRequest loginRequest){
        UserLoginResponse loginResponse = bankService.login(loginRequest);

        return new ResponseEntity<>(loginResponse, HttpStatus.FOUND);
    }
}
