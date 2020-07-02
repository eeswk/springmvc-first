package ee.swan.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;

public class BookResource implements Serializable {
    private static final long serialVersionUID = 1951547807513378639L;

    private String bookId;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd") // ISO 8061 확장 형식
    private LocalDate publishedDate;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }
}
