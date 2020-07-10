package ee.swan.repository;

import ee.swan.api.domain.Account;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class AccountRepository{
    private static final Map<String, Account> accounts = new HashMap<>();

    public void create(Account account) {
        if (accounts.containsKey(account.getId())) {
            accounts.put(account.getId(), account);
        }
    }

    public Account findOne(String id) {
        if (accounts.containsKey(id)) {
            return accounts.get(id);
        }
        return null;
    }
}
