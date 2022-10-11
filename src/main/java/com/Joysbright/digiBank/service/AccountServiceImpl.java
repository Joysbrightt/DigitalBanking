package com.Joysbright.digiBank.service;

import com.Joysbright.digiBank.dtos.request.*;
import com.Joysbright.digiBank.dtos.response.*;
import com.Joysbright.digiBank.exceptionClass.BankException;
import com.Joysbright.digiBank.model.Account;
import com.Joysbright.digiBank.model.Transaction;
import com.Joysbright.digiBank.model.Transactiontype;
import com.Joysbright.digiBank.repository.AccountRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;

import static java.math.BigDecimal.valueOf;

@Service
@Slf4j

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    private static int transactionIdCounter;
    private static  int accountId;
    private int accountNumber = 10001;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDepositResponse deposit(AccountDepositRequest depositRequest) {
        Account account = accountRepository.findByAccountNo(depositRequest.getAccountNo());
        if (!account.getAccountNo().equals(depositRequest.getAccountNo())) throw new BankException("invalid account details", 404);
        if (depositRequest.getAmount().compareTo(BigDecimal.valueOf(1000000)) >= 1 || depositRequest.getAmount().compareTo(BigDecimal.valueOf(500)) <= -1) throw new BankException("deposit amount cannot be less than 1 or greater than 1000000", 401);
//        Account.builder().accountNo(depositRequest.getAccountNo()).build();
        BigDecimal balance = account.getBalance().add(depositRequest.getAmount());
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
        Account account = accountRepository.findByAccountNo(request.getAccountNo());
        if (!account.getAccountNo().equals(request.getAccountNo())) throw new BankException("account doesn't exist",404);
        if (request.getAmount().compareTo(BigDecimal.valueOf(500)) <= -1) throw new BankException("withdrawal amount cannot be less than 500", 401);
        if (account.getBalance().subtract(request.getAmount()).compareTo(BigDecimal.valueOf(500)) <= -1 ) {
              throw new BankException("please enter a valid amount", 404);
          }

          if (!account.getPassword().equals(request.getPassword())) throw new BankException("incorrect password", 404);

        BigDecimal balance = account.getBalance().subtract(request.getAmount());
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
        withdrawResponse.setMessage("you have successfully withdraw " +request.getAmount());
        withdrawResponse.setSuccessful(true);

        return withdrawResponse;
        }


    @Override
    public AccountResponse createAccount(AccountRequest request) {
        Account account = accountRepository.findByAccountName(request.getAccountName());
        SecureRandom secureRandom;
        if (account != null) {throw new BankException("account already exist",400);
        }
        secureRandom = new SecureRandom();
        account = new Account();
//        valid account has initial deposit of 500 before opening account
        if (request.getInitialDeposit().compareTo(BigDecimal.valueOf(500)) <= -1 || request.getInitialDeposit().compareTo(BigDecimal.valueOf(1000000)) >= 1) throw new BankException("account cannot be less than 500 or greater than 1000000", 401);
        int accountNumber = this.accountNumber++;
        String accountNo = String.valueOf(accountNumber);
           account.setAccountNo(accountNo);
           account.setAccountName(request.getAccountName());
//           generating account by incrementing it
            account.setAccountId(String.valueOf(++accountId));
            account.setPassword(request.getPassword());
            accountRepository.save(account);
            AccountResponse response = new AccountResponse();
            response.setMessage(account.getAccountName()+ " account created successfully");
        return response;
    }



    @Override
    public AccountQuerryResponse querryAccount(String accountNo, AccountQuerryRequest querryRequest) {
        Account account = accountRepository.findByAccountNo(accountNo);
//        if account is null throw exception
        if (account.getAccountNo().equals(null)) throw new BankException("account doesn't exist", 404);

        if (!account.getPassword().equals(querryRequest.getPassword())) throw new BankException("invalid query details", 400);

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

    @Override

    public Account findAccountByAccountName(String accountName) {
        return accountRepository.findByAccountName(accountName);
    }

    @Override
    public Account findByAccountNo(String accountNo) {
        return accountRepository.findByAccountNo(accountNo);
    }

    @Override
    public List<Transaction> getAccountStatement(String accountNo) {
        Account account = accountRepository.findByAccountNo(accountNo);
        if (!account.getAccountNo().equals(accountNo)) throw new BankException("incorrect user details", 404);
        return account.getTransactionList();
    }

    @Override
    public UserTransferResponse transfer(UserTransferRequest transferRequest) {
        Account senderAccount = accountRepository.findByAccountNo(transferRequest.getSenderAccountNo());
        if(senderAccount == null ){
            throw new BankException("Account does not exist", 404);
        }
        Account receiverAccount = accountRepository.findByAccountNo(transferRequest.getReceiverAccountNo());
        if(receiverAccount == null){
            throw new BankException("Account does not exist", 400);
        }
        if (senderAccount.getBalance().subtract(transferRequest.getAmountToSend()).compareTo(BigDecimal.valueOf(500)) <= -1) throw new BankException("insufficient balance",404);

        if(!senderAccount.getPassword().equals(transferRequest.getSenderAccountPassword())){
            throw new BankException("Incorrect details", 404);
        }
            AccountDepositRequest accountDepositRequest = AccountDepositRequest.builder()
                    .accountNo(transferRequest.getReceiverAccountNo())
                    .amount(transferRequest.getAmountToSend())
                    .build();

        AccountWithdrawRequest accountWithdrawRequest = AccountWithdrawRequest.builder()
                                    .accountNo(transferRequest.getSenderAccountNo())
                                    .amount(transferRequest.getAmountToSend())
                                    .password(transferRequest.getSenderAccountPassword())
                                    .build();
        deposit(accountDepositRequest);
        withdraw(accountWithdrawRequest);

        UserTransferResponse userTransferResponse = UserTransferResponse.builder()
                .message("you have successfully transfer " + transferRequest.getAmountToSend()+
                        " to" +transferRequest.getReceiverAccountNo())
                .isSuccessful(true)
                .statusCode(200)
                .build();
        return userTransferResponse;
    }

    @Override
    public AccountLoginResponse accountLogin(AccountRequestLogin requestLogin) {
        Account account = accountRepository.findByAccountId(requestLogin.getAccountId());
        if (!account.getPassword().equals(requestLogin.getPassword())) throw new BankException("incorrect user details", 404);
        account.setAccountNo(requestLogin.getAccountNumber());
        account.setPassword(requestLogin.getPassword());

        AccountLoginResponse loginResponse = AccountLoginResponse.builder()
                .accessToken("Account logged successfully")
                .isSuccess(true)
                .message("welcome back " +accountRepository.findByAccountName(account.getAccountName()))
                .statusCode(201)
                .build();
        return loginResponse;
    }

}
