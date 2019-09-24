package milan.miljus.eBookRepository2019.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import milan.miljus.eBookRepository2019.model.enumeration.Role;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by milan.miljus on 9/21/19 23:21.
 */
@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("SUB")
public class Sub extends User {

    @ManyToMany(mappedBy = "subs")
    private Set<Category> categories = new HashSet<>();

    @Builder
    public Sub(@NotNull @NotBlank String firstName, @NotNull @NotBlank String lastName, @NotBlank String username, @NotNull @NotBlank String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public List<Long> getCategoryIds() {
        return this.categories.stream().map(Category::getId).collect(Collectors.toList());
    }

    @Override
    public Role getRole() {
        return Role.SUB;
    }

    @Override
    public boolean canDownloadBook(final Book book) {
        return categories.stream().anyMatch(category -> category.getId() == book.getCategory().getId());
    }

    public void addCategory(final Category category) {
        this.categories.add(category);
    }

}
