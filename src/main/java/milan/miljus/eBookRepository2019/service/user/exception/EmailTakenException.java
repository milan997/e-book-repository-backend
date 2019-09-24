package milan.miljus.eBookRepository2019.service.user.exception;

import milan.miljus.eBookRepository2019.controller.exception.CustomException;

/**
 * Created by milan.miljus on 9/22/19 17:39.
 */
public class EmailTakenException extends CustomException {

    public EmailTakenException() {
        super("email.taken");
    }

}
