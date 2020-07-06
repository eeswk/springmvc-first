package ee.swan.api.controller;

import ee.swan.api.BookResource;
import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class BookRestTemplateController {

    @Autowired
    RestOperations restOperations;

    @RequestMapping("test/books")
    public void books(){
        /*
        String resource = restOperations.getForObject(
                "http://localhost:8088/books/9791158390747",
                String.class);

        System.out.println(resource);
         */

        BookResource bookResource = restOperations.getForObject(
                "http://localhost:8088/books/9791158390747",
                BookResource.class);

        System.out.println(bookResource.getName());

    }

    @RequestMapping("test/books/create")
    public void createdBook(){
        BookResource resource = new BookResource();
        resource.setName("러닝!");
        resource.setPublishedDate(LocalDate.of(2020, 7, 6));

        URI createdResourceUri = restOperations.postForLocation(
                "http://localhost:8088/books",resource);

        System.out.println(createdResourceUri);
    }

    @RequestMapping("test/books/headCreate")
    public void headWithCreateBook() {
        BookResource resource = new BookResource();
        resource.setName("러닝!");
        resource.setPublishedDate(LocalDate.of(2020, 7, 6));

        RequestEntity<BookResource> requestEntity = RequestEntity
                .post(URI.create("http://localhost:8088/books"))
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Track-Id", UUID.randomUUID().toString())
                .body(resource);

        //ResponseEntity<Void> responseEntity = restOperations.exchange(requestEntity, Void.class);
        ResponseEntity<Void> responseEntity = restOperations.postForEntity("http://localhost:8088/books", resource, Void.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println(httpStatus.value());
        responseHeaders.entrySet().stream().forEach(e -> {
            System.out.println("key: " + e.getKey()+ ", value: " + e.getValue());
        });

    }


    @RequestMapping("test/books/uriTemplate")
    public void uriTemplate() {
        String bookid = "9791158390747";

        BookResource bookResource = restOperations.getForObject(
                "http://localhost:8088/books/{bookId}",
                BookResource.class, bookid
        );

        RequestEntity<Void> requestEntity = RequestEntity
                .get(UriComponentsBuilder
                        .fromUriString("http://localhost:8088/books/{bookId}")
                        .buildAndExpand(bookid)
                        .encode()
                        .toUri())
                .header("X-Track-Id", UUID.randomUUID().toString()).build();


    }

}
