package milan.miljus.eBookRepository2019.controller;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.search.ElasticIndexing;
import milan.miljus.eBookRepository2019.component.search.ElasticSearching;
import milan.miljus.eBookRepository2019.component.search.value.BookSearchInfo;
import milan.miljus.eBookRepository2019.component.search.value.BookSearchResponse;
import milan.miljus.eBookRepository2019.service.book.IndexAllBooks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by milan.miljus on 9/19/19 19:52.
 */
@RestController
@RequestMapping("/elastic/books")
@RequiredArgsConstructor
public class ElasticBooksController {

    private final ElasticIndexing elasticIndexing;
    private final ElasticSearching elasticSearching;
    private final IndexAllBooks indexAllBooks;

    @GetMapping("/search")
    public ResponseEntity<BookSearchResponse> searchBook(
                    @RequestParam(required = false) final String searchTerm,
                    @RequestParam(required = false) final List<Long> categories) {
        final BookSearchInfo bookSearchInfo = new BookSearchInfo(searchTerm, categories);
        final BookSearchResponse response = elasticSearching.search(bookSearchInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllFromIndex() {
        elasticIndexing.clearIndex();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/index-all")
    public ResponseEntity<Void> indexAllFromDisk() {
        indexAllBooks.execute();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
