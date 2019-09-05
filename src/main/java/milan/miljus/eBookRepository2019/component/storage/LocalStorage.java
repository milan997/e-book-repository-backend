package milan.miljus.eBookRepository2019.component.storage;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import milan.miljus.eBookRepository2019.config.CustomProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by milan.miljus on 2019-07-20 14:25.
 */
@Service
@RequiredArgsConstructor
public class LocalStorage implements IStorage {

    private final CustomProperties customProperties;

    @Override
    public void put(String key, File file) {

    }

    @Override
    @SneakyThrows(IOException.class)
    public void put(String key, MultipartFile multipartFile) {
        final byte[] bytes = multipartFile.getBytes();
        final Path path = Paths.get(customProperties.getStorage().getLocation() + key);
        Files.write(path, bytes);
    }

    @Override
    public File get(String key) throws IOException {
        final byte[] bytes = Files.readAllBytes(Paths.get(customProperties.getStorage().getLocation() + key));
        final Path path = Paths.get(customProperties.getStorage().getLocation() + key);
        final File file = path.toFile();
        return file;
    }

    @Override
    public void delete(String key) {

    }


}
