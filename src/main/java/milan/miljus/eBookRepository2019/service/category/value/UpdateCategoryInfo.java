package milan.miljus.eBookRepository2019.service.category.value;

import lombok.Getter;
import milan.miljus.eBookRepository2019.controller.value.category.UpdateCategoryRequest;

/**
 * Created by milan.miljus on 9/24/19 03:13.
 */
@Getter
public class UpdateCategoryInfo {

    @Getter
    private long id;

    @Getter
    private String name;

    public UpdateCategoryInfo(final UpdateCategoryRequest request, final long id) {
        this.id = id;
        this.name = request.getName();
    }

}
