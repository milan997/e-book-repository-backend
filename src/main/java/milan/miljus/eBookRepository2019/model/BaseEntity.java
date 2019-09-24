package milan.miljus.eBookRepository2019.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@TypeDefs({
//        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
        @TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class),
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
})
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @Override
    public String toString() {
        return String.format("Entity type %s with id: %s", this.getClass().getName(), this.getId());
    }

    public void delete() {
        this.deleted = true;
    }

}
