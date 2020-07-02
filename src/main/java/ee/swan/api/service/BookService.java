package ee.swan.api.service;

import ee.swan.api.domain.Book;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final Map<String, Book> bookRepository = new ConcurrentHashMap<>();

    @PostConstruct
    public void loadDummyData() {
        Book book = new Book();
        book.setBookId("9791158390747");
        book.setName("슬랙으로 협업하기");
        book.setPublishedDate(LocalDate.of(2020,7,3));
        bookRepository.put(book.getBookId(), book);
    }


    public Book find(String bookId) {
        Book book =  bookRepository.get(bookId);
        return book;
    }

    public Book create(Book book) {
        String bookId = UUID.randomUUID().toString();
        book.setBookId(bookId);
        bookRepository.put(bookId, book);
        return book;
    }
}
