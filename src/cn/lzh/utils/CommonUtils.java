package cn.lzh.utils;

import java.util.Arrays;

public final class CommonUtils {

    private CommonUtils(){
        throw new AssertionError("cannot instantiation");
    }

    /**
     * {@link Arrays#toString(Object[])}
     * @param a 数组
     * @param isFilterNull 是否过滤null元素
     */
    public static String toString(Object[] a, boolean isFilterNull) {
        if(!isFilterNull){
            return Arrays.toString(a);
        }
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (Object o : a) {
            if (o == null) {
                continue;
            }
            b.append(String.valueOf(o)).append(", ");
        }
        int length = b.length();
        int suffixLength = ", ".length();
        if (length > suffixLength) {
            b.delete(length - suffixLength, length);
        }
        return b.append(']').toString();
    }

}
