package ee.swan.service;

import ee.swan.api.domain.Account;
import ee.swan.config.AppConfig;
import ee.swan.config.TestConfig;
import ee.swan.config.TransactionManagerConfig;
import ee.swan.repository.AccountRepository;
import java.lang.annotation.Target;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestConfig.class, TransactionManagerConfig.class})
@Sql("/account-delete.sql")
public class AccountRepositoryTest {

    @Autowired
    @Qualifier("jdbcTemplateForAssertion")
    JdbcTemplate jdbcTemplate;

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

        //JdbcTemplate을 이용해 등록한 레코드를 다시 데이터베이스에서 취득
        Map<String, Object> createdAccount = jdbcTemplate.queryForMap("SELECT id, name FROM account WHERE id ='001'");
        System.out.println("selected=>" + createdAccount.get("id"));
        assertThat(createdAccount.get("id"), is("001"));
        assertThat(createdAccount.get("name"), is("이순신"));


    }

    @Test
    public void testFind() {
        Account account = accountRepository.findOne("001");
        System.out.println("selected=>" + account.getId());
    }


    // 메서드에 지정한 account-delete.sql과  account-insert-data.sql이 실행된다.
    // 데이터를 지운 후에 데이터를 등록하고 테스트가 실행된다.
    @Test
    @Sql({"/account-delete.sql", "/account-insert-data.sql"})
    public void testFindOne() {
        Account account = accountRepository.findOne("001");
        System.out.println("selected=>" + account.getId());
        //
    }

}
