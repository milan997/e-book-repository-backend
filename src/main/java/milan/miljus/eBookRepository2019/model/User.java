package milan.miljus.eBookRepository2019.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import milan.miljus.eBookRepository2019.model.enumeration.UserType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by milan.miljus on 2019-07-08 00:31.
 */
@Entity
@Table(name = "`user`")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @NaturalId
    private String username;

    // TODO: milan.miljus 2019-07-20 04:42 extract password into new UserAuthorization or something entity
    //
//    @NotBlank
//    @NotNull
//    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @NotNull
    private boolean allCategories;

    @ManyToMany
    private List<Category> categories;

}
