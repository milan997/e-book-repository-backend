package milan.miljus.eBookRepository2019.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

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
public class Book extends BaseEntity {

    @NotBlank
    @Size(min = BOOK_NAME_MIN, max = BOOK_NAME_MAX)
    private String name;

    @Size(min = AUTHOR_NAME_MIN, max = AUTHOR_NAME_MAX)
    private String author;

    @Type(type = "string-array")
    @Column(columnDefinition = "text[]")
    private List<String> keywords;

    @Pattern(regexp = MIME_TYPE_REGEX)
    private String mimeType;

    @Positive
    private Integer year;

    @Size(min = LANGUAGE_NAME_MIN, max = LANGUAGE_NAME_MAX)
    private String language;

    private String image;

    @NotNull
    @ManyToOne
    private Category category;

    @Builder
    public Book(@NotNull UUID id, @NotNull @NotBlank @Size(min = BOOK_NAME_MIN, max = BOOK_NAME_MAX) String name, @Size(min = 3, max = 120) String author, List<String> keywords, @Size(min = 3, max = 100) @Pattern(regexp = "[a-z]+/[a-z]+") String mimeType, Integer year, String language, String image, @NotNull Category category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.keywords = keywords;
        this.mimeType = mimeType;
        this.year = year;
        this.language = language;
        this.image = image;
        this.category = category;
    }
}
