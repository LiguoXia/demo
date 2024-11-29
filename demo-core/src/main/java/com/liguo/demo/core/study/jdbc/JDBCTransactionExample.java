package com.liguo.demo.core.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/17 21:00
 * @since 0.0.1
 */
public class JDBCTransactionExample {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // 1. 加载数据库驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 建立数据库连接
            String url = "jdbc:mysql://localhost:3306/your_database";
            String user = "your_username";
            String password = "your_password";
            connection = DriverManager.getConnection(url, user, password);

            // 3. 设置事务
            connection.setAutoCommit(false);

            // 4. 执行SQL语句
            try {
                // 假设有两个更新操作，这里只是示例
                updateData(connection, "UPDATE your_table SET column1 = value1 WHERE condition");
                updateData(connection, "UPDATE your_table SET column2 = value2 WHERE condition");

                // 如果没有异常，提交事务
                connection.commit();
                System.out.println("Transaction committed successfully!");
            } catch (SQLException e) {
                // 发生异常，回滚事务
                connection.rollback();
                System.err.println("Transaction rolled back due to exception: " + e.getMessage());
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭数据库连接
            if (connection != null) {
                try {
                    // 恢复自动提交模式
                    connection.setAutoCommit(true);
                    // 关闭连接
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void updateData(Connection connection, String sql) throws SQLException {
        // 使用PreparedStatement执行更新操作
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }
    }
}
