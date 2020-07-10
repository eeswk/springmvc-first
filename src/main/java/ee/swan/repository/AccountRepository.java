package ee.swan.repository;

import ee.swan.api.domain.Account;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class AccountRepository{
    private static final Map<String, Account> accounts = new HashMap<>();

    @Autowired
    JdbcTemplate jdbcTemplate;


    public void create(Account account) {
        String sql = "INSERT INTO account(id, name) values ( ? , ?)";
        jdbcTemplate.update(sql, account.getId(), account.getName());
/*
        if (accounts.containsKey(account.getId())) {
            accounts.put(account.getId(), account);
        }
 */
    }

    public Account findOne(String id) {
        String sql = "SELECT id, name FROM account WHERE id = ?";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
        Account account = new Account();
        account.setId((String) result.get("id"));
        account.setName((String) result.get("name"));
        return account;

    }
}
