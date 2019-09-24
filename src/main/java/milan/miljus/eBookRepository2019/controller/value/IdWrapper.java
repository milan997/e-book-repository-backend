package milan.miljus.eBookRepository2019.controller.value;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * Created by milan.miljus on 2019-07-20 05:08.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IdWrapper {

    @NotNull
    private long id;

    public static IdWrapper of(long id) {
        return new IdWrapper(id);
    }

}
