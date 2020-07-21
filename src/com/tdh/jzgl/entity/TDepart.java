package com.tdh.jzgl.entity;

import java.util.List;

/**
 * Description: ADD Description(可选). <br/>
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-7-20 16:26  use      1.0        1.0 Version
 */
public class TDepart {
    private String bmdm;
    private String fbmdm;
    private String dwdm;
    private String bmid;
    private String bmmc;
    private String sfjy;
    private Short pxh;
    private List<TDepart> childDepartList;

    public String getBmdm() {
        return bmdm;
    }

    public void setBmdm(String bmdm) {
        this.bmdm = bmdm;
    }

    public String getFbmdm() {
        return fbmdm;
    }

    public void setFbmdm(String fbmdm) {
        this.fbmdm = fbmdm;
    }

    public String getDwdm() {
        return dwdm;
    }

    public void setDwdm(String dwdm) {
        this.dwdm = dwdm;
    }

    public String getBmid() {
        return bmid;
    }

    public void setBmid(String bmid) {
        this.bmid = bmid;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getSfjy() {
        return sfjy;
    }

    public void setSfjy(String sfjy) {
        this.sfjy = sfjy;
    }

    public Short getPxh() {
        return pxh;
    }

    public void setPxh(Short pxh) {
        this.pxh = pxh;
    }

    public List<TDepart> getChildDepartList() {
        return childDepartList;
    }

    public void setChildDepartList(List<TDepart> childDepartList) {
        this.childDepartList = childDepartList;
    }
}
