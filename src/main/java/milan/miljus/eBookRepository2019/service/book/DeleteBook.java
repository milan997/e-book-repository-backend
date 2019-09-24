package milan.miljus.eBookRepository2019.service.book;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.search.ElasticIndexing;
import milan.miljus.eBookRepository2019.model.Book;
import milan.miljus.eBookRepository2019.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by milan.miljus on 9/22/19 23:13.
 */
@Service
@RequiredArgsConstructor
public class DeleteBook {

    private final BookRepository bookRepository;
    private final ElasticIndexing elasticIndexing;

    @Transactional(rollbackFor = Throwable.class, readOnly = false)
    public void execute(long bookId) {
        bookRepository.findById(bookId).ifPresent((Book book) -> {
            book.delete();
            elasticIndexing.deleteBook(String.valueOf(bookId));
        });
    }

}
