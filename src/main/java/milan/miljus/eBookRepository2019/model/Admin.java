package milan.miljus.eBookRepository2019.model;

import milan.miljus.eBookRepository2019.model.enumeration.Role;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.util.List;

/**
 * Created by milan.miljus on 9/21/19 23:23.
 */
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    @OneToMany(mappedBy = "owner")
    public List<Book> books;

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }

    @Override
    public boolean canDownloadBook(Book book) {
        return true;
    }

    @PrePersist
    private void setFullAccess() {
        this.fullAccess = true;
    }

}
