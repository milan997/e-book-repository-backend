package milan.miljus.eBookRepository2019.service.auth;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.config.security.JwtUtils;
import milan.miljus.eBookRepository2019.controller.value.auth.SignUpRequest;
import milan.miljus.eBookRepository2019.model.Sub;
import milan.miljus.eBookRepository2019.service.auth.value.SignInResponse;
import milan.miljus.eBookRepository2019.service.user.CreateSub;
import milan.miljus.eBookRepository2019.service.user.value.CreateSubInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by milan.miljus on 9/22/19 17:35.
 */
@Service
@RequiredArgsConstructor
public class SignUp {

    private final CreateSub createSub;

    @Transactional(rollbackFor = Throwable.class, readOnly = false)
    public SignInResponse execute(final SignUpRequest request) {
        final Sub sub = createSub.execute(new CreateSubInfo(request));
        final String token = JwtUtils.generateToken(sub);
        return new SignInResponse(token);
    }


}
