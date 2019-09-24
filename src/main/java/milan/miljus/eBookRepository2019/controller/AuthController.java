package milan.miljus.eBookRepository2019.controller;

import lombok.RequiredArgsConstructor;
import milan.miljus.eBookRepository2019.controller.value.auth.SignUpRequest;
import milan.miljus.eBookRepository2019.service.auth.ForgotPassword;
import milan.miljus.eBookRepository2019.service.auth.SignIn;
import milan.miljus.eBookRepository2019.service.auth.SignUp;
import milan.miljus.eBookRepository2019.service.auth.value.ForgotPasswordInfo;
import milan.miljus.eBookRepository2019.service.auth.value.SignInInfo;
import milan.miljus.eBookRepository2019.service.auth.value.SignInResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by milan.miljus on 9/21/19 23:50.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignUp signUp;
    private final SignIn signIn;
    private final ForgotPassword forgotPassword;

    @PostMapping("/sign-up")
    public ResponseEntity<SignInResponse> signUp(@RequestBody @Valid final SignUpRequest request) {
        final SignInResponse response = signUp.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid final SignInInfo info) {
        final SignInResponse response = signIn.execute(info);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody @Valid final ForgotPasswordInfo info) {
        forgotPassword.execute(info);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
