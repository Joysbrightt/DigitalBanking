package com.Joysbright.digiBank.dtos.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountWithdrawResponse {
     private String message;
     private boolean isSuccessful;
     private int responseCode;
}
