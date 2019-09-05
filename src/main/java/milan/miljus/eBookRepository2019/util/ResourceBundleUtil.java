package milan.miljus.eBookRepository2019.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUtil {
    final static private String RESOURCE_BUNDLE_EXCEPTION = "i18n.exception";

    final static private ResourceBundle.Control resourceControl = new UTF8ResourceBundleControl();

    public static String getExceptionValue(Locale locale, String resourceBundleKey) {
        return getResource(locale, resourceBundleKey, RESOURCE_BUNDLE_EXCEPTION);
    }

    private static String getResource(Locale locale, String resourceBundleKey, String bundleName) {
        return ResourceBundle.getBundle(bundleName, locale, resourceControl).getString(resourceBundleKey);
    }
}
