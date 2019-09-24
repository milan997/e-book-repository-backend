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
public class BookElastic extends BaseIndexingObject {

    @NotBlank
    @Size(min = BOOK_TITLE_MIN, max = BOOK_TITLE_MAX)
    private String title;

    @Size(min = AUTHOR_NAME_MIN, max = AUTHOR_NAME_MAX)
    private String author;

    @NotNull
    private String[] keywords;

    @Positive
    private Integer year;

    @Size(min = LANGUAGE_CODE_MIN, max = LANGUAGE_CODE_MAX)
    private String language;

    @NotBlank
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
    private String text;

    @NotBlank
    private String timestamp;


    public BookElastic(final Book book, final String text) {
        this.id = String.valueOf(book.getId());
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.keywords = book.getKeywords();
        this.year = book.getYear();
        this.language = book.getLanguageCode();
        this.image = book.getImage();
        this.categoryName = book.getCategory().getName();
        this.categoryId = book.getCategory().getId();
        this.pageCount = book.getPageCount();
        this.timestamp = book.getTimestamp().toString();

        this.ownerId = book.getOwner().getId();
        this.ownerName = book.getOwner().getUsername();
        this.text = text;
    }
}
