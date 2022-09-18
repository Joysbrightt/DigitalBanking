package com.Joysbright.digiBank.repository;

import com.Joysbright.digiBank.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    List<Account> accountList = new ArrayList<>();

    @Override
    public Account findByAccountName(String name) {
        for (int i = 0; i < accountList.size(); i++) {
            if(accountList.get(i).getAccountName().equals(name)){
                return accountList.get(i);
            }
        }
        return null;
    }

    @Override
    public Account findByAccountId(String accountId) {
        for (int i = 0; i < accountList.size(); i++) {
            if(accountList.get(i).getAccountId().equals(accountId)){
                return accountList.get(i);
            }
        }
        return null;
    }


    public  Account save(Account account) {
        accountList.add(account);
        return account;
    }

    @Override
    public int size() {
        return accountList.size();
    }

    @Override
    public Account findByAccountNo(String accountNo) {
        for (int i = 0; i < accountList.size() ; i++) {
            if (accountList.get(i).getAccountNo().equals(accountNo))
              return accountList.get(i);
        }
        return null;
    }
}
