package com.liguo.demo.core.study.算法题;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/11 20:34
 * @since 0.0.1
 */
public class 二维数组_坐标最小路径 {
    public static int minCost(int[][] grid, int x, int y) {
        // 获取数组的行数和列数
        int rows = grid.length;
        int cols = grid[0].length;

        // 创建二维数组用于保存最小成本路径的累积值
        int[][] dp = new int[rows][cols];

        // 填充第一行和第一列
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 根据动态规划的状态转移方程来填充dp数组
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        // 返回最小成本路径的累积值
        return dp[y][x];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3},
                {4, 8, 2},
                {1, 5, 3}
        };
        int x = 2;
        int y = 2;
        int result = minCost(grid, x, y);
        System.out.println("最小成本路径的值为：" + result);
    }
}
