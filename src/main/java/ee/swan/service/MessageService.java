package ee.swan.service;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageSource messageSource;

    public String getMessage() {
        return "Hello!";
    }

    public String getMessageByCode(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }
}
