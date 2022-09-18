package com.Joysbright.digiBank.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    public int minimumBalance;

    @Generated()
    private String accountId;

    @NonNull
    @NotBlank
    private String accountName;
    @NonNull
    @NotBlank
    private String  accountNo;
    @NonNull
    @NotBlank
    private String password;

    private BigDecimal balance;

    private List<Transaction> transactionList;
}
