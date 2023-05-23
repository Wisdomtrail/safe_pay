package safe_pay.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import safe_pay.models.Account;

import java.math.BigDecimal;

public class AccountRepositoryTest {

    AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
       accountRepository  = new AccountRepository();
    }


    @Test
    public void saveAccountTest(){
        Account account = new Account("sunday", new BigDecimal(20000));
        Account savedAccount =accountRepository.save(account);
        Assertions.assertNotNull(savedAccount);
    }
}
