package com.Joysbright.digiBank.dtos.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    private String password;
    private String email;
}
