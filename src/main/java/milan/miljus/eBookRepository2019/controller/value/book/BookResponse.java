package milan.miljus.eBookRepository2019.controller.value.book;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Created by milan.miljus on 2019-07-08 01:19.
 */
@Builder
public class BookResponse {

    @NotBlank
    @Size(min = 3, max = 80)
    private String name;

    @NotBlank
    private String fileName;

    @Size(min = 3, max = 120)
    private String author;

    @Size(min = 3, max = 120)
    private String keywords;

    @Size(min = 3, max = 100)
    @Pattern(regexp = "[a-z]+/[a-z]+")
    private String mimeType;

    private Integer year;

    private String language;

    @NotNull
    private UUID categoryId;

}
