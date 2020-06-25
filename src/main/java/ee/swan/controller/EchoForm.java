package ee.swan.controller;

import ee.swan.validation.AlphaNumeric;
import ee.swan.validation.EqualsPropertyValues;
import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@EqualsPropertyValues(property = "password", comparingProperty = "reEnteredPassword", message = "서로 입력값이 다릅니다.")
public class EchoForm implements Serializable {
    private static final long serialVersionUID = 7238558783632745800L;
    @NotEmpty
    @Size(max = 10)
    private String text;

    @NotNull
    @AlphaNumeric(message = "영문숫자조합입니다.")
    private String password;

    private String reEnteredPassword;
    @AlphaNumeric(message = "영문숫자조합입니다.")
    private String alphaNum;

    private List<String> roles;

    @Valid
    private CardForm card;

    private List<CardForm> cards;

    public CardForm getCard() {
        return card;
    }

    public void setCard(CardForm card) {
        this.card = card;
    }

    public List<CardForm> getCards() {
        return cards;
    }

    public void setCards(List<CardForm> cards) {
        this.cards = cards;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAlphaNum() {
        return alphaNum;
    }

    public void setAlphaNum(String alphaNum) {
        this.alphaNum = alphaNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReEnteredPassword() {
        return reEnteredPassword;
    }

    public void setReEnteredPassword(String reEnteredPassword) {
        this.reEnteredPassword = reEnteredPassword;
    }
}
