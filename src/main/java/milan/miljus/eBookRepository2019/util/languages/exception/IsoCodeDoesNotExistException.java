package milan.miljus.eBookRepository2019.util.languages.exception;

import milan.miljus.eBookRepository2019.controller.exception.CustomException;

/**
 * Created by milan.miljus on 8/22/19 10:39 AM.
 */
public class IsoCodeDoesNotExistException extends CustomException {

    public IsoCodeDoesNotExistException() {
        super("iso.code.does.not.exist");
    }

}
