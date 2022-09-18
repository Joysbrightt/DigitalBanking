package com.Joysbright.digiBank.dtos.response;

import java.time.LocalDate;

public class AccountTransactionQuerryResponse {
    LocalDate transactionDate;
    String transactionType;
    String narration;
    Double amount;
    Double accountBalanceAfterTransaction;
}
