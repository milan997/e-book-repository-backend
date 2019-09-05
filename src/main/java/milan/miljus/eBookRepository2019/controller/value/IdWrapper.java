package milan.miljus.eBookRepository2019.controller.value;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by milan.miljus on 2019-07-20 05:08.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IdWrapper {

    @NotNull
    private UUID id;

    public static IdWrapper of(UUID id) {
        return new IdWrapper(id);
    }

}
