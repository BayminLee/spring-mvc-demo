package com.tdh.jzgl.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tdh.jzgl.comstant.ComConstant;
import com.tdh.jzgl.entity.TDepart;
import com.tdh.jzgl.pojo.vo.ComResultVO;
import com.tdh.jzgl.service.IDeptService;
import com.tdh.jzgl.service.IUserService;
import com.tdh.jzgl.util.CommonUtil;
import com.tdh.jzgl.util.FanyiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Override
    public String getBmlist() throws Exception{
//        Map<String,String> bmmap = FanyiUtils.createbmfy();
        Map<String,String> bmmap = new HashMap<String, String>();
        List<TDepart> tDeparts = new ArrayList<TDepart>();
        tDeparts = hibernateTemplate.find("from TDepart u where u.sfjy='0' order by pxh");
        String bmmc = null;
        String bmdm = null;
        for (TDepart tDepart:tDeparts) {
            bmdm = tDepart.getBmdm();
            bmmc = tDepart.getBmmc();
            bmmap.put(bmdm,bmmc);
        }
        StringBuilder bmlist = new StringBuilder();
        bmlist.append("<option value=\"").append("\">").append("</option>");
        for (String key : bmmap.keySet()){
            bmlist.append("<option value=\"").append(key).append("\">").append(bmmap.get(key)).append("</option>");
        }
        return bmlist.toString();
    }

    @Override
    public JSONArray getBmTree() throws Exception{
        JSONArray jsonArray = new JSONArray();
        List<TDepart> tDeparts = new ArrayList<TDepart>();
        tDeparts = hibernateTemplate.find("from TDepart u where u.sfjy='0'" );
        for (TDepart tDepart:tDeparts){
            JSONObject jsonObject = new JSONObject();
            if ("0".equals(tDepart.getFbmdm())){
                jsonObject.put("open","true");
                jsonObject.put("iconSkin","courtIcon");
            } else {
                jsonObject.put("iconSkin","crowdIcon");
            }
            jsonObject.put("name",tDepart.getBmmc());
            jsonObject.put("pId",tDepart.getFbmdm());
            jsonObject.put("id",tDepart.getBmdm());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public String removeDept(String[] nodeIds) {
//        ComResultVO comResultVO = new ComResultVO();
        System.out.println("nodeIds = " + nodeIds);
        String state = null;
        for (int i = 0; i < nodeIds.length; i++) {
            if (CommonUtil.isContainUsers(nodeIds[i])){
                state = "containUser";
                return state;
            } else {
                TDepart depart = new TDepart();
                depart.setBmdm(nodeIds[i]);
                hibernateTemplate.delete(depart);
                state = "success";
            }
        }
        return state;
    }

}

 
 