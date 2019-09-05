package milan.miljus.eBookRepository2019.component.pdf.exception;

import milan.miljus.eBookRepository2019.controller.exception.CustomException;

/**
 * Created by milan.miljus on 9/5/19 22:20.
 */
public class PdfParsingException extends CustomException {

    public PdfParsingException() {
        super("pdf.parsing.exception");
    }

}
