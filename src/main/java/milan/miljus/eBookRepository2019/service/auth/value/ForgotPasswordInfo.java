package milan.miljus.eBookRepository2019.service.auth.value;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * Created by milan.miljus on 9/22/19 22:15.
 */
@Getter
public class ForgotPasswordInfo {

    @NotBlank
    private String username;

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;

}
