package milan.miljus.eBookRepository2019.service.category;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.model.Category;
import milan.miljus.eBookRepository2019.repository.CategoryRepository;
import milan.miljus.eBookRepository2019.service.category.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by milan.miljus on 2019-07-20 05:04.
 */
@Service
@RequiredArgsConstructor
public class GetCategory {

    private final CategoryRepository categoryRepository;

    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public Category execute(UUID categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }

}
