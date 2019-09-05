package milan.miljus.eBookRepository2019.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@TypeDefs({
//        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
        @TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class),
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
})

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseEntity implements Persistable<UUID> {

    @Id
    @Type(type = "pg-uuid") // for postgres
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private ZonedDateTime updatedTime;

    private boolean deleted;

    @PreUpdate
    private void preUpdate() {
        this.setUpdatedTime(ZonedDateTime.now());
    }

    @Transient
    public boolean isNew() {
        return null == this.getId();
    }

    @Override
    public String toString() {
        return String.format("Entity type %s with id: %s", this.getClass().getName(), this.getId());
    }

}
