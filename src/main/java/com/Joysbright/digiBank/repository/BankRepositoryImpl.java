package com.Joysbright.digiBank.repository;

import com.Joysbright.digiBank.exceptionClass.BankException;
import com.Joysbright.digiBank.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class BankRepositoryImpl implements BankRepository {

    private List<User> bankUsers = new ArrayList<>();
    private int size;
    @Override
    public User save(User user) {
        bankUsers.add(user);
        return user;
    }

    @Override
    public User findUserByAccountNo(String accountNo) {
        for (int i = 0; i <bankUsers.size(); i++) {
           if(bankUsers.get(i).getAccount().getAccountNo().equals(accountNo)){
               return bankUsers.get(i);
           }
        }
        throw new BankException("User not found!", 404);
    }

    @Override
    public User findUserByAccountName(String accountName) {
        for (int i = 0; i <bankUsers.size() ; i++) {
            if (bankUsers.get(i).getAccount().getAccountName().equals(accountName)) {
                return bankUsers.get(i);
            }
        }
        throw new BankException("User not found!", 404);
    }



    @Override
    public User addUser(User user) {
        for (int i = 0; i < bankUsers.size() ; i++) {
            if (!bankUsers.get(i).equals(user))
              return bankUsers.get(i);
        }
        size++;
        return null;

    }

    @Override
    public User deleteUser(User user) {
        for (int i = 0; i < bankUsers.size(); i++) {
            if (bankUsers.get(i).equals(user));
            return bankUsers.remove(i);
        }
        size--;
        throw new BankException("User not found!", 404);
    }

    public int getSize() {
        return size;
    }
}
