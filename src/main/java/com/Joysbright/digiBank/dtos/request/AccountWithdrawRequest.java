package com.Joysbright.digiBank.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountWithdrawRequest {
  private String accountNo;
   private BigDecimal amount;
   private String password;
}
