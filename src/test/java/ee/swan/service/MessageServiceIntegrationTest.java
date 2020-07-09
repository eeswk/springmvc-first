package ee.swan.service;

import ee.swan.config.AppConfig;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class MessageServiceIntegrationTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public static final SpringMethodRule SPRING_METHOD_RULE = new SpringMethodRule();

    @Autowired
    MessageService messageService;

    @Test
    public void testGetMessageByCode() {
        String actualMessage = messageService.getMessageByCode("greeting");
        assertThat(actualMessage, is("Hello!!"));

    }

}
