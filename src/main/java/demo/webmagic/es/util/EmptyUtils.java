package demo.webmagic.es.util;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * <p>
 * es
 *
 * @Date: 2022/6/17 20:43
 * @Author: James Lin
 * @Version: 1.0
 */

public class EmptyUtils {

    public static boolean isEmpty(Object s) {
        if (s == null) {
            return true;
        }
        if ((s instanceof String) && (((String)s).trim().length() == 0)) {
            return true;
        }
        if (s instanceof Map) {
            return ((Map<?, ?>)s).isEmpty();
        }
        if (s instanceof List) {
            return ((List<?>)s).isEmpty();
        }
        if (s instanceof Object[]) {
            return (((Object[])s).length == 0);
        }
        return false;
    }
}
