package ee.swan.controller;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class CardForm implements Serializable {
    private static final long serialVersionUID = -509221012454079965L;

    @NotNull
    @NotEmpty
    @Size(min = 5, max=13)
    private String no;

    @Past
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date validMonth;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getValidMonth() {
        return validMonth;
    }

    public void setValidMonth(Date validMonth) {
        this.validMonth = validMonth;
    }
}
