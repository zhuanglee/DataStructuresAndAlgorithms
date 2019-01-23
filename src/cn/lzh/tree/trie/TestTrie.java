package cn.lzh.tree.trie;

public class TestTrie {

    public static void main(String[] args) {
        Trie<Character> trie = new CharTrie();
        trie.add("js");
        trie.add("jar");
        trie.add("java");
        trie.add("java script");
        trie.add("java web");
        trie.add("java is very simple");
        trie.add("java is very powerful");
        System.out.println(trie.search("j"));
        System.out.println(trie.search("ja"));
        System.out.println(trie.search("java"));
        System.out.println(trie.search("java is"));
        System.out.println(trie.search("java is very s"));
        System.out.println(trie.search("js"));
        System.out.println(trie.search("web"));
        System.out.println(trie.search(""));
    }

}
