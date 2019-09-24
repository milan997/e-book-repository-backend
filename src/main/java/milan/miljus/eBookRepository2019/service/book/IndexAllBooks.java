package milan.miljus.eBookRepository2019.service.book;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.pdf.PdfDissection;
import milan.miljus.eBookRepository2019.component.search.ElasticIndexing;
import milan.miljus.eBookRepository2019.component.search.value.BookElastic;
import milan.miljus.eBookRepository2019.model.Book;
import milan.miljus.eBookRepository2019.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by milan.miljus on 9/21/19 13:30.
 */
@Service
@RequiredArgsConstructor
public class IndexAllBooks {

    private final ElasticIndexing elasticIndexing;
    private final BookRepository bookRepository;
    private final PdfDissection pdfDissection;

    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public void execute() {
        final List<Book> books = bookRepository.findAll();

        final List<BookElastic> bookElastics = books.stream().map(book -> {
            final String text = pdfDissection.extractText(book.getFileKey());
            return new BookElastic(book, text);
        }).collect(Collectors.toList());

        elasticIndexing.indexAll(bookElastics);
    }
}
