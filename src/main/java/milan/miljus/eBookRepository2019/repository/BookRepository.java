package milan.miljus.eBookRepository2019.repository;

import milan.miljus.eBookRepository2019.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by milan.miljus on 2019-07-08 00:17.
 */
@Repository
public interface BookRepository extends CustomRepository<Book> {

    List<Book> getAllByCategoryId(UUID categoryId);

}
