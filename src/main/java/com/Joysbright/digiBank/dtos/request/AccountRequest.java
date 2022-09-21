package com.Joysbright.digiBank.dtos.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {

    private String accountName;
    private String password;
    private double initialDeposit;

}
