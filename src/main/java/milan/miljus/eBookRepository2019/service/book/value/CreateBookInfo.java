package milan.miljus.eBookRepository2019.service.book.value;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

import static milan.miljus.eBookRepository2019.util.ValidationConstants.*;

/**
 * Created by milan.miljus on 2019-07-20 04:52.
 */
@Getter
@Builder
public class CreateBookInfo {

    @NotBlank
    @Size(min = BOOK_NAME_MIN, max = BOOK_NAME_MAX)
    private String name;

    @NotBlank
    @Size(min = AUTHOR_NAME_MIN, max = AUTHOR_NAME_MAX)
    private String author;

    private List<String> keywords;

    @Pattern(regexp = MIME_TYPE_REGEX)
    private String mimeType;

    @Positive
    private Integer year;

    @Pattern(regexp = LANGUAGE_ISO_CODE_REGEX)
    private String languageIsoCode;

    @NotNull
    private UUID categoryId;

    @NotNull
    private String fileKey;
}
