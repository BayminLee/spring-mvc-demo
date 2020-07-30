package com.tdh.jzgl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tdh.jzgl.entity.TUser;
import com.tdh.jzgl.pojo.vo.UsersVO;
import com.tdh.jzgl.service.IUserService;
import com.tdh.jzgl.util.CommonUtil;
import com.tdh.jzgl.util.FanyiUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.SchemaOutputResolver;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description: 用户相关接口实现层
 *
 * @author wudb
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-7-20 15:17  use      1.0        1.0 Version
 */

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    /**
     * 根据用户信息获取人员列表
     * @return 查询到的用户的xml
     * @author liming
     * @date 2020-07-20
     */
    @Override
    public String getxbList() throws Exception{
        Map<String,String> xbmap = FanyiUtils.createxbfy();
        StringBuilder xblist = new StringBuilder();
        xblist.append("<option value=\"").append("\">").append("</option>");
        for (String key : xbmap.keySet()){
            xblist.append("<option value=\"").append(key).append("\">").append(xbmap.get(key)).append("</option>");
        }
        return xblist.toString();
    }

    /**
     * 根据用户信息获取人员列表
     * @return 查询到的用户的xml
     * @author liming
     * @date 2020-07-20
     */
    @Override
    public String queryUserByYhxx(String yhxx, String yhbm) throws Exception{
        StringBuilder builder = new StringBuilder();
        List<String> pargs = new ArrayList<String>();
        builder.append("FROM TUser u ");
        if (!"".equals(yhxx)) {
            builder.append(" AND (u.yhid LIKE ? or u.yhxm LIKE ?) ");
            pargs.add("%"+yhxx+"%");
            pargs.add("%"+yhxx+"%");
        }
        if (!"".equals(yhbm)) {
            builder.append(" AND u.yhbm LIKE ? ");
            pargs.add("%"+yhbm+"%");
        }
        builder.append(" ORDER BY pxh");
        String hql = builder.toString().replaceFirst(" AND", " WHERE");
        List<TUser> users = new ArrayList<TUser>();
        users = hibernateTemplate.find(hql,pargs.toArray());
        //两个map分别存放性别和部门的代码和名称
        Map<String,String> xbmap = FanyiUtils.createxbfy();
        Map<String,String> bmmap = FanyiUtils.createbmfy();
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><rows>");
        int i = 1;
        for (TUser tUser : users) {
            xml.append("<row id=\"").append(CommonUtil.trim(tUser.getYhdm())).append("\">");
            xml.append("<cell>0</cell>");
            xml.append("<cell>").append(i++).append("</cell>");
            xml.append("<cell>").append("webapp/userManager/imgs/update.jpg^编辑^javascript:updateUser(\"").append(CommonUtil.trim(tUser.getYhdm())).append("\");^_self</cell>");
            xml.append("<cell>").append("webapp/userManager/imgs/delete.jpg^删除^javascript:deleteUser(\"").append(CommonUtil.trim(tUser.getYhdm())).append("\");^_self</cell>");
            xml.append("<cell>").append("webapp/userManager/imgs/select.jpg^查看^javascript:viewUser(\"").append(CommonUtil.trim(tUser.getYhdm())).append("\");^_self</cell>");
            xml.append("<cell>").append(CommonUtil.trim(tUser.getYhxm())).append("</cell>");
            xml.append("<cell>").append(CommonUtil.trim(tUser.getYhid())).append("</cell>");
            xml.append("<cell>").append(CommonUtil.trim(bmmap.get(tUser.getYhbm()))).append("</cell>");
            xml.append("<cell>").append(CommonUtil.trim(xbmap.get(tUser.getYhxb()))).append("</cell>");
            xml.append("</row>");
        }
        xml.append("</rows>");
        return xml.toString();
    }

    /**
     * 删除单个用户信息
     * @param yhdm
     * @return
     * @throws Exception
     */
    public String deleteUserByYhdm(String yhdm) throws Exception {
        TUser tUser = new TUser();
        tUser.setYhdm(yhdm);
        hibernateTemplate.delete(tUser);
        return "success";
    }

    /**
     * 根据传入的值删除数据库中的多条数据
     * @param listUser 一个String，里面存放要删除的用户代码
     * @return 返回一个表示执行状态的字符串
     */
    public String deleteUsers(String listUser) {
        //System.out.println("listUser = " + listUser);
        String[] arr = listUser.split(",");
        //System.out.println("arr = " + arr);
        for (String str : arr) {
            TUser tUser = new TUser();
            tUser.setYhdm(str);
            hibernateTemplate.delete(tUser);
        }

        return "success";
    }

    /**
     * 保存用户信息
     * @date 2020-07-20
     */
    @Override
    @Transactional(value = "transactionManager")
    public JSONObject saveUser(String type,UsersVO usersVO) throws Exception {
        TUser tUser = new TUser();
        BeanUtils.copyProperties(usersVO, tUser);
        if (usersVO.getFile() != null) {
            tUser.setYhtx(usersVO.getFile().getBytes());
        }
        if (StringUtils.isNotBlank(usersVO.getCsrq())) {
            tUser.setCsrq(usersVO.getCsrq().replaceAll("-", ""));
        }
        tUser.setYhdm("320100" + usersVO.getYhid());
        tUser.setDwdm("320100");
        if ("add".equals(type)){
            hibernateTemplate.save(tUser);
        } else if ("update".equals(type)){
            hibernateTemplate.update(tUser);
        }
        return null;
    }
    /**
     * 根据用户代码查看用户信息
     * @return 查询到的用户json
     * @author liming
     * @date 2020-07-20
     */
    public JSONObject viewUser(String yhdm) throws Exception{
        //用户代码进行解码
        yhdm = URLDecoder.decode(yhdm, "UTF-8");
        JSONObject jsonObject = new JSONObject();
        List<TUser> users = new ArrayList<TUser>();
        users=hibernateTemplate.find("FROM TUser u WHERE u.yhdm=?",yhdm);
        for(TUser user : users) {
            jsonObject.put("YHID",user.getYhid());
            jsonObject.put("YHXM", user.getYhxm());
            jsonObject.put("YHKL", user.getYhkl());
            jsonObject.put("YHXB", user.getYhxb());
            jsonObject.put("SFJY", user.getSfjy());
            jsonObject.put("YHBM", user.getYhbm());
            jsonObject.put("PXH", user.getPxh());

            String csrq = user.getCsrq();
            if(csrq!=null && !"".equals(csrq)){
                csrq = CommonUtil.dateFormate(csrq, "yyyyMMdd", "yyyy-MM-dd");
                jsonObject.put("CSRQ",csrq);
            }

            jsonObject.put("YHTX",user.getYhtx());
        }
        return jsonObject;
    }

}
