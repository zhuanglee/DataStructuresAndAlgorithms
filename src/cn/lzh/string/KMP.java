package cn.lzh.string;

import com.sun.istack.internal.NotNull;

/**
 * KMP算法<br/>
 * https://www.zhihu.com/question/21923021<br/>
 * https://time.geekbang.org/column/article/71845
 */
public final class KMP {

    /**
     * @param str    主串
     * @param subStr 子串（模式串）
     * @return 子串在主串中的起始位置
     */
    public static int indexOf(@NotNull String str, @NotNull String subStr) {
        return indexOf(str.toCharArray(), subStr.toCharArray());
    }

    private static int indexOf(char[] str, char[] subStr) {
        int n = str.length;
        int m = subStr.length;
        if (m > n) {
            throw new IllegalArgumentException("子串长度超出主串长度");
        }
        // 用来存储模式串中每个前缀的最长可匹配前缀子串的结尾字符下标
        int[] next = getNext(subStr);
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (j == -1 || str[i] == subStr[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return j == m ? i - j : -1;
    }

    private static int[] getNext(char[] subStr) {
        int length = subStr.length;
        int[] next = new int[length];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < length - 1) {
            if (j == -1 || subStr[i] == subStr[j]) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
