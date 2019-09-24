package milan.miljus.eBookRepository2019.controller.value.book;

import lombok.Getter;

import javax.validation.constraints.*;

import static milan.miljus.eBookRepository2019.util.ValidationConstants.*;

/**
 * Created by milan.miljus on 9/23/19 00:42.
 */
@Getter
public class UpdateBookRequest {

    @NotBlank
    @Size(min = AUTHOR_NAME_MIN, max = AUTHOR_NAME_MAX)
    private String author;

    @NotBlank
    @Size(min = BOOK_TITLE_MIN, max = BOOK_TITLE_MAX)
    private String title;

    private String[] keywords;

    @Positive
    private Integer year;

    @Pattern(regexp = LANGUAGE_ISO_CODE_REGEX)
    private String languageIsoCode;

    @NotNull
    private long categoryId;

}
