package com.Joysbright.digiBank.dtos.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {

    private String accountName;
    private String password;
    private BigDecimal initialDeposit;

}
