package ee.swan.api;


import ee.swan.api.domain.Book;
import ee.swan.api.domain.BookCriteria;
import ee.swan.api.service.BookService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin
@RestController
@RequestMapping("books")
public class BooksRestController {
    @Autowired
    BookService bookService;

    @CrossOrigin(maxAge = 900)
    @RequestMapping(path = "{bookId}", method = RequestMethod.GET)
    public BookResource getBook(@PathVariable String bookId) {
        Book book = bookService.find(bookId);
        BookResource resource = new BookResource();
        resource.setBookId(book.getBookId());
        resource.setName(book.getName());
        resource.setPublishedDate(book.getPublishedDate());
        return resource;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createBook(
            @Validated @RequestBody BookResource newResource,
            UriComponentsBuilder uriBuilder) {

        Book newBook = new Book();
        newBook.setName(newResource.getName());
        newBook.setPublishedDate(newResource.getPublishedDate());

        Book createBook = bookService.create(newBook);

//        String resourceUri = "http://localhost:8088/books/" + createBook.getBookId();
//        return ResponseEntity.created(URI.create(resourceUri)).build();
        URI resourceUri = uriBuilder.path("books/{bookId}")
                .buildAndExpand(createBook.getBookId())
                .encode()
                .toUri();
        return ResponseEntity.created(resourceUri).build();
    }

    @RequestMapping(path = "{bookId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(@PathVariable String bookId, @Validated @RequestBody BookResource resource) {
        Book book = new Book();
        book.setBookId(bookId);
        book.setName(resource.getName());
        book.setPublishedDate(resource.getPublishedDate());
        bookService.update(book);
    }

    @RequestMapping(path = "{bookId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String bookId) {
        bookService.delete(bookId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookResource> searchBooks(@Validated BookResourceQuery query) {
        BookCriteria criteria = new BookCriteria();
        criteria.setName(query.getName());
        criteria.setPublishedDate(query.getPublishedDate());

        List<Book> books = bookService.findAllByCriteria(criteria);
        return books.stream().map(book -> {
            BookResource resource = new BookResource();
            resource.setBookId(book.getBookId());
            resource.setName(book.getName());
            resource.setPublishedDate(book.getPublishedDate());
            return resource;
        }).collect(Collectors.toList());
    }

}

