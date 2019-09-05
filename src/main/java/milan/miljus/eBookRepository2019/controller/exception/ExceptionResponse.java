package milan.miljus.eBookRepository2019.controller.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import milan.miljus.eBookRepository2019.util.ResourceBundleUtil;
import org.springframework.http.HttpStatus;

import java.util.Locale;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
final class ExceptionResponse {

    private final int status;

    private final String errorKey;

    private final String errorMessage;

    private String localizedErrorMessage;

    private Object data;

    ExceptionResponse(String resourceBundleExceptionKey, HttpStatus status) {
        this.status = status.value();
        this.errorKey = resourceBundleExceptionKey;
        this.errorMessage = ResourceBundleUtil.getExceptionValue(Locale.ENGLISH, resourceBundleExceptionKey);
    }

    ExceptionResponse(String resourceBundleExceptionKey, Locale locale, HttpStatus status) {
        this.status = status.value();
        this.errorKey = resourceBundleExceptionKey;
        this.errorMessage = ResourceBundleUtil.getExceptionValue(Locale.ENGLISH, resourceBundleExceptionKey);
        this.localizedErrorMessage = ResourceBundleUtil.getExceptionValue(locale, resourceBundleExceptionKey);
    }

    ExceptionResponse(String resourceBundleExceptionKey, Locale locale, String errorDescription, HttpStatus status) {
        this.status = status.value();
        this.errorKey = resourceBundleExceptionKey;
        this.errorMessage = errorDescription;
        this.localizedErrorMessage = ResourceBundleUtil.getExceptionValue(locale, resourceBundleExceptionKey);
    }

    void setErrors(Object data) {
        this.data = data;
    }

}
