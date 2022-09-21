package com.Joysbright.digiBank.repository;

import com.Joysbright.digiBank.model.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
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
    public Account findByAccountPassword(Account password) {

        for (int i = 0; i <accountList.size() ; i++) {
            accountList.add(password);
            ;
        }
        return null;
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
