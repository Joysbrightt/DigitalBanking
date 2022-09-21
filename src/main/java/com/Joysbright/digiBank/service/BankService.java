package com.Joysbright.digiBank.service;

import com.Joysbright.digiBank.dtos.request.RegisterUserRequest;
import com.Joysbright.digiBank.dtos.request.UserLoginRequest;
import com.Joysbright.digiBank.dtos.request.UserTransferRequest;
import com.Joysbright.digiBank.dtos.response.RegisterUserResponse;
import com.Joysbright.digiBank.dtos.response.UserLoginResponse;
import com.Joysbright.digiBank.dtos.response.UserTransferResponse;

public interface BankService {

    RegisterUserResponse register(RegisterUserRequest userRequest);

    UserLoginResponse login(UserLoginRequest loginRequest);

    UserTransferResponse transfer(UserTransferRequest transferRequest);
}
