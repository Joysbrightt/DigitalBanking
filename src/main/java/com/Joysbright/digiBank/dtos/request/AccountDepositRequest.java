package com.Joysbright.digiBank.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class AccountDepositRequest {
   private String accountNo;
    private Double amount;
}