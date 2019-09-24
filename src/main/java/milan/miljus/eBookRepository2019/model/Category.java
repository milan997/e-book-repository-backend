package milan.miljus.eBookRepository2019.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static milan.miljus.eBookRepository2019.util.ValidationConstants.CATEGORY_NAME_MAX;
import static milan.miljus.eBookRepository2019.util.ValidationConstants.CATEGORY_NAME_MIN;

/**
 * Created by milan.miljus on 2019-07-07 23:59.
 */
@Entity
@NoArgsConstructor
@Where(clause = "deleted = false")
public class Category extends BaseEntity {

    @NotNull
    @Size(min = CATEGORY_NAME_MIN, max = CATEGORY_NAME_MAX)
    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Book> books;

    @ManyToMany
    @JoinTable(
            name = "subs_categories",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sub_id", referencedColumnName = "id")
    )
    private Set<Sub> subs = new HashSet<>();

    public Category(@NotNull @Size(min = CATEGORY_NAME_MIN, max = CATEGORY_NAME_MAX) String name) {
        this.name = name;
    }

    public void addSub(final Sub sub) {
        this.subs.add(sub);
    }

    @Override
    public void delete() {
        super.delete();
        if (books != null) {
            books.forEach(Book::delete);
        }
    }
}
