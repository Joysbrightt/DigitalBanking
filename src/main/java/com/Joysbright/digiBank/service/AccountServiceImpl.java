package com.Joysbright.digiBank.service;

import com.Joysbright.digiBank.dtos.request.AccountDepositRequest;
import com.Joysbright.digiBank.dtos.request.AccountQuerryRequest;
import com.Joysbright.digiBank.dtos.request.AccountRequest;
import com.Joysbright.digiBank.dtos.request.AccountWithdrawRequest;
import com.Joysbright.digiBank.dtos.response.AccountDepositResponse;
import com.Joysbright.digiBank.dtos.response.AccountQuerryResponse;
import com.Joysbright.digiBank.dtos.response.AccountWithdrawResponse;
import com.Joysbright.digiBank.dtos.response.AccountResponse;
import com.Joysbright.digiBank.exceptionClass.BankException;
import com.Joysbright.digiBank.model.Account;
import com.Joysbright.digiBank.model.Transaction;
import com.Joysbright.digiBank.model.Transactiontype;
import com.Joysbright.digiBank.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;

import static java.math.BigDecimal.valueOf;

@Service
@Slf4j

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    private static int transactionIdCounter;
    private static  int accountId;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDepositResponse deposit(AccountDepositRequest depositRequest) {
        Account account = accountRepository.findByAccountNo(depositRequest.getAccountNo());
        if (!account.getAccountNo().equals(depositRequest.getAccountNo())) throw new BankException("invalid account details");
        if (depositRequest.getAmount() > 10000000 || depositRequest.getAmount() < 1) throw new BankException("deposit amount cannot be less than 1 or greater than 1000000");
        BigDecimal balance = account.getBalance().add(BigDecimal.valueOf(depositRequest.getAmount()));
        account.setBalance(balance);

        Transaction transaction = Transaction.builder()
                .transactionType(Transactiontype.DEPOSIT)
                .balanceAfterTransaction(balance)
                .id(++transactionIdCounter)
                .localDate(LocalDate.now())
                .narration("deposit of " +depositRequest.getAmount() +" was made").amount(depositRequest.getAmount())
                .build();
        account.getTransactionList().add(transaction);
        accountRepository.save(account);

        AccountDepositResponse depositResponse = new AccountDepositResponse();
        depositResponse.setResponseCode(200);
        depositResponse.setMessage("account deposit make successfully");
        depositResponse.setSuccess(true);
        return depositResponse;
    }

    @Override
    public AccountWithdrawResponse withdraw(AccountWithdrawRequest request ) {
        Account account = accountRepository.findByAccountId(request.getAccountNo());
        if (!account.getAccountNo().equals(request.getAccountNo())) throw new BankException("account doesn't exist");
        if (request.getAmount() < 1) throw new BankException("withdrawal amount cannot be less than 1");
        if (account.getBalance().compareTo(valueOf(request.getAmount())) < 0) {
              throw new BankException("please enter a valid amount");
          }

          if (!account.getPassword().equals(request.getPassword())) throw new BankException("incorrect password");

        BigDecimal balance = account.getBalance().subtract(BigDecimal.valueOf(request.getAmount()));
        account.setBalance(balance);
        Transaction transaction = Transaction.builder()
                .transactionType(Transactiontype.DEPOSIT)
                .balanceAfterTransaction(balance)
                .id(++transactionIdCounter)
                .localDate(LocalDate.now())
                .narration("deposit of " +request.getAmount() +" was made").amount(request.getAmount())
                .build();
        account.getTransactionList().add(transaction);
        accountRepository.save(account);
        AccountWithdrawResponse withdrawResponse = new AccountWithdrawResponse();
        withdrawResponse.setResponseCode(200);
        withdrawResponse.setMessage("you have successfully withdraw" +request.getAmount());
        withdrawResponse.setSuccessful(true);

        return withdrawResponse;
        }


    @Override
    public AccountResponse createUser(AccountRequest request) {
        Account account = new Account();
        SecureRandom secureRandom;
        if (account.getAccountName().equals(request.getAccountName())) throw new BankException("account already exist");
        secureRandom = new SecureRandom();
//        valid account has initial deposit of 500 before opening account
        if (account.minimumBalance < 500 || account.minimumBalance > 1000000) throw new BankException("account cannot be less than 500 or greater than 1000000");
        String accountNo = String.valueOf(secureRandom.nextInt(764890976));
           account.setAccountNo(accountNo);
           account.setAccountName(request.getAccountName());
//           generating account by incrementing it
            account.setAccountId(String.valueOf(++accountId));
            account.setPassword(request.getPassword());
            Account savedAccount = accountRepository.save(account);
            AccountResponse response = new AccountResponse();
            response.setMessage(savedAccount.getAccountName()+ "account created successfully");
        return response;
    }



    @Override
    public AccountQuerryResponse querryAccount(AccountQuerryRequest querryRequest) {
        Account account = accountRepository.findByAccountNo(querryRequest.getAccountNo());
//        if account is null throw exception
        if (account.getAccountNo().equals(null)) throw new BankException("account doesn't exist");


        return AccountQuerryResponse.builder()
                .account(account)
                .responseCode(200)
                .success(true)
                .message("account was successfully queried")
                .build();
    }


    @Override
    public AccountResponse anotherCreate(AccountRequest request) {
                Account account = Account.builder()
                        .accountName(request.getAccountName())
                        .password(request.getPassword())
                        .accountId(request.getAccountName())
                        .build();

        return new AccountResponse();
    }

}
