package milan.miljus.eBookRepository2019.service.book;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.pdf.PdfDissection;
import milan.miljus.eBookRepository2019.component.pdf.value.PdfMeta;
import milan.miljus.eBookRepository2019.controller.value.book.UploadBookFileResponse;
import milan.miljus.eBookRepository2019.service.files.FilesService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by milan.miljus on 9/20/19 17:59.
 */
@Service
@RequiredArgsConstructor
public class UploadBookFile {

    private final FilesService filesService;
    private final PdfDissection pdfDissection;

    public UploadBookFileResponse execute(final MultipartFile multipartFile) {
        final String key = filesService.put(multipartFile);
        final PdfMeta pdfMeta = pdfDissection.extractMeta(key);
        return new UploadBookFileResponse(key, pdfMeta);
    }
}
