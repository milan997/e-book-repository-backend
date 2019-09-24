package milan.miljus.eBookRepository2019.model;

import lombok.*;
import milan.miljus.eBookRepository2019.util.Utils;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import java.sql.Timestamp;

import static milan.miljus.eBookRepository2019.util.ValidationConstants.*;

/**
 * Created by milan.miljus on 2019-07-08 00:03.
 * Svaka knjiga mora biti opisana sledećim obeležjima:
 *      id, naslov, ime fajla (apsolutno ili relativno ime), jezik (entitet Language) i kategorija (entitet Category) kojoj pripada.
 * Opciono knjiga može posedovati i sledeća obeležja:
 *      autor, ključne reči, godina objavljivanja, MIME type fajla (application/pdf).
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "deleted = false")
public class Book extends BaseEntity {

    @NotBlank
    @Size(min = BOOK_TITLE_MIN, max = BOOK_TITLE_MAX)
    @Setter
    private String title;

    @NotNull
    private Timestamp timestamp;

    @Setter
    @Size(min = AUTHOR_NAME_MIN, max = AUTHOR_NAME_MAX)
    private String author;

    @Setter
    @Type(type = "string-array")
    @Column(columnDefinition = "text[]")
    private String[] keywords;

    @Positive
    private int pageCount;

    @Setter
    @Positive
    private Integer year;

    @Setter
    @Size(min = LANGUAGE_CODE_MIN, max = LANGUAGE_CODE_MAX)
    private String languageCode;

    private String image;

    @Setter
    @NotNull
    @ManyToOne
    private Category category;

    @NotNull
    @NotBlank
    private String fileKey;

    @ManyToOne
    private Admin owner;

    public void delete() {
        super.delete();
    }

    @PrePersist
    private void setTimestamp() {
        this.timestamp = Utils.getTimestamp();
    }

}
