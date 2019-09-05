package milan.miljus.eBookRepository2019.controller.value.book;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

import static milan.miljus.eBookRepository2019.util.ValidationConstants.*;

/**
 * Created by milan.miljus on 2019-07-08 01:16.
 */
@Getter
@Builder
public class CreateBookRequest {

    @NotBlank
    @Size(min = BOOK_NAME_MIN, max = BOOK_NAME_MAX)
    private String name;

    @Size(min = AUTHOR_NAME_MIN, max = AUTHOR_NAME_MAX)
    private String author;

    private List<String> keywords;

    @Pattern(regexp = MIME_TYPE_REGEX)
    private String mimeType;

    private Integer year;

    @Pattern(regexp = LANGUAGE_ISO_CODE_REGEX)
    private String languageCode;

    @NotNull
    private UUID categoryId;

    @NotNull
    private String fileKey;
}
