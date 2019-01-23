package cn.lzh.string;

import cn.lzh.utils.Log;

import static cn.lzh.string.StringUtils.*;

public final class StringUtilsTest {

    private StringUtilsTest(){
        throw new AssertionError("cannot instantiation");
    }

    static {
        Log.isDebug = false;
    }

    public static void main(String[] args) {
        String str = "AabcababcdefgabcabcZ";
        String[] subStrArray = new String[]{ "abab","abcabc", "abd", "cbacba", "A", "Z"};
        for (String subStr : subStrArray) {
            Log.info("\n\"%s\".indexOf(\"%s\")=%d\n", str, subStr, indexOf(str, subStr));
            Log.info("\"%s\".indexOfByBF(\"%s\")=%d\n", str, subStr,  indexOfByBF(str, subStr));
            Log.info("\"%s\".indexOfByRK(\"%s\")=%d\n", str, subStr,  indexOfByRK(str, subStr));
            Log.info("\"%s\".indexOfByBM(\"%s\")=%d\n", str, subStr,  indexOfByBM(str, subStr));
            Log.info("\"%s\".indexOfByKMP(\"%s\")=%d\n", str, subStr,  indexOfByKMP(str, subStr));
        }
    }

}
