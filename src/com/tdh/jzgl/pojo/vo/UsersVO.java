package com.tdh.jzgl.pojo.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 用户VO
 *
 * @author wudb
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-7-20 15:18  use      1.0        1.0 Version
 */
public class UsersVO {
    private String yhdm;
    private String dwdm;
    private String yhid;
    private String yhkl;
    private String yhxm;
    private String yhbm;
    private String yhxb;
    private String csrq;
    private String sfjy;
    private Integer pxh;
    private MultipartFile file;

    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public String getDwdm() {
        return dwdm;
    }

    public void setDwdm(String dwdm) {
        this.dwdm = dwdm;
    }

    public String getYhid() {
        return yhid;
    }

    public void setYhid(String yhid) {
        this.yhid = yhid;
    }

    public String getYhxm() {
        return yhxm;
    }

    public void setYhxm(String yhxm) {
        this.yhxm = yhxm;
    }

    public String getYhbm() {
        return yhbm;
    }

    public void setYhbm(String yhbm) {
        this.yhbm = yhbm;
    }

    public String getYhxb() {
        return yhxb;
    }

    public void setYhxb(String yhxb) {
        this.yhxb = yhxb;
    }

    public String getSfjy() {
        return sfjy;
    }

    public void setSfjy(String sfjy) {
        this.sfjy = sfjy;
    }

    public Integer getPxh() {
        return pxh;
    }

    public void setPxh(Integer pxh) {
        this.pxh = pxh;
    }

    public String getYhkl() {
        return yhkl;
    }

    public void setYhkl(String yhkl) {
        this.yhkl = yhkl;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "UsersVO{" +
                "yhdm='" + yhdm + '\'' +
                ", dwdm='" + dwdm + '\'' +
                ", yhid='" + yhid + '\'' +
                ", yhkl='" + yhkl + '\'' +
                ", yhxm='" + yhxm + '\'' +
                ", yhbm='" + yhbm + '\'' +
                ", yhxb='" + yhxb + '\'' +
                ", csrq='" + csrq + '\'' +
                ", sfjy='" + sfjy + '\'' +
                ", pxh=" + pxh +
                ", file=" + file +
                '}';
    }
}
