package milan.miljus.eBookRepository2019.service.book;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.model.Book;
import milan.miljus.eBookRepository2019.repository.BookRepository;
import milan.miljus.eBookRepository2019.service.book.exception.BookNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by milan.miljus on 2019-07-20 14:52.
 */
@Service
@RequiredArgsConstructor
public class GetBook {

    private final BookRepository bookRepository;

    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public Book execute(UUID bookId) {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }

}
