package com.Joysbright.digiBank;

import com.Joysbright.digiBank.dtos.request.*;
import com.Joysbright.digiBank.dtos.response.*;
import com.Joysbright.digiBank.repository.AccountRepository;
import com.Joysbright.digiBank.service.AccountService;
import com.Joysbright.digiBank.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest

public class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    private AccountRequest createAccountRequest;
    private AccountRequest createAccountRequest1;
    private AccountResponse accountResponse;
    private AccountResponse accountResponse1;
    private AccountDepositRequest accountDepositRequest;
    private AccountWithdrawRequest accountWithdrawRequest;

    private UserTransferRequest userTransferRequest;

    private AccountRequestLogin accountRequestLogin;

    @BeforeEach
    public void setUp(){

        accountService = new AccountServiceImpl(accountRepository);
        createAccountRequest = AccountRequest.builder()
                .accountName("name")
                .password("password")
                .initialDeposit(BigDecimal.valueOf(500))
                .build();

        createAccountRequest1 = AccountRequest.builder()
                .accountName("tomi")
                .password("tomi")
                .initialDeposit(BigDecimal.valueOf(500))
                .build();

        accountResponse = accountService.createAccount(createAccountRequest);
        accountResponse1 = accountService.createAccount(createAccountRequest1);

        accountDepositRequest = AccountDepositRequest.builder()
                .accountNo("10001")
                .amount(BigDecimal.valueOf(1000.00))
                .build();

        accountWithdrawRequest = AccountWithdrawRequest.builder()
                .accountNo("10001")
                .password("password")
                .amount(BigDecimal.valueOf(500.00))
                .build();


        userTransferRequest = UserTransferRequest.builder()
                .amountToSend(BigDecimal.valueOf(500.00))
                .narration("sending money to me")
                .ReceiverAccountNo("10002")
                .senderAccountNo("10001")
                .senderAccountPassword("password")
                .build();

        accountRequestLogin = AccountRequestLogin.builder()
                .accountId("1")
                .accountNumber("10001")
                .password("password")
                .build();

    }

    @Test
    public void accountCanBeCreatedTest(){

//        AccountResponse accountResponse =
//                accountService.createAccount(createAccountRequest);

        assertEquals("name account created successfully", accountResponse.getMessage());

    }


    @Test
    public void accountCanDepositTest(){

        AccountDepositResponse accountDepositResponse =
                accountService.deposit(accountDepositRequest);
        assertEquals("account deposit make successfully", accountDepositResponse.getMessage());
        assertEquals(200, accountDepositResponse.getResponseCode());
        assertEquals(true, accountDepositResponse.isSuccess());
    }


    @Test

    public void accountCanMakeWithdrawalTest(){

        accountService.deposit(accountDepositRequest);
        AccountWithdrawResponse accountWithdrawResponse =
                accountService.withdraw(accountWithdrawRequest);
        assertEquals("you have successfully withdraw 500.0" , accountWithdrawResponse.getMessage());
        assertEquals(200, accountWithdrawResponse.getResponseCode());
        assertTrue(accountWithdrawResponse.isSuccessful());
    }

    @Test
    public void accountCanTransferTest(){

        accountService.deposit(accountDepositRequest);
        UserTransferResponse userTransferResponse =
                accountService.transfer(userTransferRequest);

        assertEquals("you have successfully transfer " + userTransferRequest.getAmountToSend()+
                " to" +userTransferRequest.getReceiverAccountNo(), userTransferResponse.getMessage());
        assertTrue(userTransferResponse.isSuccessful());
        assertEquals(200, userTransferResponse.getStatusCode());

    }

    @Test
    public void accountCanLoginTest(){
        AccountLoginResponse accountLoginResponse =
                accountService.accountLogin(accountRequestLogin);
        assertEquals("Account logged successfully", accountLoginResponse.getAccessToken());
        assertEquals(201, accountLoginResponse.getStatusCode());
    }

    @Test
    public void canFindAccountByAccountNumberTest(){

        accountService.findByAccountNo("10001");
        assertEquals(accountRepository.findByAccountNo("10001"),accountService.findByAccountNo(String.valueOf(10001)));
    }

    @Test
    public void canGetAccountTransactionStatement(){

        accountService.getAccountStatement("10001");
        assertEquals(accountRepository.findByAccountNo("12345"), accountService.findByAccountNo("12345"));
    }
}