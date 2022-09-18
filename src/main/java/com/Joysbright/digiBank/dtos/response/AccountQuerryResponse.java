package com.Joysbright.digiBank.dtos.response;

import com.Joysbright.digiBank.model.Account;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountQuerryResponse {
    int responseCode;
    boolean success;
    String message;
    Account account;
}
