package ee.swan.api;


import ee.swan.api.domain.Book;
import ee.swan.api.service.BookService;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BooksRestController {
    @Autowired
    BookService bookService;

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
            @Validated @RequestBody BookResource newResource) {

        Book newBook = new Book();
        newBook.setName(newResource.getName());
        newBook.setPublishedDate(newResource.getPublishedDate());

        Book createBook = bookService.create(newBook);

        String resourceUri = "http://localhost:8088/books/" + createBook.getBookId();
        return ResponseEntity.created(URI.create(resourceUri)).build();
    }



}

