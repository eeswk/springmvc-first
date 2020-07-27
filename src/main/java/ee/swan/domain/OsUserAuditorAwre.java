package ee.swan.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;

public class UserAuditorAwre implements AuditorAware<String> {

    @Value("#{ systemProperties['user.name'] }")
    private String userName;

    @Override
    public String getCurrentAuditor() {
        return userName;
    }
}
