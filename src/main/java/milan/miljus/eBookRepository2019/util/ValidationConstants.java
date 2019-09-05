package milan.miljus.eBookRepository2019.util;

/**
 * Created by milan.miljus on 2019-07-20 04:52.
 */
public final class ValidationConstants {

    public static final int BOOK_NAME_MIN = 3;
    public static final int BOOK_NAME_MAX = 255;

    public static final int AUTHOR_NAME_MIN = 3;
    public static final int AUTHOR_NAME_MAX = 80;

    public static final int LANGUAGE_NAME_MIN = 2;
    public static final int LANGUAGE_NAME_MAX = 6;

    public static final int CATEGORY_NAME_MIN = 3;
    public static final int CATEGORY_NAME_MAX = 63;

    public static final String FILENAME_REGEX = ".{3,}\\.[a-z]{2,5}";
    public static final String MIME_TYPE_REGEX = "[a-z]{3,}\\/[a-z]{3,}";
    public static final String LANGUAGE_ISO_CODE_REGEX = "[a-z]{2,5}";


}
