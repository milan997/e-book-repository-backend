package milan.miljus.eBookRepository2019.service.user.value;

import lombok.Getter;
import milan.miljus.eBookRepository2019.controller.value.auth.SignUpRequest;
import milan.miljus.eBookRepository2019.model.enumeration.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by milan.miljus on 9/21/19 23:52.
 */
@Getter
public class CreateSubInfo {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private Role role;

    public CreateSubInfo(final SignUpRequest request) {
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.username = request.getUsername();
        this.password = request.getPassword();
        this.role = Role.SUB;
    }

}
