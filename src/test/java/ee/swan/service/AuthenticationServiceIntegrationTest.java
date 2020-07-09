package ee.swan.service;

import ee.swan.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@TestPropertySource(properties="auth.failureCountToLock=3")
public class AuthenticationServiceIntegrationTest {

    @Autowired
    AuthenticationService authenticationService;

    @Test
    public void testAuthenticationFailCount() {
        System.out.println("auth.failureCountToLock = " + authenticationService.getFailureCountToLock());
    }
}
