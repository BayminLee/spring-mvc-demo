package com.tdh.jzgl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tdh.jzgl.entity.TUser;
import com.tdh.jzgl.pojo.vo.UsersVO;
import com.tdh.jzgl.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 根据用户信息获取人员列表
     * @return List<TUser> 用户链表
     * @author wudb
     * @date 2020-07-20
     */
    @Override
    public List<TUser> queryUserByYhxx(UsersVO usersVO) {
        return null;
    }

    /**
     * 保存用户信息
     * @return ComResultVO 公共返回对象
     * @author wudb
     * @date 2020-07-20
     */
    @Override
    @Transactional(value = "transactionManager")
    public JSONObject saveUser(UsersVO usersVO) throws Exception {
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
        hibernateTemplate.saveOrUpdate(tUser);
        return null;
    }
}
