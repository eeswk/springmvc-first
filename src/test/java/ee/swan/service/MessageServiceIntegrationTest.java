package ee.swan.service;

import ee.swan.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class MessageServiceIntegrationTest {

    @Autowired
    MessageService messageService;

    @Test
    public void testGetMessageByCode() {
        String actualMessage = messageService.getMessageByCode("greeting");
        assertThat(actualMessage, is("Hello!!"));

    }

}
