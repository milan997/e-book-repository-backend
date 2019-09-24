package milan.miljus.eBookRepository2019.model;

import lombok.Getter;
import lombok.Setter;
import milan.miljus.eBookRepository2019.model.enumeration.Role;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by milan.miljus on 2019-07-08 00:31.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@Table(name = "`user`")
@Getter
public abstract class User extends BaseEntity {

    @NotNull
    @NotBlank
    @Setter
    protected String firstName;

    @NotNull
    @NotBlank
    @Setter
    protected String lastName;

    @NotBlank
    @NaturalId
    protected String username;

    @NotNull
    @NotBlank
    @Setter
    protected String password;

    @NotNull
    protected boolean fullAccess;

    public abstract Role getRole();

    public abstract boolean canDownloadBook(final Book book);

    public boolean isSub() {
        return getRole() == Role.SUB;
    }

}
