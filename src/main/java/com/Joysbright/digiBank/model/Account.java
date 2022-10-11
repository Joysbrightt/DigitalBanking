package com.Joysbright.digiBank.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    public double minimumBalance;

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

    private BigDecimal balance = BigDecimal.ZERO;

    private List<Transaction> transactionList = new ArrayList<>();
}
