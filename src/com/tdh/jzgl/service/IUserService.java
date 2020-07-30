package com.tdh.jzgl.service;

import com.alibaba.fastjson.JSONObject;
import com.tdh.jzgl.pojo.vo.UsersVO;

/**
 * 用户相关接口定义层
 */
public interface IUserService {
    /**
     * 初始化性别下拉框
     * @return xml
     * @author liming
     * @date 2020-07-20
     */
    String getxbList() throws Exception;

    /**
     * 根据用户信息获取人员列表
     * @return xml
     * @author liming
     * @date 2020-07-20
     */
    String queryUserByYhxx(String yhxx, String yhbm) throws Exception;

    /**
     * 根据用户代码删除用户
     * @return 操作状态
     * @param yhdm 用户代码
     * @author liming
     */
    String deleteUserByYhdm(String yhdm) throws Exception;

    /**
     * 根据用户代码删除用户
     * @return 操作状态
     * @param listUser 用户代码
     * @author liming
     */
    String deleteUsers(String listUser);

    /**
     * 保存用户信息
     * @return ComResultVO 公共返回对象
     * @author liming
     * @date 2020-07-20
     */
    JSONObject saveUser(String type,UsersVO usersVO) throws Exception;

    /**
     * 查看用户信息
     * @param yhdm
     * @return
     * @throws Exception
     */
    JSONObject viewUser(String yhdm) throws Exception;
}
