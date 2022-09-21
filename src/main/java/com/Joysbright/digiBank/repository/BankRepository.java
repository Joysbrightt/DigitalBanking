package com.Joysbright.digiBank.repository;

import com.Joysbright.digiBank.model.Account;
import com.Joysbright.digiBank.model.User;
import org.springframework.stereotype.Repository;


public interface BankRepository {
     User save(User user);
     User findUserByAccountNo(String accountNo);

     User findUserByAccountName(String accountName);

     User addUser(User user);

     User deleteUser(User user);
}
