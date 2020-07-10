package ee.swan.service;

import ee.swan.api.domain.Account;
import ee.swan.config.AppConfig;
import ee.swan.config.TestConfig;
import ee.swan.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestConfig.class})
@Sql("/account-delete.sql")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    // 클래스 레벨에 지정한 account-delete.sql이 실행된다.
    // 데이터를 지운 후에 테스트가 실행된다.
    @Test
    public void testCreate() {
        Account account = new Account();
        account.setId("001");
        account.setName("이순신");
        accountRepository.create(account);
    }

    // 메서드에 지정한 account-delete.sql과  account-insert-data.sql이 실행된다.
    // 데이터를 지운 후에 데이터를 등록하고 테스트가 실행된다.
    @Test
    @Sql({"/account-delete.sql", "/account-insert-data.sql"})
    public void testFindOne() {
        Account account = accountRepository.findOne("001");
        System.out.println(account);
        //
    }

}
