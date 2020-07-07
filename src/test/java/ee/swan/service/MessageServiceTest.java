package ee.swan.service;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MessageServiceTest {

    @Test
    public void testGetMessage() {
        MessageService service = new MessageService();
        String actualMessage = service.getMessage();
        assertThat(actualMessage, is("Hello!"));
    }

}
