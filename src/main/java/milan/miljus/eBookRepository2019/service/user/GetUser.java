package milan.miljus.eBookRepository2019.service.user;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.model.User;
import milan.miljus.eBookRepository2019.repository.UserRepository;
import milan.miljus.eBookRepository2019.service.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by milan.miljus on 9/21/19 23:38.
 */
@Service
@RequiredArgsConstructor
public class GetUser {

    private final UserRepository userRepository;

    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public User byUsername(final String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public User byId(final long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public User byUsernameAndPassword(final String username, final String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(UserNotFoundException::new);
    }

}
