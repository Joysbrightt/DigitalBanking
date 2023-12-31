package com.Joysbright.digiBank.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponse {
    private String message;
    private String firstName;
}
