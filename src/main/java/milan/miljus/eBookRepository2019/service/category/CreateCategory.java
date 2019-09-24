package milan.miljus.eBookRepository2019.service.category;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.model.Category;
import milan.miljus.eBookRepository2019.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by milan.miljus on 9/22/19 02:17.
 */
@Service
@RequiredArgsConstructor
public class CreateCategory {

    private final CategoryRepository categoryRepository;

    @Transactional(rollbackFor = Throwable.class, readOnly = false)
    public long execute(final String name) {
        return categoryRepository.save(new Category(name)).getId();
    }

}
