package com.Joysbright.digiBank.dtos.response;


import com.Joysbright.digiBank.model.Account;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  UserTransferResponse {

    private String message;
    private int statusCode;
    private boolean isSuccessful;

}
