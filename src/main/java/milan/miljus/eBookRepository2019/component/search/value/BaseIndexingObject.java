package milan.miljus.eBookRepository2019.component.search.value;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * Created by milan.miljus on 9/19/19 19:21.
 */
@Getter
public abstract class BaseIndexingObject {

    @NotBlank
    protected String id;

}
