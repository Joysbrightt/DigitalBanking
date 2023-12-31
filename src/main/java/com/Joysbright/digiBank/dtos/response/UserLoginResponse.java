package com.Joysbright.digiBank.dtos.response;

import com.Joysbright.digiBank.model.Account;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginResponse {
    private String message;
    private Account account;

}
