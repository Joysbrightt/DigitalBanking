package com.Joysbright.digiBank.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public class AccountWithdrawRequest {
  private String accountNo;
   private double amount;
   private String password;
}
