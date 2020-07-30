package com.tdh.jzgl.controller;

import com.alibaba.fastjson.JSONObject;
import com.tdh.jzgl.pojo.vo.ComResultVO;
import com.tdh.jzgl.pojo.vo.UsersVO;
import com.tdh.jzgl.service.IUserService;
import com.tdh.jzgl.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;
import java.util.UUID;

/**
 * Description: 用户信息相关控制器
 *
 * @author wudb
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-7-20 15:00  use      1.0        1.0 Version
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    /**
     * 初始化性别下拉框
     * @return String
     * @author liming
     * @date 2020-07-20
     */
    @RequestMapping(value = "/getXbList.do",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getxbList() {
        try {
            return userService.getxbList();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ("获取用户信息异常！");
        }
    }
    /**
     * 根据用户信息查询人员列表
     * @return xml数据
     * @author liming
     * @param yhbm 入参：用户部门
     * @param yhxx 入参：用户信息（yhid or yhxm）
     * @date 2020-07-20
     */
    @RequestMapping(value = "/queryUserByYhxx.do",produces = "application/xml; charset=UTF-8")
    @ResponseBody
    public String queryUserByYhxx(String yhxx, String yhbm) {
        LOGGER.info("查询的输入参数为：", yhxx+yhbm);
        try {
            return userService.queryUserByYhxx(yhxx, yhbm);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ("获取用户信息异常！");
        }
    }

    /**
     * 根据用户代码删除用户
     * @param yhdm 入参：用户代码
     * @date 2020-07-20
     */
    @RequestMapping(value = "/deleteUserByYhdm.do")
    @ResponseBody
    public String deleteUserByYhdm(String yhdm) {
        LOGGER.info("要删除的用户代码为：", yhdm);
        try {
            return userService.deleteUserByYhdm(yhdm);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ("删除异常！");
        }
    }

    /**
     * 批量删除
     * @param listUser 入参
     * @return 返回操作状态
     */
    @RequestMapping(value = "/deleteUsers.do")
    @ResponseBody
    public String deleteUsers(String listUser) {
        LOGGER.info("要批量删除的用户代码为：", listUser);
        try {
            return userService.deleteUsers(listUser);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ("批量删除异常！");
        }
    }
    /**
     * 根据用户代码查看信息
     * @date 2020-07-20
     * @param yhdm 用户代码
     * @return 包含用户信息的json对象
     */
    @RequestMapping(value = "/viewUser.do")
    @ResponseBody
    public JSONObject viewUser(String yhdm) {
        LOGGER.info("要查看的用户代码：", yhdm);
        try {
            return userService.viewUser(yhdm);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

/*    @ResponseBody
    @RequestMapping(value = "getUserList.do",produces = "application/xml;charset=UTF-8")
    public List<TUser> getUserList(UsersVO usersVO){
        return userService.queryUserByYhxx(usersVO);
    }*/

    /**
     * 保存用户
     * @return ComResultVO 公共返回对象
     * @author liming
     * @param usersVO 输入的formdate序列化数据，users操作类
     * @date 2020-07-20
     */
    @RequestMapping("/saveUser.do")
    @ResponseBody
    public ComResultVO saveUser(String type,UsersVO usersVO) {
        LOGGER.info("保存用户信息----->入参：{}", usersVO.toString());
        try {
            return success(userService.saveUser(type,usersVO));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return fail("保存异常！");
        }
    }

    /**
     * 文件上传
     * @param request
     * @return
     */
    @RequestMapping("/uploadFile.do")
    @ResponseBody
    public ComResultVO uploadFile(HttpServletRequest request) {
        try {
            MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;

            Map<String,MultipartFile> map = multipartHttpServletRequest.getFileMap();

            for (Map.Entry<String,MultipartFile> entity:map.entrySet())  {
                String path = CommonUtil.getContextPath(request) + File.separator + "temp" + File.separator + UUID.randomUUID();
                File file = new File(path);
                if (!file.exists()) {
                    if (!file.getParentFile().exists()) {
                        file.mkdirs();
                    }
                    file.createNewFile();
                }
                MultipartFile multipartFile = entity.getValue();
                multipartFile.transferTo(file);
                LOGGER.info("文件上传成功！path:{}", path);
            }
            return success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return fail("");
        }
    }
}
