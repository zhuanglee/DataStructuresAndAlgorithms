package cn.lzh.greedy;

import cn.lzh.utils.Log;
import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * Huffman编码
 */
public class Huffman {

    private HuffmanNode root;

    /**
     * 编码
     * @param reader A character stream whose source is a string.
     * @return 编码结果
     */
    public String encode(StringReader reader) throws IOException {
        root = buildTree(countCharTimes(reader));
        printTree(root);
        Map<Character, String> char2code = dfs(root);
        StringBuilder sb = new StringBuilder();
        char[] chars = new char[1024];
        int len;
        while ((len = reader.read(chars)) != -1) {
            sb.append(encode(char2code, chars, len));
        }
        return sb.toString();
    }

    /**
     * 编码
     * @param text 原文
     * @return 编码结果
     */
    public String encode(@NotNull String text) {
        char[] chars = text.toCharArray();
        root = buildTree(countCharTimes(chars));
        printTree(root);
        return encode(dfs(root), chars, chars.length);
    }

    /**
     * 编码
     *
     * @param char2code 字符-Huffman编码映射表
     * @param chars     字符数组
     * @return 编码结果
     */
    private static String encode(Map<Character, String> char2code, @NotNull char[] chars, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char key = chars[i];
            String code = char2code.get(key);
            if (code == null) {
                throw new IllegalStateException("not support char：" + key);
            }
            Log.debug("key=%s, code=%s\n", key, code);
            sb.append(code);
        }
        return sb.toString();
    }

    /**
     * 解码
     *
     * @param huffmanCode 哈夫曼编码
     * @return 原文
     */
    public String decode(String huffmanCode) {
        StringBuilder sb = new StringBuilder();
        char[] chars = huffmanCode.toCharArray();
        HuffmanNode node = root;
        for (char c : chars) {
            node = c == '0' ? node.left : node.right;
            if (node.key != null) {
                sb.append(node.key);
                node = root;
            }
        }
        return sb.toString();
    }

    /**
     * 统计每个字符出现的频率
     *
     * @param reader A character stream whose source is a string.
     * @return 字符-频率映射表
     */
    private static Map<Character, Integer> countCharTimes(StringReader reader) throws IOException {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = new char[1024];
        int len;
        while ((len = reader.read(chars)) != -1) {
            for (int i = 0; i < len; i++) {
                char c = chars[i];
                Integer times = map.get(c);
                map.put(c, times == null ? 1 : times + 1);
            }
        }
        return map;
    }

    /**
     * 统计每个字符出现的频率
     *
     * @param chars 待编码字符数组
     * @return 字符-频率映射表
     */
    private static Map<Character, Integer> countCharTimes(char[] chars) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            Integer times = map.get(c);
            map.put(c, times == null ? 1 : times + 1);
        }
        return map;
    }

    /**
     * 构建Huffman树
     *
     * @param char2times 字符-频率映射表
     * @return Huffman树的根节点
     */
    private static HuffmanNode buildTree(@NotNull Map<Character, Integer> char2times) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        Set<Character> keys = char2times.keySet();
        for (Character key : keys) {
            queue.add(new HuffmanNode(key, char2times.get(key)));
        }
        while (queue.size() > 1) {
            HuffmanNode key1 = queue.poll();
            HuffmanNode key2 = queue.poll();
            HuffmanNode key3 = new HuffmanNode(key1, key2);
            queue.add(key3);
        }
        return queue.poll();
    }

    /**
     * 打印 Huffman 树
     *
     * @param root Huffman树的根节点
     */
    private static void printTree(HuffmanNode root) {
        HuffmanNode node = root;
        Log.debug("tree=%s\n", node);
        Queue<HuffmanNode> queue = new LinkedList<>();
        queue.add(node);
        Log.debug(node.toSimpleString());
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                node.left.index = node.index * 2 + 1;
                if (node.right == null) {
                    Log.debug("left=%s\n", node.left.toSimpleString());
                } else {
                    Log.debug("left=%s", node.left.toSimpleString());
                }
            }
            if (node.right != null) {
                queue.add(node.right);
                node.right.index = node.index * 2 + 2;
                if (node.left == null) {
                    Log.debug("right=%s\n", node.right.toSimpleString());
                } else {
                    Log.debug("\tright=%s\n", node.right.toSimpleString());
                }
            }
        }
    }

    /**
     * 深度遍历
     *
     * @param root HuffmanNode
     * @return 字符-Huffman编码的映射表
     */
    private static Map<Character, String> dfs(@NotNull HuffmanNode root) {
        root.code = "";
        Map<Character, String> map = new HashMap<>();
        Stack<HuffmanNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            HuffmanNode node = stack.pop();
            if (node.key != null) {
                map.put(node.key, node.code);
            }
            if (node.left != null) {
                node.left.code = node.code + "0";
                stack.push(node.left);
            }
            if (node.right != null) {
                node.right.code = node.code + "1";
                stack.push(node.right);
            }
        }
        return map;
    }

    /**
     * Huffman树节点
     */
    private static class HuffmanNode implements Comparable<HuffmanNode> {
        private final Character key;// 字符
        private final int weight;// 权重
        private int index;// 节点索引
        private String code;// Huffman编码值
        private HuffmanNode left;
        private HuffmanNode right;

        public HuffmanNode(HuffmanNode left, HuffmanNode right) {
            this.key = null;
            this.weight = (left == null ? 0 : left.weight) + (right == null ? 0 : right.weight);
            this.left = left;
            this.right = right;
        }

        public HuffmanNode(Character key, int weight) {
            this.key = key;
            this.weight = weight;
        }

        @Override
        public int compareTo(@NotNull HuffmanNode o) {
            return weight - o.weight;
        }

        @Override
        public String toString() {
            return "HuffmanNode{" +
                    "key=" + key +
                    ", weight=" + weight +
                    (left == null ? "" : ", left=" + left) +
                    (right == null ? "" : ", right=" + right) +
                    '}';
        }

        public String toSimpleString() {
            return "{" +
                    "index=" + index +
                    ", key=" + key +
                    ", weight=" + weight +
                    '}';
        }
    }

}
