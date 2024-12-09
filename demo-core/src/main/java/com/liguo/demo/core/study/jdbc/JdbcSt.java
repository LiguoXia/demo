package com.liguo.demo.core.study.jdbc;

import java.sql.*;

/**
 * SPI 即Service Provider Interface ，字面意思就是：“服务提供者的接口”，
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/17 14:42
 * @since 0.0.1
 */
public class JdbcSt {
    /**
     * 使用JDBC连接并操作mysql数据库
     * url: jdbc:mysql://192.168.18.22:3306/demo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8
     *       username: liguo
     *       password: xlg123456
     */
    public static void main(String[] args) {
        // 数据库驱动类名的字符串
        String driver = "com.mysql.cj.jdbc.Driver";
        // 数据库连接串
        String url = "jdbc:mysql://192.168.18.22:3306/demo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8";
        // 用户名
        String username = "liguo";
        // 密码
        String password = "xlg123456";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 1、加载数据库驱动（ 成功加载后，会将Driver类的实例注册到DriverManager类中）
            // 使用SPI扩展来加载具体的驱动，我们在Java中写连接数据库的代码的时候，不需要再使用Class.forName("com.mysql.jdbc.Driver")来加载驱动了
            // DriverManager加载的时候调用了ServiceLoader.load
            /**
             * @see DriverManager#static
             */
            // java.sql.Driver 参考文件
            // Class.forName(driver);
            // 2、获取数据库连接
            conn = DriverManager.getConnection(url, username, password);
            // 3、获取数据库操作对象
            stmt = conn.createStatement();
            // 4、定义操作的SQL语句
            String sql = "select * from imc_user where user_id = 1";
            // 5、执行数据库操作
            rs = stmt.executeQuery(sql);
            // 6、获取并操作结果集
            while (rs.next()) {
                System.out.println(rs.getInt("user_id"));
                System.out.println(rs.getString("user_nick"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7、关闭对象，回收数据库资源
            if (rs != null) { //关闭结果集对象
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) { // 关闭数据库操作对象
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) { // 关闭数据库连接对象
                try {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
