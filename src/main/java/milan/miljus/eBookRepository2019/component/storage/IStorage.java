package milan.miljus.eBookRepository2019.component.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by milan.miljus on 2019-07-06 23:39.
 */
public interface IStorage {

    // TODO: milan.miljus 2019-07-06 23:39 finish

    void put(String key, File file);

    void put(String key, MultipartFile multipartFile);

    File get(String key) throws IOException;

    void delete(String key);
}
