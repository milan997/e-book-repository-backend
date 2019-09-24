package milan.miljus.eBookRepository2019.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by milan.miljus on 2019-07-03 21:49.
 */
@Getter
@Configuration
@ConfigurationProperties(prefix = "custom", ignoreUnknownFields = false)
@EnableConfigurationProperties({CustomProperties.class})
@Validated
public class CustomProperties {

    // TODO: milan.miljus 2019-07-20 14:30 check if these validations work

    @NotNull
    private Storage storage = new Storage();

    @NotNull
    private Elastic elastic = new Elastic();

    @Getter
    @Setter
    public static class Storage {

        @NotBlank
        private String location;

    }

    @Getter
    @Setter
    public static class Elastic {

        @NotBlank
        private String hostname;

        @Range(min = 1024, max = 65535)
        private int port;

        @Pattern(regexp = "^(http|https)$")
        private String protocol;

    }
}
