package cn.lzh.algorithm;

import cn.lzh.utils.Log;

import java.util.Arrays;

/**
 * 8皇后问题（回溯算法）
 */
public final class Queen8 {
    public static final int QUEEN_NUMBER = 8;
    private static int index;

    public static void main(String[] args) {
        fill(new int[QUEEN_NUMBER], 0);
    }

    /**
     * 求解所有8皇后，并打印结果
     * @param queens 8皇后棋盘
     * @param row 行
     */
    private static void fill(int[] queens, int row) {
        if (row == QUEEN_NUMBER) {
            printQueen(queens);
            return;
        }
        for (int col = 0; col < QUEEN_NUMBER; col++) {
            if (validate(queens, row, col)) {
                queens[row] = col;
                fill(queens, row + 1);
            }
        }
    }

    /**
     * 验证8皇后规则：行、列、对角线上有且仅有一个皇后
     * @param queens 8皇后棋盘
     * @param row 行
     * @param col 列
     * @return 当前棋盘是否符合规则
     */
    private static boolean validate(int[] queens, int row, int col) {
        int leftUpCol = col - 1, rightUpCol = col + 1;
        int value;
        for (int i = row - 1; i >= 0; i--) {
            value = queens[i];
            if (value == col || value == leftUpCol || value == rightUpCol) {
                return false;
            }
            leftUpCol--;
            rightUpCol++;
        }
        return true;
    }

    /**
     * 打印8皇后棋盘：<br/>
     * 索引为行，值为列，如queens[0] = 1，则第1行第2列有一个皇后
     * @param queens 8皇后棋盘
     */
    private static void printQueen(int[] queens) {
        index++;
        Log.info("第%d种8皇后：%s\n", index, Arrays.toString(queens));
        for (int i = 0; i < QUEEN_NUMBER; i++) {
            for (int j = 0; j < QUEEN_NUMBER; j++) {
                System.out.print(queens[i] == j ? "Q\t" : "*\t");
            }
            System.out.println();
        }
    }

}
