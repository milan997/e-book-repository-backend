package milan.miljus.eBookRepository2019.service.user;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.model.Admin;
import milan.miljus.eBookRepository2019.repository.AdminRepository;
import milan.miljus.eBookRepository2019.service.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by milan.miljus on 9/22/19 02:28.
 */
@Service
@RequiredArgsConstructor
public class GetAdmin {

    private final AdminRepository adminRepository;

    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public Admin byId(final long id) {
        return adminRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
