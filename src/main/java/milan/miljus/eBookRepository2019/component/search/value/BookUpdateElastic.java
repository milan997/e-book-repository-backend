package milan.miljus.eBookRepository2019.component.search.value;

import lombok.Getter;
import milan.miljus.eBookRepository2019.model.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import static milan.miljus.eBookRepository2019.util.ValidationConstants.*;

/**
 * Created by milan.miljus on 9/19/19 19:21.
 */
@Getter
public class BookUpdateElastic extends BaseIndexingObject {

    @Size(min = AUTHOR_NAME_MIN, max = AUTHOR_NAME_MAX)
    private String author;

    @NotBlank
    private String title;

    @NotNull
    private String[] keywords;

    @Positive
    private Integer year;

    @Size(min = LANGUAGE_CODE_MIN, max = LANGUAGE_CODE_MAX)
    private String language;

    @NotBlank
    private String categoryName;

    @Positive
    private long categoryId;

    public BookUpdateElastic(final Book book) {
        this.id = String.valueOf(book.getId());
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.keywords = book.getKeywords();
        this.year = book.getYear();
        this.language = book.getLanguageCode();
        this.categoryName = book.getCategory().getName();
        this.categoryId = book.getCategory().getId();
    }

}
