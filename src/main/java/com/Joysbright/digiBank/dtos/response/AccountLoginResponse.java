package com.Joysbright.digiBank.dtos.response;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountLoginResponse {
    boolean isSuccess;
    int statusCode;
    String message;
    String accessToken;
}
