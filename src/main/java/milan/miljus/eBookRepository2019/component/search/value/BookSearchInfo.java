package milan.miljus.eBookRepository2019.component.search.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by milan.miljus on 9/23/19 21:05.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookSearchInfo {

    public static final BookSearchInfo GET_ALL = new BookSearchInfo(null, null);

    private String searchTerm;

    private List<Long> categories;

    public boolean isGetAll() {
        return filteringCategories() && isSearch();
    }

    public boolean filteringCategories() {
        return categories != null && categories.size() > 0;
    }

    public boolean isSearch() {
        return StringUtils.hasText(searchTerm);
    }

}
