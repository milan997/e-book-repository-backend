package milan.miljus.eBookRepository2019.controller.value.auth;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * Created by milan.miljus on 9/21/19 23:50.
 */
@Getter
public class SignUpRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
