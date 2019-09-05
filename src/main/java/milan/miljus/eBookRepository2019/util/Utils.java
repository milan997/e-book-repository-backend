package milan.miljus.eBookRepository2019.util;

import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by milan.miljus on 9/5/19 21:24.
 */
@UtilityClass
public class Utils {

    public String getTimestamp() {
        final Date date= new Date();
        final long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        return ts.toString();
    }

}
