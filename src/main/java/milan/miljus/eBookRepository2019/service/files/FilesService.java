package milan.miljus.eBookRepository2019.service.files;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.component.storage.IStorage;
import milan.miljus.eBookRepository2019.service.files.exception.FileNotFoundException;
import milan.miljus.eBookRepository2019.util.Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by milan.miljus on 9/5/19 21:25.
 */
@Service
@RequiredArgsConstructor
public class FilesService {

    private final IStorage storage;

    public String put(final MultipartFile multipartFile) {
        final String key = generateFileKey(multipartFile.getOriginalFilename());
        storage.put(key, multipartFile);
        return key;
    }

    public File get(final String key) {
        try {
            return storage.get(key);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    private String generateFileKey(String originalFilename) {
        return Utils.getTimestamp() + "~" + originalFilename;
    }
}
