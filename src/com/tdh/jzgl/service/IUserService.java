package com.tdh.jzgl.service;

import com.alibaba.fastjson.JSONObject;
import com.tdh.jzgl.entity.TUser;
import com.tdh.jzgl.pojo.vo.UsersVO;

import java.util.List;

/**
 * 用户相关接口定义层
 */
public interface IUserService {
    /**
     * 根据用户信息获取人员列表
     * @return List<TUser> 用户链表
     * @author wudb
     * @date 2020-07-20
     */
    List<TUser> queryUserByYhxx(UsersVO usersVO);

    /**
     * 保存用户信息
     * @return ComResultVO 公共返回对象
     * @author wudb
     * @date 2020-07-20
     */
    JSONObject saveUser(UsersVO usersVO) throws Exception;
}
