package milan.miljus.eBookRepository2019.repository;

import milan.miljus.eBookRepository2019.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by milan.miljus on 2019-07-03 23:47.
 */
@NoRepositoryBean
public interface CustomRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

}
