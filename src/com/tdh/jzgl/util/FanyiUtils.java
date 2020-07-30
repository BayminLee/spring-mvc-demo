package com.tdh.jzgl.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 翻译工具类
 */

public class FanyiUtils {
    private static Map<String,String> BmFanyi = new HashMap<String, String>(); //创建2个map存储code和获得的翻译
    private static Map<String,String> XbFanyi = new HashMap<String, String>();
    /**
     * 翻译标准代码到性别
     * @param
     * @return XbFanyi
     * @author yinyuan
     * @date 2020/7/3
    */
    public static Map<String,String> createxbfy() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            conn = JDBCUtils.getConnection();
            stmt = conn.prepareStatement("SELECT DISTINCT CODE,MC FROM TS_BZDM WHERE KIND = '00003'");
            rs = stmt.executeQuery();
            String code = null; //创建变量存储查询到的结果
            String mc = null;
            while (rs.next()) {
                //获取标准代码和性别名称
                code = rs.getString("CODE");
                mc = rs.getString("MC");
                //放到XbFanyi Map中
                XbFanyi.put(code, mc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn,stmt, rs);
        }

        return XbFanyi;
    }

    /**
     * 得到翻译后的性别
     * @param code
     * @return mc
     * @author yinyuan
     * @date 2020/7/3
    */
    public static String getXb(String code){
        String mc = XbFanyi.get(code);
        return mc;
    }

    /**
     * 翻译部门代码到部门名称
     * @param
     * @return BmFanyi
     * @author yinyuan
     * @date 2020/7/3
    */
    public static Map<String,String> createbmfy() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            conn = JDBCUtils.getConnection();
            stmt = conn.prepareStatement("SELECT DISTINCT BMDM,BMMC FROM T_DEPART WHERE SFJY = '0' order by pxh");
            rs = stmt.executeQuery();
            String bmdm = null;
            String bmmc = null;
            while (rs.next()) {
                //获取部门代码和部门名称
                bmdm = rs.getString("BMDM");
                bmmc = rs.getString("BMMC");
                //放到BmFanyi Map中
                BmFanyi.put(bmdm, bmmc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn,stmt, rs);
        }
        return BmFanyi;
    }

    /**
     * 得到部门名称
     * @param bmdm
     * @return
     * @author yinyuan
     * @date 2020/7/3
    */
    public static String getBmmc(String bmdm){
        String bmmc = BmFanyi.get(bmdm);
        return bmmc;
    }

}
