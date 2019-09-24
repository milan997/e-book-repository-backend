package milan.miljus.eBookRepository2019.service.auth;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.model.User;
import milan.miljus.eBookRepository2019.service.auth.value.ForgotPasswordInfo;
import milan.miljus.eBookRepository2019.service.auth.value.SignInInfo;
import milan.miljus.eBookRepository2019.service.user.GetUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by milan.miljus on 9/22/19 22:14.
 */
@Service
@RequiredArgsConstructor
public class ForgotPassword {

    private final SignIn signIn;
    private final GetUser getUser;

    @Transactional(rollbackFor = Throwable.class, readOnly = false)
    public void execute(final ForgotPasswordInfo info) {
        signIn.execute(new SignInInfo(info.getUsername(), info.getOldPassword()));
        final User user = getUser.byUsername(info.getUsername());
        user.setPassword(info.getNewPassword());
    }

}
