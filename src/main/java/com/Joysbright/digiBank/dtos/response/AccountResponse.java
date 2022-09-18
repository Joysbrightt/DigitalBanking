package com.Joysbright.digiBank.dtos.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

  private int statusCode;
  private boolean isSuccess;
    private String message;



}
