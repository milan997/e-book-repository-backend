package milan.miljus.eBookRepository2019.controller.value.book;

import lombok.Getter;
import milan.miljus.eBookRepository2019.model.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Created by milan.miljus on 2019-07-08 01:19.
 */
@Getter
public class BookResponse {

    @NotBlank
    @Size(min = 3, max = 80)
    private String title;

    @Size(min = 3, max = 120)
    private String author;

    @Size(min = 3, max = 120)
    private String[] keywords;

    @Positive
    private int year;

    @NotBlank
    private String languageCode;

    @NotNull
    private long categoryId;

    public BookResponse(final Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.keywords = book.getKeywords();
        this.year = book.getYear();
        this.languageCode = book.getLanguageCode();
        this.categoryId = book.getCategory().getId();
    }

}
