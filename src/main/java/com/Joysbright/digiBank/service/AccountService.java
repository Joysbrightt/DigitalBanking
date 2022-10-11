package com.Joysbright.digiBank.service;

import com.Joysbright.digiBank.dtos.request.*;
import com.Joysbright.digiBank.dtos.response.*;
import com.Joysbright.digiBank.model.Account;
import com.Joysbright.digiBank.model.Transaction;

import java.util.List;

public interface AccountService {

     final int minimumBalance = 500;

    AccountDepositResponse deposit(AccountDepositRequest depositRequest);
    AccountWithdrawResponse withdraw(AccountWithdrawRequest request);
    AccountResponse createAccount(AccountRequest request);
    AccountQuerryResponse querryAccount(String accountNo, AccountQuerryRequest querryRequest);
     AccountResponse anotherCreate(AccountRequest request);

    Account findAccountByAccountName(String accountName);

    Account findByAccountNo(String accountNo);


    List<Transaction> getAccountStatement(String accountNo);

    UserTransferResponse transfer(UserTransferRequest transferRequest);

    AccountLoginResponse accountLogin(AccountRequestLogin requestLogin);
}
