package milan.miljus.eBookRepository2019.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final String resourceBundleKey;

}
