package milan.miljus.eBookRepository2019.service.user.exception;

import milan.miljus.eBookRepository2019.controller.exception.CustomException;

/**
 * Created by milan.miljus on 9/21/19 23:39.
 */
public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super("user.not.found.exception");
    }

}
