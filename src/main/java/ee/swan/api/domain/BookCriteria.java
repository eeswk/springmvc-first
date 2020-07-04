package ee.swan.api.domain;

import java.time.LocalDate;

public class BookCriteria {
    private String name;
    private LocalDate publishedDate;

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
