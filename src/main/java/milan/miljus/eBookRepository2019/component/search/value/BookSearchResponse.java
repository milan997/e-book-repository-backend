package milan.miljus.eBookRepository2019.component.search.value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

import static milan.miljus.eBookRepository2019.util.ValidationConstants.*;

/**
 * Created by milan.miljus on 9/21/19 13:47.
 */
@Getter
@AllArgsConstructor
public class BookSearchResponse {

    private List<String> suggestions;

    @Setter
    private List<BookSearchResponse.Book> books;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Book {

        @Positive
        private long id;

        @NotBlank
        @Size(min = BOOK_TITLE_MIN, max = BOOK_TITLE_MAX)
        private String title;

        @Size(min = AUTHOR_NAME_MIN, max = AUTHOR_NAME_MAX)
        private String author;

        @Column(columnDefinition = "text[]")
        private String[] keywords;

        @Positive
        private Integer year;

        @Size(min = LANGUAGE_CODE_MIN, max = LANGUAGE_CODE_MAX)
        private String language;

        private String image;

        @NotBlank
        private String categoryName;

        @Positive
        private long categoryId;

        @Positive
        private int pageCount;

        @NotBlank
        private String ownerName;

        @Positive
        private long ownerId;

        @NotBlank
        private String timestamp;

        @Setter
        private String highlight;

    }

}
