package ee.swan.controller;

import java.io.Serializable;

public class EchoForm implements Serializable {
    private static final long serialVersionUID = 7238558783632745800L;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
