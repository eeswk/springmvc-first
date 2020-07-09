package ee.swan.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MessageServiceIntegrationAnotherTest {

    @Configuration
    static class LocalContext {
        //빈 정의 생략
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("messages");
            return messageSource;
        }

        @Bean
        public MessageService messageService() {
            MessageService messageService = new MessageService();
            return messageService;
        }


    }
    @Autowired
    MessageService messageService;

    @Test
    public void testGetMessageByCode() {
        String actualMessage = messageService.getMessageByCode("greeting");
        assertThat(actualMessage, is("Hello!!"));

    }

}
