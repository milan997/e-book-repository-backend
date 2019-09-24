package milan.miljus.eBookRepository2019.service.category;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.model.Category;
import milan.miljus.eBookRepository2019.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by milan.miljus on 9/20/19 16:11.
 */
@Service
@RequiredArgsConstructor
public class GetCategories {

    private final CategoryRepository categoryRepository;

    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public List<Category> allOrderByName() {
        return categoryRepository.findAllByOrderByName();
    }
}
