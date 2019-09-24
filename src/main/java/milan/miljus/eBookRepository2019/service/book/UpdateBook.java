package milan.miljus.eBookRepository2019.service.book;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.search.ElasticIndexing;
import milan.miljus.eBookRepository2019.component.search.value.BookUpdateElastic;
import milan.miljus.eBookRepository2019.model.Book;
import milan.miljus.eBookRepository2019.model.Category;
import milan.miljus.eBookRepository2019.service.book.value.UpdateBookInfo;
import milan.miljus.eBookRepository2019.service.category.GetCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by milan.miljus on 9/23/19 00:40.
 */
@Service
@RequiredArgsConstructor
public class UpdateBook {

    private final GetBook getBook;
    private final GetCategory getCategory;
    private final ElasticIndexing elasticIndexing;

    @Transactional(rollbackFor = Throwable.class, readOnly = false)
    public void execute(final UpdateBookInfo info) {
        final Book book = updateBookInDb(info);
        elasticIndexing.updateBook(new BookUpdateElastic(book));
    }

    private Book updateBookInDb(UpdateBookInfo info) {
        final Book book = getBook.execute(info.getId());
        final Category category = getCategory.execute(info.getCategoryId());
        book.setCategory(category);
        book.setAuthor(info.getAuthor());
        book.setKeywords(info.getKeywords());
        book.setLanguageCode(info.getLanguageIsoCode());
        book.setYear(info.getYear());
        book.setTitle(info.getTitle());
        return book;
    }
}
