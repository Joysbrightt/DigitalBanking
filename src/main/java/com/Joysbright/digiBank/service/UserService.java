package com.Joysbright.digiBank.service;

import com.Joysbright.digiBank.dtos.request.RegisterUserRequest;
import com.Joysbright.digiBank.dtos.request.UserLoginRequest;
import com.Joysbright.digiBank.dtos.response.RegisterUserResponse;

public interface UserService {

    RegisterUserResponse register(RegisterUserRequest userRequest);

    UserLoginRequest login(UserLoginRequest loginRequest);
}
