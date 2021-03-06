package ee.swan.controller;

import java.io.Serializable;

public class AccountCreateForm implements Serializable {
    private static final long serialVersionUID = -1435459210001857209L;

    private String type;
    private String cardNo;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
