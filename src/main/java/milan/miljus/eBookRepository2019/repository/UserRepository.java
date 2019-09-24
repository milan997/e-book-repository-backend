package milan.miljus.eBookRepository2019.repository;

import milan.miljus.eBookRepository2019.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by milan.miljus on 2019-07-08 00:34.
 */
@Repository
public interface UserRepository extends CustomRepository<User> {

    Optional<User> findByUsername(final String username);

    Optional<User> findByUsernameAndPassword(final String username, final String password);

    @Query(nativeQuery = true,
        value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
                "FROM public.\"user\" WHERE username = :username"
    )
    boolean existsByUsernameIncludeDeleted(@Param("username") final String username);

}
