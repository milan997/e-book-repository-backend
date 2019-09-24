package milan.miljus.eBookRepository2019.service.user;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.model.Sub;
import milan.miljus.eBookRepository2019.repository.UserRepository;
import milan.miljus.eBookRepository2019.service.user.exception.EmailTakenException;
import milan.miljus.eBookRepository2019.service.user.value.CreateSubInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by milan.miljus on 9/21/19 23:51.
 */
@Service
@RequiredArgsConstructor
public class CreateSub {

    private final UserRepository userRepository;

    @Transactional(rollbackFor = Throwable.class, readOnly = false)
    public Sub execute(final CreateSubInfo info) {
        check(info);

        final Sub sub = Sub.builder()
                .firstName(info.getFirstName())
                .lastName(info.getLastName())
                .username(info.getUsername())
                .password(info.getPassword())
                .build();

        userRepository.save(sub);

        return sub;
    }

    private void check(CreateSubInfo info) {
        if (userRepository.existsByUsernameIncludeDeleted(info.getUsername())) {
            throw new EmailTakenException();
        }
    }
}
