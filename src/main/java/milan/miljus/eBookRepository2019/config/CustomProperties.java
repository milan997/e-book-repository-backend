package milan.miljus.eBookRepository2019.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @Getter
    @Setter
    public static class Storage {

        @NotBlank
        private String location;

    }
}
