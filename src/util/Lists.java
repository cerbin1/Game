package util;

import java.util.LinkedList;
import java.util.List;

public class Lists {
    @SafeVarargs
    public static <T> List<T> join(List<T>... lists) {
        List<T> combined = new LinkedList<>();
        for (List<T> list : lists) {
            combined.addAll(list);
        }
        return combined;
    }
}
