package milan.miljus.eBookRepository2019.controller;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.pdf.PdfTextExtractor;
import milan.miljus.eBookRepository2019.component.storage.IStorage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * Created by milan.miljus on 9/5/19 21:56.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final IStorage storage;
    private final PdfTextExtractor pdfTextExtractor;

    @GetMapping("/{fileKey}")
    public ResponseEntity<Void> readFile(@PathVariable String fileKey) throws IOException {
        final File file = storage.get(fileKey);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping("/{fileKey}/text")
    public ResponseEntity<String> readPdfText(@PathVariable String fileKey) {
        final String text = pdfTextExtractor.extractText(fileKey);
        return new ResponseEntity<>(text, HttpStatus.I_AM_A_TEAPOT);
    }

}
