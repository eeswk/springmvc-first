package ee.swan.api.service;

import ee.swan.api.domain.Book;
import ee.swan.api.domain.BookCriteria;
import ee.swan.exception.BookResourceNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
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
        if (book == null) {
            throw new BookResourceNotFoundException(bookId);
        }
        return book;
    }

    public Book create(Book book) {
        String bookId = UUID.randomUUID().toString();
        book.setBookId(bookId);
        bookRepository.put(bookId, book);
        return book;
    }

    public Book update(Book book) {
        return bookRepository.put(book.getBookId(), book); //Map 갱신
    }

    public Book delete(String bookId) {
        return bookRepository.remove(bookId);
    }

    public List<Book> findAllByCriteria(BookCriteria criteria) {
        return bookRepository.values().stream().filter(book ->
                    (criteria.getName() == null
                        || book.getName().contains(criteria.getName())) &&
                    (criteria.getPublishedDate() == null
                         || book.getPublishedDate().equals(criteria.getPublishedDate())))
                    .sorted((o1, o2) -> o1.getPublishedDate().compareTo(o2.getPublishedDate()))
                    .collect(Collectors.toList());
    }
}
