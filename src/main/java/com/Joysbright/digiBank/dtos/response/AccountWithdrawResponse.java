package com.Joysbright.digiBank.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountWithdrawResponse {
     private String message;
     private boolean isSuccessful;
     private int responseCode;
}
