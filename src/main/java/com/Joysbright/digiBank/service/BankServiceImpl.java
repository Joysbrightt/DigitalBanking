package com.Joysbright.digiBank.service;

import com.Joysbright.digiBank.dtos.request.*;
import com.Joysbright.digiBank.dtos.response.RegisterUserResponse;
import com.Joysbright.digiBank.dtos.response.UserLoginResponse;
import com.Joysbright.digiBank.dtos.response.UserTransferResponse;
import com.Joysbright.digiBank.exceptionClass.BankException;
import com.Joysbright.digiBank.model.Account;
import com.Joysbright.digiBank.model.User;
import com.Joysbright.digiBank.repository.BankRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Getter
@Service
public class BankServiceImpl implements BankService{
    @Autowired
    private BankRepository bankRepository;
    private AccountService accountService;

    public BankServiceImpl() {
    }

    public BankServiceImpl(BankRepository bankRepository, AccountService accountService) {
        this.bankRepository = bankRepository;
        this.accountService = accountService;
    }

    private List<User> userList;
    private int userId;
    @Override
    public RegisterUserResponse register(RegisterUserRequest userRequest) {
        User user = new User();
        if ((user.getFirstName() +" " +user.getLastName()).equals(userRequest.getFirstName() +" " +userRequest.getLastName())){
            throw new BankException("account already exist", 400);}
        user.setUserId(userId);
        user.setEmail(userRequest.getEmail());
        user.setPhoneNo(userRequest.getPhoneNo());
        user.setFirstName(userRequest.getFirstName());
        AccountRequest accountRequest = AccountRequest.builder()
                .accountName(userRequest.getFirstName() + " " + userRequest.getLastName())
                .password(userRequest.getPassword())
                .initialDeposit(userRequest.getInitialDeposit())
                .build();
        accountService.createUser(accountRequest);
         Account account = accountService.findAccountByAccountName(accountRequest.getAccountName());
                user.setAccount(account);
        User saved = bankRepository.save(user);

        RegisterUserResponse userResponse = RegisterUserResponse.builder()
                .message(saved.getFirstName()+ " " +saved.getLastName() + "Successfully registered")
                .build();
        return userResponse;
    }
    @Override
    public UserTransferResponse transfer(UserTransferRequest transferRequest) {
        UserTransferResponse transferResponse = accountService.transfer(transferRequest);
        return transferResponse;




//
//                .build();
//        accountService.withdraw(accountWithdrawRequest);
//        Account senderAccount = accountService.findByAccountNo(transferRequest.getSenderAccountNo());
//
//        AccountDepositRequest accountDepositRequest = AccountDepositRequest.builder().build();
//        accountService.deposit(accountDepositRequest);
//        Account receiverAccount = accountService.findByAccountNo(transferRequest.getReceiverAccountNo());
//
//        if(senderAccount.getBalance().compareTo(BigDecimal.valueOf(account.getMinimumBalance())));
    }

    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest) {
        User user = User.builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();
        if (!user.getEmail().equals(loginRequest.getEmail()))
            throw new BankException("incorrect email or password", 401);

        if (!user.getPassword().equals(loginRequest.getPassword()))
            throw new BankException("incorrect login details", 404);

        UserLoginResponse userLoginResponse = UserLoginResponse.builder()
                .message("logged in successfully")
                .account(Account.builder().build())
                .build();

        return userLoginResponse;
    }

}
