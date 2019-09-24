package milan.miljus.eBookRepository2019.repository;

import milan.miljus.eBookRepository2019.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by milan.miljus on 2019-07-08 00:17.
 */
@Repository
public interface BookRepository extends CustomRepository<Book> {

    @Query(nativeQuery = true, value = "SELECT * FROM Book WHERE category_id = :categoryId")
    List<Book> findByCategoryId(final long categoryId);

}
