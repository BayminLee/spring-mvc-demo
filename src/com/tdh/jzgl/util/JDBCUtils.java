package com.tdh.jzgl.util;

import java.sql.*;

/**
 * JDBC工具
 * 
 * @author manan
 * @date 2020/7/1 13:40
 */
public class JDBCUtils {

    static String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
    static String className = "oracle.jdbc.driver.OracleDriver";
    static String user = "tdh";
    static String password = "123";

    /**
     * 获得连接
     * 
     * @return java.sql.Connection
     * @author manan
     * @date 2020/7/6 8:55
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // 加载数据库驱动
            Class.forName(className);
            // 获取数据库连接
            connection = DriverManager.getConnection(jdbcUrl, user, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 回滚
     * 
     * @param connection 连接
     * @author manan
     * @date 2020/7/6 8:56
     */
    public static void rollback(Connection connection) {
        try {
            connection.rollback();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     * 
     * @param conn Connection
     * @param ps PreparedStatement
     * @param rs ResultSet
     * @author manan
     * @date 2020/7/6 8:56
     */
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) {
                ps.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
