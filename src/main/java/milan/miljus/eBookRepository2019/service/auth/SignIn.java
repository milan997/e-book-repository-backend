package milan.miljus.eBookRepository2019.service.auth;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.config.security.JwtUtils;
import milan.miljus.eBookRepository2019.model.User;
import milan.miljus.eBookRepository2019.service.auth.value.SignInInfo;
import milan.miljus.eBookRepository2019.service.auth.value.SignInResponse;
import milan.miljus.eBookRepository2019.service.user.GetUser;
import milan.miljus.eBookRepository2019.service.auth.exception.AccessDeniedCustomException;
import milan.miljus.eBookRepository2019.service.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by milan.miljus on 9/11/19 20:08.
 */
@Service
@RequiredArgsConstructor
public class SignIn {

    private final GetUser getUser;

    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public SignInResponse execute(final SignInInfo signInInfo) {
        final String username = signInInfo.getUsername();
        final String password = signInInfo.getPassword();

        try {
            final User user = getUser.byUsernameAndPassword(username, password);
//            final User user = getUser.byUsername(username);

            final String token = JwtUtils.generateToken(user);

            final SignInResponse signInResponse = new SignInResponse(token);
            return signInResponse;

        } catch (UserNotFoundException ignore) {
            throw new AccessDeniedCustomException();
        }
    }

}
