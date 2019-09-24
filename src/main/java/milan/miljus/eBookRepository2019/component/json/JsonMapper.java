package milan.miljus.eBookRepository2019.component.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

/**
 * Created by milan.miljus on 9/19/19 19:24.
 */
@UtilityClass
public class JsonMapper {

    private final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

}
