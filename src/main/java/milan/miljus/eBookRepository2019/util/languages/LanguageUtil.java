package milan.miljus.eBookRepository2019.util.languages;

import lombok.experimental.UtilityClass;
import milan.miljus.eBookRepository2019.util.languages.value.Language;

import java.util.*;

/**
 * Created by milan.miljus on 8/22/19 10:45 AM.
 */
@UtilityClass
public class LanguageUtil {

    private static final List<Language> languages;
    private static final List<String> isoCodes;

    static {
        final List<String> languageCodes = Arrays.asList(Locale.getISOLanguages());
        isoCodes = Collections.unmodifiableList(languageCodes);

        final List<Language> tempLanguages = new ArrayList<>();
        languageCodes.forEach(languageCode -> {
            final Locale locale = new Locale(languageCode);
            final String languageName = locale.getDisplayLanguage();
            tempLanguages.add(new Language(languageCode, languageName));
        });
        tempLanguages.sort(Comparator.comparing(Language::getName));
        languages = Collections.unmodifiableList(tempLanguages);
    }

    public static List<Language> getLanguages() {
        return languages;
    }

    public static List<String> getIsoCodes() {
        return isoCodes;
    }

    public static boolean isoCodeExists(final String isoCode) {
        return isoCode.contains(isoCode);
    }
}
