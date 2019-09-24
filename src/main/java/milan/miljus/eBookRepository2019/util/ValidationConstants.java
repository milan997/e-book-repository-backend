package milan.miljus.eBookRepository2019.util;

import lombok.experimental.UtilityClass;

/**
 * Created by milan.miljus on 2019-07-20 04:52.
 */
@UtilityClass
public final class ValidationConstants {

    public static final int BOOK_TITLE_MIN = 1;
    public static final int BOOK_TITLE_MAX = 255;

    public static final int AUTHOR_NAME_MIN = 1;
    public static final int AUTHOR_NAME_MAX = 80;

    public static final int LANGUAGE_CODE_MIN = 2;
    public static final int LANGUAGE_CODE_MAX = 6;

    public static final int CATEGORY_NAME_MIN = 3;
    public static final int CATEGORY_NAME_MAX = 63;

    public static final String FILENAME_REGEX = ".{3,}\\.[a-z]{2,5}";
    public static final String LANGUAGE_ISO_CODE_REGEX = "[a-z]{2,5}";


}
