package com.Joysbright.digiBank.dtos.response;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountLoginResponse {
    boolean success;
    String accessToken;
}
