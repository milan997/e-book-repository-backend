package milan.miljus.eBookRepository2019.controller.value.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import static milan.miljus.eBookRepository2019.util.ValidationConstants.*;

/**
 * Created by milan.miljus on 2019-07-08 01:16.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {

    @NotBlank
    @Size(min = BOOK_TITLE_MIN, max = BOOK_TITLE_MAX)
    private String title;

    @Size(min = AUTHOR_NAME_MIN, max = AUTHOR_NAME_MAX)
    private String author;

    private String[] keywords;

    private Integer year;

    @Pattern(regexp = LANGUAGE_ISO_CODE_REGEX)
    private String languageCode;

    @Positive
    private long categoryId;

    @NotNull
    private String fileKey;
}
