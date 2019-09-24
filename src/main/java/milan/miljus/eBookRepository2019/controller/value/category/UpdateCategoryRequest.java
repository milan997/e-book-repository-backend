package milan.miljus.eBookRepository2019.controller.value.category;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * Created by milan.miljus on 9/24/19 03:22.
 */
@Getter
public class UpdateCategoryRequest {

    @NotBlank
    private String name;

}
