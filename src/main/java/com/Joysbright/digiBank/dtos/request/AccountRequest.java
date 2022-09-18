package com.Joysbright.digiBank.dtos.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    private String accountName;
    private String password;
    private double initialDeposit;

}
