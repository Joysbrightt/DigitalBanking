package com.Joysbright.digiBank.service;

import com.Joysbright.digiBank.dtos.request.AccountDepositRequest;
import com.Joysbright.digiBank.dtos.request.AccountQuerryRequest;
import com.Joysbright.digiBank.dtos.request.AccountRequest;
import com.Joysbright.digiBank.dtos.request.AccountWithdrawRequest;
import com.Joysbright.digiBank.dtos.response.AccountDepositResponse;
import com.Joysbright.digiBank.dtos.response.AccountQuerryResponse;
import com.Joysbright.digiBank.dtos.response.AccountResponse;
import com.Joysbright.digiBank.dtos.response.AccountWithdrawResponse;
import com.Joysbright.digiBank.model.Account;
import com.Joysbright.digiBank.model.Transaction;

public interface AccountService {

     final int minimumBalance = 500;

    AccountDepositResponse deposit(AccountDepositRequest depositRequest);
    AccountWithdrawResponse withdraw(AccountWithdrawRequest request);
    AccountResponse createUser(AccountRequest request);
    AccountQuerryResponse querryAccount(AccountQuerryRequest querryRequest);
     AccountResponse anotherCreate(AccountRequest request);
}
