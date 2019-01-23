package cn.lzh.string;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串匹配算法<br/>
 * https://time.geekbang.org/column/article/71187<br/>
 * https://time.geekbang.org/column/article/71525<br/>
 */
public final class StringUtils {

    private StringUtils(){
        throw new AssertionError("cannot instantiation");
    }

    /**
     * @see String#indexOf(String)
     * @param str    主串
     * @param subStr 子串（模式串）
     * @return 子串在主串中的起始位置
     */
    public static int indexOf(@NotNull String str, @NotNull String subStr) {
        return str.indexOf(subStr);
    }

    /**
     * BF算法，BruteForce算法，暴力匹配算法（也叫朴素匹配算法）
     *
     * @param str    主串
     * @param subStr 子串（模式串）
     * @return 子串在主串中的起始位置
     */
    public static int indexOfByBF(@NotNull String str, @NotNull String subStr) {
        return indexOfByBF(str.toCharArray(), subStr.toCharArray());
    }

    /**
     * BF算法，BruteForce算法，暴力匹配算法（也叫朴素匹配算法）
     *
     * @param str    主串
     * @param subStr 子串（模式串）
     * @return 子串在主串中的起始位置
     */
    private static int indexOfByBF(@NotNull char[] str, @NotNull char[] subStr) {
        int n = str.length;
        int m = subStr.length;
        if (m > n) {
            throw new IllegalArgumentException("子串长度超出主串长度");
        }
        int end = n - m;
        int i, j;
        for (i = 0; i <= end; i++) {
            for (j = 0; j < m; j++) {
                if (str[i + j] != subStr[j]) break;
                if (j == m - 1) return i;
            }
        }
        return -1;
    }

    /**
     * RK算法，Rabin-Karp算法，BF算法升级版：<br/>
     * 1、对每个子串求哈希值(哈希值可能存在冲突)<br/>
     * 2、比较哈希值，哈希值相同时，再比较哈希值相同的两个子串的内容
     *
     * @param str    主串
     * @param subStr 子串（模式串）
     * @return 子串在主串中的起始位置
     */
    public static int indexOfByRK(@NotNull String str, @NotNull String subStr) {
        int n = str.length();
        int m = subStr.length();
        if (m > n) {
            throw new IllegalArgumentException("子串长度超出主串长度");
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= n - m; i++) {
            // 对每个子串求哈希值
            map.put(str.substring(i, i + m).hashCode(), i);
        }
        Integer index = map.get(subStr.hashCode());
        if (index != null) {
            // 哈希值可能存在冲突，再比较哈希值相同的两个子串的内容是否相同
            String substring = str.substring(index, index + m);
            for (int i = 0; i < m; i++) {
                if (subStr.charAt(i) != substring.charAt(i)) {
                    return -1;
                }
            }
        }
        return index == null ? -1 : index;
    }

    /**
     * BM算法<br/>
     *
     * @param str    主串
     * @param subStr 子串（模式串）
     * @return 子串在主串中的起始位置
     * @see BoyerMoore#indexOf(String, String)
     */
    public static int indexOfByBM(@NotNull String str, @NotNull String subStr) {
        return BoyerMoore.indexOf(str, subStr);
    }

    /**
     * KMP算法<br/>
     *
     * @param str    主串
     * @param subStr 子串（模式串）
     * @return 子串在主串中的起始位置
     * @see KMP#indexOf(String, String)
     */
    public static int indexOfByKMP(@NotNull String str, @NotNull String subStr) {
        return KMP.indexOf(str, subStr);
    }
}
