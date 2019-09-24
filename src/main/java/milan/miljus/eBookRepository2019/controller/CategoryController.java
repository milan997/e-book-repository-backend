package milan.miljus.eBookRepository2019.controller;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.controller.value.category.CategoryResponse;
import milan.miljus.eBookRepository2019.controller.value.category.UpdateCategoryRequest;
import milan.miljus.eBookRepository2019.model.Category;
import milan.miljus.eBookRepository2019.service.category.DeleteCategory;
import milan.miljus.eBookRepository2019.service.category.GetCategories;
import milan.miljus.eBookRepository2019.service.category.UpdateCategory;
import milan.miljus.eBookRepository2019.service.category.value.UpdateCategoryInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by milan.miljus on 9/20/19 16:08.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final GetCategories getCategories;
    private final UpdateCategory updateCategory;
    private final DeleteCategory deleteCategory;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        final List<Category> categories = getCategories.allOrderByName();
        final List<CategoryResponse> response = categories.stream().map(CategoryResponse::new).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> updateCategory(@PathVariable final long categoryId,
                                               @RequestBody @Valid final UpdateCategoryRequest request) {
        updateCategory.execute(new UpdateCategoryInfo(request, categoryId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable final long categoryId) {
        deleteCategory.execute(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
