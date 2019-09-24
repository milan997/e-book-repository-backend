package milan.miljus.eBookRepository2019.service.auth.exception;

import milan.miljus.eBookRepository2019.controller.exception.CustomException;

/**
 * Created by milan.miljus on 9/21/19 23:42.
 */
public class AccessDeniedCustomException extends CustomException {

    public AccessDeniedCustomException() {
        super("access.denied");
    }

}
