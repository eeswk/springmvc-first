package ee.swan.service;

import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DefaultContext.class, DevContext.class})
@ActiveProfiles("dev")
public class AccountServiceIntegrationTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void testDataSource() {
        System.out.println("dataSource: " + dataSource);
    }
}
