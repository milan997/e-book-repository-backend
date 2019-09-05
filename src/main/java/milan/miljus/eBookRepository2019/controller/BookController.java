package milan.miljus.eBookRepository2019.controller;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.controller.value.IdWrapper;
import milan.miljus.eBookRepository2019.controller.value.book.CreateBookRequest;
import milan.miljus.eBookRepository2019.service.book.CreateBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static milan.miljus.eBookRepository2019.converter.BookConverter.toCreateBookInfo;

/**
 * Created by milan.miljus on 2019-07-08 01:14.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final CreateBook createBook;

    @PostMapping
    public ResponseEntity<IdWrapper> createBookEntity(@RequestBody final CreateBookRequest request) {
        final UUID bookId = createBook.execute(toCreateBookInfo(request));
        return new ResponseEntity<>(IdWrapper.of(bookId), HttpStatus.CREATED);
    }

}
