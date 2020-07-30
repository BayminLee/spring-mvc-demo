package com.tdh.jzgl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: ADD Description(可选). <br/>
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-7-13 15:27  use      1.0        1.0 Version
 */
public class CommonUtil {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    /**
     * TRIM 将字符串对象去空格或者NULL对象变""
     *
     * @param str String对象.
     * @return trim() 2010.7.23 增加判断 字符串 "null"也返回空串
     */
    public static String trim(String str) {
        if (str == null || "null".equals(str) || "undefined".equals(str)) {
            str = "";
        }
        return str.trim();
    }

    public static String trimVal(Object obj) {
        if (obj == null || "null".equals(String.valueOf(obj)) || "undefined".equals(String.valueOf(obj)))
            return "";
        if (obj instanceof String) {
            return obj.toString().trim();
        } else {
            return obj.toString();
        }
    }

    /**
     * 获取http路径
     * @param request
     * @return
     */
    public static String getContextURI(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        return basePath;
    }

    /**
     * form表单提交时,解析参数
     *
     * @param name
     * @param request
     * @return
     */
    public static String getPar(String name, HttpServletRequest request) {
        return trim(request.getParameter(name));
    }

    /**
     * HTTP协议传参时,以UTF-8解析
     *
     * @param name
     * @param request
     * @return
     */
    public static String getPar8(String name, HttpServletRequest request) {
        String val = trim(request.getParameter(name));
        try {
            if (val == null || "null".equals(val)) {
                val = "";
            } else {
                val = java.net.URLDecoder.decode(val, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            val = "";
        }
        return val;
    }

    /**
     * 获取当前应用在服务器上的绝对路径.
     *
     * @param request 请求
     * @return
     */
    public static String getContextPath(HttpServletRequest  request) {
        HttpSession httpSession = request.getSession();
        if (httpSession != null) {
            return httpSession.getServletContext().getRealPath("/");
        } else {
            String rootPath = CommonUtil.class.getResource("/").getPath();
            int pos = rootPath.indexOf("WEB-INF/");
            if (pos > 0) {
                rootPath = rootPath.substring(0, pos);
            } else {
                rootPath = System.getProperty("jzgl.root");
            }
            rootPath = rootPath.replace("/", "\\");
            if (rootPath.startsWith("\\")) {
                rootPath = rootPath.substring(1);
            }
            return rootPath;
        }

    }
    public static String dateFormate (String date, String pattern, String parttern1) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date1 = format.parse(date);
        SimpleDateFormat format1 = new SimpleDateFormat(parttern1);
        return format1.format(date1);

    }
    public static boolean isContainUsers(String yhbm){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try{
            conn = JDBCUtils.getConnection();
            pst = conn.prepareStatement("SELECT COUNT(1) FROM T_USER WHERE YHBM=?");
            pst.setString(1,yhbm);
            rs = pst.executeQuery();
            while (rs.next()){
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,pst,null);
        }
        return count>0?true:false;
    }
}
