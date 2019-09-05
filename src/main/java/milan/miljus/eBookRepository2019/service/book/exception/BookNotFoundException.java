package milan.miljus.eBookRepository2019.service.book.exception;

import milan.miljus.eBookRepository2019.controller.exception.CustomException;

/**
 * Created by milan.miljus on 9/5/19 22:11.
 */
public class BookNotFoundException extends CustomException {

    public BookNotFoundException() {
        super("book.not.found");
    }

}
