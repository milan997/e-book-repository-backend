package milan.miljus.eBookRepository2019.component.pdf;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.pdf.exception.PdfParsingException;
import milan.miljus.eBookRepository2019.service.files.FilesService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by milan.miljus on 9/5/19 21:38.
 */
@Service
@RequiredArgsConstructor
public class PdfTextExtractor {

    private final FilesService filesService;

    public String extractText(final String fileKey) {
        final File file = filesService.get(fileKey);

        try (final PDDocument pdDoc = PDDocument.load(file)) {
            final PDFTextStripper pdfStripper = new PDFTextStripper();
            final String parsedText = pdfStripper.getText(pdDoc);
            return parsedText;
        } catch (IOException e) {
            throw new PdfParsingException();
        }
    }
}
