package milan.miljus.eBookRepository2019.repository;

import milan.miljus.eBookRepository2019.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * Created by milan.miljus on 2019-07-03 23:47.
 */
@NoRepositoryBean
public interface CustomRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {

    @Override
    @Query("update #{#entityName} e set e.deleted=true where e.id = ?1")
    @Modifying
    void deleteById(UUID id);


    @Override
    @Modifying
    default void delete(T entity) {
        deleteById(entity.getId());
    }
}
