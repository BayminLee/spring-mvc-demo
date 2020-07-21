package com.tdh.jzgl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 视图层控制器
 *
 * @author wudb
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-7-20 19:39  use      1.0        1.0 Version
 */
@Controller
@RequestMapping("/webapp")
public class JspViewController {

    /**
     * 打开主页面
     * @return
     * @throws Exception
     * @author wudb
     * @date 2020-07-20
     */
    @RequestMapping("/user.do")
    public ModelAndView getUser() {
        ModelAndView mav = new ModelAndView();
        //webapp/userManager/user.jsp
        mav.setViewName("/userManager/user");
        return mav;
    }

    /**
     * 打开用户信息页面
     * @return
     * @throws Exception
     * @author wudb
     * @date 2020-07-20
     */
    @RequestMapping("/getUserForm.do")
    public ModelAndView getUserForm() {
        ModelAndView mav = new ModelAndView();
        //webapp/userManager/form.jsp
        mav.setViewName("/userManager/form");
        return mav;
    }
}
