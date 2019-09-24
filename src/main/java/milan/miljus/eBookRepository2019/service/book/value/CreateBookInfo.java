package milan.miljus.eBookRepository2019.service.book.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import milan.miljus.eBookRepository2019.controller.value.book.CreateBookRequest;

import javax.validation.constraints.*;

import static milan.miljus.eBookRepository2019.util.ValidationConstants.*;

/**
 * Created by milan.miljus on 2019-07-20 04:52.
 */
@Getter
@Builder
@AllArgsConstructor
public class CreateBookInfo {

    @NotBlank
    @Size(min = BOOK_TITLE_MIN, max = BOOK_TITLE_MAX)
    private String title;

    @NotBlank
    @Size(min = AUTHOR_NAME_MIN, max = AUTHOR_NAME_MAX)
    private String author;

    private String[] keywords;

    @Positive
    private Integer year;

    @Pattern(regexp = LANGUAGE_ISO_CODE_REGEX)
    private String languageIsoCode;

    @NotNull
    private long categoryId;

    @NotNull
    private String fileKey;

    @Positive
    private long ownerId;

    public CreateBookInfo(final CreateBookRequest request, final long ownerId) {
        this.title = request.getTitle();
        this.author = request.getAuthor();
        this.keywords = request.getKeywords();
        this.year = request.getYear();
        this.languageIsoCode = request.getLanguageCode();
        this.categoryId = request.getCategoryId();
        this.fileKey = request.getFileKey();
        this.ownerId = ownerId;
    }


}
