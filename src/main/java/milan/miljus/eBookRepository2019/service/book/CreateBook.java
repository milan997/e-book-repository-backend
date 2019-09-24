package milan.miljus.eBookRepository2019.service.book;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.pdf.PdfDissection;
import milan.miljus.eBookRepository2019.component.search.ElasticIndexing;
import milan.miljus.eBookRepository2019.component.search.value.BookElastic;
import milan.miljus.eBookRepository2019.model.Admin;
import milan.miljus.eBookRepository2019.model.Book;
import milan.miljus.eBookRepository2019.model.Category;
import milan.miljus.eBookRepository2019.repository.BookRepository;
import milan.miljus.eBookRepository2019.service.book.value.CreateBookInfo;
import milan.miljus.eBookRepository2019.service.category.GetCategory;
import milan.miljus.eBookRepository2019.service.user.GetAdmin;
import milan.miljus.eBookRepository2019.util.Constants;
import milan.miljus.eBookRepository2019.util.languages.LanguageUtil;
import milan.miljus.eBookRepository2019.util.languages.exception.IsoCodeDoesNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by milan.miljus on 2019-07-20 16:51.
 */
@Service
@RequiredArgsConstructor
public class CreateBook {

    private final GetCategory getCategory;
    private final BookRepository bookRepository;
    private final PdfDissection pdfDissection;
    private final ElasticIndexing elasticIndexing;
    private final GetAdmin getAdmin;

    @Transactional(rollbackFor = Throwable.class, readOnly = false)
    public long execute(final CreateBookInfo info) {
        check(info);
        
        final Category category = getCategory.execute(info.getCategoryId());
        final Admin owner = getAdmin.byId(info.getOwnerId());

        final String text = pdfDissection.extractText(info.getFileKey());
        final int pageCount = pdfDissection.extractMeta(info.getFileKey()).getPageCount();

        final Book book = bookRepository.save(Book.builder()
                .languageCode(info.getLanguageIsoCode())
                .year(info.getYear())
                .author(info.getAuthor())
                .title(info.getTitle())
                .category(category)
                .keywords(info.getKeywords())
                .image(Constants.DEFAULT_BOOK_IMAGE)
                .fileKey(info.getFileKey())
                .pageCount(pageCount)
                .owner(owner)
                .build());

        // save to disk
        bookRepository.save(book);

        // index
        elasticIndexing.index(new BookElastic(book, text));

        return book.getId();
    }

    private void check(final CreateBookInfo info) {
        if (!LanguageUtil.isoCodeExists(info.getLanguageIsoCode())) {
            throw new IsoCodeDoesNotExistException();
        }
    }

}
