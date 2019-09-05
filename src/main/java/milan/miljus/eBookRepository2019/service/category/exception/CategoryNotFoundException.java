package milan.miljus.eBookRepository2019.service.category.exception;

import milan.miljus.eBookRepository2019.controller.exception.CustomException;

/**
 * Created by milan.miljus on 9/5/19 22:04.
 */
public class CategoryNotFoundException extends CustomException {

    public CategoryNotFoundException() {
        super("category.not.found");
    }

}
