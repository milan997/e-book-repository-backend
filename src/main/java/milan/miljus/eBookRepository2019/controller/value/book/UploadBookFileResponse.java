package milan.miljus.eBookRepository2019.controller.value.book;

import lombok.Getter;
import milan.miljus.eBookRepository2019.component.pdf.value.PdfMeta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by milan.miljus on 9/20/19 17:57.
 */
@Getter
public class UploadBookFileResponse {

    @NotBlank
    private String fileKey;

    @NotNull
    private PdfMeta pdfMeta;

    public UploadBookFileResponse(final String fileKey, final PdfMeta pdfMeta) {
        this.fileKey = fileKey;
        this.pdfMeta = pdfMeta;
    }

}
