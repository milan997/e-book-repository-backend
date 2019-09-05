package milan.miljus.eBookRepository2019.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static milan.miljus.eBookRepository2019.util.ValidationConstants.CATEGORY_NAME_MAX;
import static milan.miljus.eBookRepository2019.util.ValidationConstants.CATEGORY_NAME_MIN;

/**
 * Created by milan.miljus on 2019-07-07 23:59.
 */
@Entity
@Getter
@Setter
public class Category extends BaseEntity {

    @NaturalId
    @NotNull
    @Size(min = CATEGORY_NAME_MIN, max = CATEGORY_NAME_MAX)
    private String name;
//
//    @Type(type = "hstore")
//    @Column(columnDefinition = "hstore")
//    private Map<String, String> translations;

    @OneToMany(mappedBy = "category")
    private List<Book> books;

    @ManyToMany(mappedBy = "categories")
    private List<User> users;
}
