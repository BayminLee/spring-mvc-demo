package com.tdh.jzgl.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Description: ADD Description(可选). <br/>
 *
 * @author wudb
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-7-13 15:28  use      1.0        1.0 Version
 */
@Deprecated
public class JdbcUtil {
    /**
     * JDBC配置文件路径
     */
    private static final String JDBC_PATH = "web/WEB-INF/config/jdbc.properties";

    private static Properties properties;

    static {
        loadJdbcProperties();
    }

    /**
     * 加载JDBC配置文件
     */
    public static void loadJdbcProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(JDBC_PATH)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConn() throws SQLException, ClassNotFoundException {
        String driver = CommonUtil.trim(properties.getProperty("driver"));
        String url = CommonUtil.trim(properties.getProperty("url"));
        String user = CommonUtil.trim(properties.getProperty("user"));
        String password = CommonUtil.trim(properties.getProperty("password"));
        //1、注册JDBC驱动
        Class.forName(driver);
        //2、获取数据库连接
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 数据库连接回滚
     * @param connection
     */
    public static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭资源
     * @param connection
     * @param pst
     * @param rs
     */
    public static void close(Connection connection, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (pst != null) {
                pst.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
