package cn.lzh.string;

import cn.lzh.utils.Log;
import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * BM算法，Boyer-Moore算法<br/>
 * 1、坏字符规则：<br/>
 * 从后向前比较子串的每个字符，子串中首个不匹配的位置记为si，<br/>
 * 主串中不匹配的字符在子串中的位置记为xi，每次子串向后移动si-xi位；<br/>
 * 2、好后缀规则：<br/>
 * <strong>suffix</strong>记录某后缀（后缀的长度为数组的下标）在模式串中出现的位置；<br/>
 * <strong>prefix</strong>记录某后缀（后缀的长度为数组的下标）是否为模式串的前缀；<br/>
 */
final class BoyerMoore {

    private BoyerMoore(){
        throw new AssertionError("cannot instantiation");
    }

    /**
     * @param str    主串
     * @param subStr 子串（模式串）
     * @return 子串在主串中的起始位置
     */
    public static int indexOf(@NotNull String str, @NotNull String subStr) {
        return indexOf(str.toCharArray(), subStr.toCharArray());
    }

    /**
     * @param str    主串
     * @param subStr 子串（模式串）
     * @return 子串在主串中的起始位置
     */
    private static int indexOf(@NotNull char[] str, @NotNull char[] subStr) {
        int n = str.length;
        int m = subStr.length;
        if (m > n) {
            throw new IllegalArgumentException("子串长度超出主串长度");
        }
        Map<Character, Integer> map = generateBC(subStr);
        int[] suffix = new int[m];// 记录某后缀（后缀的长度为数组的下标）在模式串中出现的位置
        boolean[] prefix = new boolean[m];// 记录某后缀（后缀的长度为数组的下标）是否为模式串的前缀
        generateGS(suffix, prefix, subStr);
        Log.debug("suffix=%s, prefix=%s\n", Arrays.toString(suffix), Arrays.toString(prefix));
        int i = 0, j;
        Character key = null;// 主串中当前和子串比较的字符
        int end = n - m;
        while (i <= end) {
            for (j = m - 1; j >= 0; j--) {
                key = str[i + j];
                if (key != subStr[j]) {
                    break;
                }
            }
            if (j < 0) {
                return i;
            }
            Integer xi = map.get(key);
            int x = j - (xi == null ? -1 : xi);// 应用"坏字符规则"得到的 i 向右偏移量
            int y = j < m - 1 ? moveByGS(suffix, prefix, m, j) : 1;// 应用"好后缀规则"得到的 i 向右偏移量
            int offset = Math.max(x, y);
            Log.debug("i=%d, j=%d, key=%c, xi=%d, offset=%d, x=%d, y=%d\n", i, j, key, xi, offset, x, y);
            i += offset;
        }
        return -1;
    }

    /**
     * 生成好后缀规则的前缀和后缀相关数组
     *
     * @param suffix 记录某后缀（后缀的长度为数组的下标）在模式串中出现的位置
     * @param prefix 记录某后缀（后缀的长度为数组的下标）是否为模式串的前缀
     * @param subStr 模式串
     */
    private static void generateGS(int[] suffix, boolean[] prefix, char[] subStr) {
        int m = subStr.length;
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < m - 1; i++) {
            int j = i;
            int k = 0;
            while (j >= 0 && subStr[j] == subStr[m - 1 - k]) {
                suffix[++k] = (j--);
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

    /**
     *
     * 如果好后缀在模式串中有对应的字符串，则返回 j - suffix[k] + 1，
     * 否则返回模式串中前缀相同的后缀的起始位置
     * @param suffix 记录某后缀（后缀的长度为数组的下标）在模式串中出现的位置
     * @param prefix 记录某后缀（后缀的长度为数组的下标）是否为模式串的前缀
     * @param m      模式串的长度
     * @param j      表示坏字符对应的模式串中的字符下标
     * @return 主串索引向右偏移量
     */
    private static int moveByGS(int[] suffix, boolean[] prefix, int m, int j) {
        int k = m - 1 - j;// 好后缀的长度
        if (suffix[k] != -1) {  // 好后缀在模式串中有对应的字符串
            return j - suffix[k] + 1;
        }
        for (int r = j + 2; r < m - 1; r++) {
            if (prefix[m - r]) {
                return r;
            }
        }
        return 1;
    }

    /**
     * 生成坏字符哈希表
     *
     * @param subStr 模式串
     * @return 坏字符哈希表
     */
    private static Map<Character, Integer> generateBC(@NotNull char[] subStr) {
        Map<Character, Integer> map = new HashMap<>();// 坏字符哈希表
        int m = subStr.length;
        for (int i = 0; i < m; i++) {
            map.put(subStr[i], i);// 相同字符存储的值为靠后的索引
        }
        return map;
    }
}
