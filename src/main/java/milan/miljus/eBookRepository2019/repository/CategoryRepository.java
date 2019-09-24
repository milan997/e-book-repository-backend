package milan.miljus.eBookRepository2019.repository;

import milan.miljus.eBookRepository2019.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by milan.miljus on 2019-07-08 00:18.
 */
@Repository
public interface CategoryRepository extends CustomRepository<Category> {

    List<Category> findAllByOrderByName();

}
