package safe_pay.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import safe_pay.exceptions.InvalidIdException;
import safe_pay.models.Account;

import java.math.BigDecimal;
import java.util.Optional;

public class AccountRepositoryTest {

    AccountRepository accountRepository;
    Optional<Account> savedAccount;

    @BeforeEach
    public void setUp() {
        accountRepository  = new AccountRepository();

    }

    @Test
    public void saveAccountTest(){
        Account account = new Account("sunday", new BigDecimal(20000));
        savedAccount = Optional.ofNullable(accountRepository.save(account));
        Assertions.assertNotNull(savedAccount);
    }

    @Test
    public void findAccountTest() {
        try {
            Assertions.assertEquals(savedAccount, accountRepository.findById(1));
        }catch (InvalidIdException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteAccountTest(){
        try {
            accountRepository.deleteAccount(1);
        } catch (InvalidIdException e) {
            System.out.println(e.getMessage());
        }

    }
}
