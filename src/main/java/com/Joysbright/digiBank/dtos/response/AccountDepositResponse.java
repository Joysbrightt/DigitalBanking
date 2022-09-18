package com.Joysbright.digiBank.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDepositResponse {
   private int responseCode;
  private  boolean success;
   private String message;
}
