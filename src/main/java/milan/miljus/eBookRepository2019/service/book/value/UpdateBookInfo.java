package milan.miljus.eBookRepository2019.service.book.value;

import lombok.Getter;
import milan.miljus.eBookRepository2019.controller.value.book.UpdateBookRequest;

import javax.validation.constraints.*;

import static milan.miljus.eBookRepository2019.util.ValidationConstants.*;

/**
 * Created by milan.miljus on 9/23/19 00:41.
 */
@Getter
public class UpdateBookInfo {

    @Positive
    private long id;

    @NotBlank
    @Size(min = AUTHOR_NAME_MIN, max = AUTHOR_NAME_MAX)
    private String author;

    private String[] keywords;

    @Positive
    private Integer year;

    @Pattern(regexp = LANGUAGE_ISO_CODE_REGEX)
    private String languageIsoCode;

    @NotBlank
    @Size(min = BOOK_TITLE_MIN, max = BOOK_TITLE_MAX)
    private String title;

    @NotNull
    private long categoryId;

    public UpdateBookInfo(final UpdateBookRequest request, final long id) {
        this.id = id;
        this.author = request.getAuthor();
        this.keywords = request.getKeywords();
        this.year = request.getYear();
        this.languageIsoCode = request.getLanguageIsoCode();
        this.categoryId = request.getCategoryId();
        this.title = request.getTitle();
    }

}
