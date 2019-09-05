package milan.miljus.eBookRepository2019.service.files.exception;

import milan.miljus.eBookRepository2019.controller.exception.CustomException;

/**
 * Created by milan.miljus on 9/5/19 22:02.
 */
public class FileNotFoundException extends CustomException {

    public FileNotFoundException() {
        super("file.not.found");
    }

}
