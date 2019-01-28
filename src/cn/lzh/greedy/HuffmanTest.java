package cn.lzh.greedy;


import cn.lzh.utils.Log;

/**
 * 贪心算法的应用
 */
public final class HuffmanTest {

    private HuffmanTest(){
        throw new AssertionError("cannot instantiation");
    }

    static {
        Log.isDebug = true;
    }

    public static void main(String[] args) {
        testHuffman("测试Huffman编码与解码");
    }

    /**
     * huffman 编码
     */
    private static void testHuffman(String text) {
        Huffman huffman = new Huffman();
        String encode = huffman.encode(text);
        Log.info("huffman.encode(%s)=%s\n", text, encode);
        Log.info("huffman.decode(%s)=%s\n", encode, huffman.decode(encode));
    }

}
