package com.Joysbright.digiBank.repository;

import com.Joysbright.digiBank.model.Account;
import org.springframework.stereotype.Repository;


public interface AccountRepository {

    Account findByAccountName(String name);
    Account findByAccountId(String accountId);
    Account save(Account account);
    Account findByAccountPassword(Account password);
    int size();
    Account findByAccountNo(String accountNo);

}
