package milan.miljus.eBookRepository2019.service.category;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.search.ElasticIndexing;
import milan.miljus.eBookRepository2019.component.search.value.BookUpdateElastic;
import milan.miljus.eBookRepository2019.model.Category;
import milan.miljus.eBookRepository2019.repository.BookRepository;
import milan.miljus.eBookRepository2019.repository.CategoryRepository;
import milan.miljus.eBookRepository2019.service.category.value.UpdateCategoryInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by milan.miljus on 9/24/19 03:12.
 */
@Service
@RequiredArgsConstructor
public class UpdateCategory {

    private final GetCategory getCategory;
    private final CategoryRepository categoryRepository;
    private final ElasticIndexing elasticIndexing;
    private final BookRepository bookRepository;

    @Transactional(rollbackFor = Throwable.class, readOnly = false)
    public void execute(final UpdateCategoryInfo info) {
        final Category category = getCategory.execute(info.getId());
        category.setName(info.getName());

        bookRepository.findByCategoryId(info.getId())
                .forEach(book -> elasticIndexing.updateBook(new BookUpdateElastic(book)));
    }

}
