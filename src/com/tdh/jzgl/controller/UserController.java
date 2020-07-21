package com.tdh.jzgl.controller;

import com.tdh.jzgl.pojo.vo.ComResultVO;
import com.tdh.jzgl.pojo.vo.UsersVO;
import com.tdh.jzgl.service.IUserService;
import com.tdh.jzgl.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * 根据用户信息获取人员列表
     * @return ComResultVO 公共返回对象
     * @author wudb
     * @date 2020-07-20
     */
    @RequestMapping("/queryUserByYhxx.do")
    @ResponseBody
    public ComResultVO queryUserByYhxx(UsersVO usersVO) {
        try {
            return success(userService.queryUserByYhxx(usersVO));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return fail("获取用户信息异常！");
        }
    }

    /**
     * 保存用户信息
     * @return ComResultVO 公共返回对象
     * @author wudb
     * @date 2020-07-20
     */
    @RequestMapping("/saveUser.do")
    @ResponseBody
    public ComResultVO saveUser(UsersVO usersVO) {
        LOGGER.info("保存用户信息----->入参：{}", usersVO.toString());
        try {
            return success(userService.saveUser(usersVO));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return fail("获取用户信息异常！");
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
