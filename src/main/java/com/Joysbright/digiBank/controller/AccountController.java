package com.Joysbright.digiBank.controller;

import com.Joysbright.digiBank.dtos.request.*;
import com.Joysbright.digiBank.dtos.response.*;
import com.Joysbright.digiBank.model.Account;
import com.Joysbright.digiBank.model.Transaction;
import com.Joysbright.digiBank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNo}")
    public ResponseEntity<?> findAccount(@PathVariable("accountNo") String accountNo, @RequestBody AccountQuerryRequest accountQuerryRequest) {
        AccountQuerryResponse accountQuerryResponse = accountService.querryAccount(accountNo, accountQuerryRequest);
        return new ResponseEntity<>(accountQuerryResponse, HttpStatus.OK);
    }

    @GetMapping("/{accountNo}")
    public ResponseEntity<?> getAccountStatement(@PathVariable("accountNo") String accountNo) {
        List<Transaction> transactionList = accountService.getAccountStatement(accountNo);
        return new ResponseEntity<>(accountNo, HttpStatus.FOUND);
    }

    @PostMapping("/{deposit}")
    public ResponseEntity<?> accountDeposit( @PathVariable("deposit") @RequestBody AccountDepositRequest depositRequest) {
        AccountDepositResponse depositResponse = accountService.deposit(depositRequest);
        return  new ResponseEntity<>(depositResponse, HttpStatus.CREATED);
    }

    @PostMapping("/{withdraw}")
    public ResponseEntity<?> accountWithdraw(@PathVariable("withdraw") @RequestBody AccountWithdrawRequest withdrawRequest){
        AccountWithdrawResponse withdrawResponse = accountService.withdraw(withdrawRequest);

        return new ResponseEntity<>(withdrawResponse, HttpStatus.CREATED);
    }

    @PostMapping("/{createAccount}")
    public ResponseEntity<?> createUserAccount( @PathVariable("createAccount") AccountRequest createAccount){

        AccountResponse accountResponse = accountService.createUser(createAccount);
        return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{findByAccountNo}")
    public ResponseEntity<?> findByAccountNo(@PathVariable("findByAccountNo") String accountNo){
        Account accounts = accountService.findByAccountNo(accountNo);
        return new ResponseEntity<>(accounts, HttpStatus.FOUND);
    }

@PostMapping("/{accountLogin}")
    public ResponseEntity<?> accountLogin(@PathVariable("accountLogin") @RequestBody AccountRequestLogin requestLogin){

    AccountLoginResponse loginResponse = accountService.accountLogin(requestLogin);
    return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);



    
    }

}
