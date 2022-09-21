package com.Joysbright.digiBank.dtos.request;

import lombok.*;
import org.springframework.stereotype.Controller;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AccountRequestLogin {
    String accountNumber;
    String password;
    String accountId;
}
