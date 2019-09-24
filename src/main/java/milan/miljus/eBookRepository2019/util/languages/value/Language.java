package milan.miljus.eBookRepository2019.util.languages.value;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by milan.miljus on 2019-08-07 10:35.
 */
@Getter
@EqualsAndHashCode(of = "isoCode")
@AllArgsConstructor
public class Language {

    private String isoCode;

    private String name;

}
