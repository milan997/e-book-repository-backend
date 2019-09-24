package milan.miljus.eBookRepository2019.service.category;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.search.ElasticIndexing;
import milan.miljus.eBookRepository2019.model.Book;
import milan.miljus.eBookRepository2019.model.Category;
import milan.miljus.eBookRepository2019.repository.BookRepository;
import milan.miljus.eBookRepository2019.repository.CategoryRepository;
import milan.miljus.eBookRepository2019.service.book.IndexAllBooks;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by milan.miljus on 9/24/19 02:48.
 */
@Service
@RequiredArgsConstructor
public class DeleteCategory {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final IndexAllBooks indexAllBooks;
    private final ElasticIndexing elasticIndexing;

    @Transactional(rollbackFor = Throwable.class, readOnly = false)
    public void execute(final long categoryId) {
        categoryRepository.findById(categoryId).ifPresent(category -> {
            category.delete();
            deleteBooksOnElastic(category);
        });
    }

    private void deleteBooksOnElastic(final Category category) {
        final List<Book> allByCategoryId = bookRepository.findByCategoryId(category.getId());
        bookRepository.findByCategoryId(category.getId())
                .forEach(book -> {
                        elasticIndexing.deleteBook(String.valueOf(book.getId()));
                    }
                );
    }

}
