package com.example.project3.Service;

import com.example.project3.API.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
   private final AccountRepository accountRepository;
   private final CustomerRepository customerRepository ;

    public void createAccount(Account account){
        accountRepository.save(account);
    }

    public void activateAccount(Integer accountId){
        Account a = accountRepository.findAccountById(accountId);
        a.setActive(true);
        accountRepository.save(a);
    }

    public Account viewAccountDetails(Integer accountId){
         Account a =  accountRepository.findAccountById(accountId);
         if(a==null){
             throw new ApiException("No account found with entered number");
         }
         return a;
    }

    public List<Account> getUserAccounts(Integer userId){
        Customer c = customerRepository.findCustomerById(userId);
        if(c==null){
            throw new ApiException("No Customer found with entered Id");
        }
       return accountRepository.findByCustomer(c);
    }

    public Account depositMoney(Integer accountId , Integer amount){
        Account a = accountRepository.findAccountById(accountId);
        double newBalance= a.getBalance() + amount;
        a.setBalance(newBalance);
        accountRepository.save(a);
        return a;
    }

    public Account withdrawMoney(Integer accountId , Integer amount){
        Account a = accountRepository.findAccountById(accountId);
        double newBalance= a.getBalance() - amount;
        if(a.getBalance()<amount){
            throw new ApiException("Insufficient Funds");
        }
        a.setBalance(newBalance);
        accountRepository.save(a);
        return a;
    }

    public void transferFunds(Integer senderAccountId , Integer receiverAccountId, Integer amount){
        Account sender = accountRepository.findAccountById(senderAccountId);
        Account receiver = accountRepository.findAccountById(receiverAccountId);
        double senderBalance= sender.getBalance() - amount;
        if(sender.getBalance()<amount){
            throw new ApiException("Insufficient Funds");
        }
        double receiverBalance= receiver.getBalance() + amount;

        sender.setBalance(senderBalance);
        receiver.setBalance(receiverBalance);
        accountRepository.save(sender);
        accountRepository.save(receiver);
    }

    public Account blockAccount(Integer accountId){
        Account a = accountRepository.findAccountById(accountId);
        if(a==null){
            throw new ApiException("No account found with entered number");
        }
        a.setActive(false);
        accountRepository.save(a);
        return a;
    }
}
