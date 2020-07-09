package ee.swan.service;

import java.util.Locale;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @InjectMocks
    MessageService service;

    @Mock
    MessageSource mockMessageSource;

    @Test
    public void testGetMessage() {
        MessageService service = new MessageService();
        String actualMessage = service.getMessage();
        assertThat(actualMessage, is("Hello!"));
    }

    @Test
    public void testGetMessageByCode() {
        doReturn("Hello!").when(mockMessageSource)
                .getMessage("greeting", null, Locale.getDefault());
        //테스트를 한다.
        String actualMessage = service.getMessageByCode("greeting");
        assertThat(actualMessage, is("Hello!"));
    }

    @Test
    public void testGetMessageByCode2() {
        given(service.getMessageByCode("greeting")).willReturn("Hello!");

        String result = service.getMessageByCode("greeting");
        assertThat(result, is("Hello!"));
    }

}
