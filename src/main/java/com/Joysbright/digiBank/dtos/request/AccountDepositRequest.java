package com.Joysbright.digiBank.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class AccountDepositRequest {
   private String accountNo;
    private BigDecimal amount;
}