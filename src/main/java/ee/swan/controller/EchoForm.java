package ee.swan.controller;

import java.io.Serializable;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class EchoForm implements Serializable {
    private static final long serialVersionUID = 7238558783632745800L;
    @NotEmpty
    @Size(max = 10)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
