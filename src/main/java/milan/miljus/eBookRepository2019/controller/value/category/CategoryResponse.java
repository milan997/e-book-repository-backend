package milan.miljus.eBookRepository2019.controller.value.category;

import lombok.Getter;
import milan.miljus.eBookRepository2019.model.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * Created by milan.miljus on 9/20/19 16:09.
 */
@Getter
public class CategoryResponse {

    @Positive
    private long id;

    @NotBlank
    private String name;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

}
