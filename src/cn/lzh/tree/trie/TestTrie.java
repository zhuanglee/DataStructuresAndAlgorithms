package cn.lzh.tree.trie;

import cn.lzh.utils.Log;

import java.util.*;

public final class TestTrie {

    private TestTrie(){
        throw new AssertionError("cannot instantiation");
    }

    private static final List<String> LIST = Arrays.asList("习近平", "李克强", "李某", "习大大", "共产党");
    private static final String INPUT_TEXT = "习近平、李克强都是伟大的共产党人，我李某人着实佩服习大大和克强同志。";

    static {
        Log.isDebug = true;
    }

    public static void main(String[] args) {
        testAC();
        testAutoCompletion();
    }

    /**
     * 测试AC自动机实现敏感词过滤
     */
    private static void testAC() {
        String text = INPUT_TEXT;
        AC ac = new AC(LIST);
        Log.info("\n敏感词：%s\n", ac);
        Log.info("待过滤信息：%s\n", text);
        Log.info("过滤敏感词结果：%s\n", ac.filter(text));
    }

    /**
     * 测试搜索框自动补全提示
     */
    private static void testAutoCompletion() {
        String text = INPUT_TEXT;
        Trie<Character> trie = new CharTrie(LIST);
        Log.info("\nTrie树中的模式串：%s\n", trie);
        Log.info("主串：%s\n", text);
        int n = text.length();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                String substring = text.substring(i, j);
                List<String> search = trie.search(substring);
                Log.info("search(%s)=%s\n", substring, search);
                if(search == null){
                    break;
                }
            }
        }
    }

}
