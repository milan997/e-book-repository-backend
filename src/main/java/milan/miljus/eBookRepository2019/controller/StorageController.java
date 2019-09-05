package milan.miljus.eBookRepository2019.controller;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.controller.value.storage.UploadFileResponse;
import milan.miljus.eBookRepository2019.service.files.FilesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by milan.miljus on 9/5/19 20:58.
 */
@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {

    private final FilesService filesService;


    @PostMapping
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        final String key = filesService.put(multipartFile);
        return new ResponseEntity<>(new UploadFileResponse(key), HttpStatus.CREATED);
    }


}
