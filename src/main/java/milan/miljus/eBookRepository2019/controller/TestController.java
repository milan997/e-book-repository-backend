package milan.miljus.eBookRepository2019.controller;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.pdf.PdfDissection;
import milan.miljus.eBookRepository2019.component.search.ElasticIndexing;
import milan.miljus.eBookRepository2019.component.storage.IStorage;
import milan.miljus.eBookRepository2019.service.files.FilesService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by milan.miljus on 9/5/19 21:56.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final IStorage storage;
    private final PdfDissection pdfDissection;
    private final ElasticIndexing elasticIndexing;
    private final FilesService filesService;

    @GetMapping("/{fileKey}")
    public ResponseEntity<Void> readFile(@PathVariable String fileKey) throws IOException {
        final File file = storage.get(fileKey);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping("/{fileKey}/text")
    public ResponseEntity<String> readPdfText(@PathVariable String fileKey) {
        final String text = pdfDissection.extractText(fileKey);
        return new ResponseEntity<>(text, HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping(path = "/download")//, produces = "")
    public ResponseEntity<Resource> download() throws IOException {
        final File file = filesService.get("2019-09-21 13:18:56.822~JavaScript_ JavaScript For Beginners - Learn JavaScript Programming with ease in HALF THE TIME - Everything about the Language, Coding, Programming and Web Pages You need to know ( PDFDrive.com ).pdf");

        final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"java-tutorial.pdf\"")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

}
