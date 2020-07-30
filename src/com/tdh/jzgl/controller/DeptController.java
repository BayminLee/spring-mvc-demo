package com.tdh.jzgl.controller;

import com.alibaba.fastjson.JSONArray;
import com.tdh.jzgl.service.IDeptService;
import com.tdh.jzgl.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IDeptService deptService;

    @RequestMapping(value = "/getBmList.do",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getBmlist() {
        try {
            return deptService.getBmlist();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ("获取部门列表异常！");
        }
    }
    @RequestMapping(value = "/getBmTree.do",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JSONArray getBmTree() {
        try {
            return deptService.getBmTree();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @RequestMapping(value = "/removeDept.do",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String removeDept(String[] nodeIds) {
        try {
            return deptService.removeDept(nodeIds);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return "error";
        }
    }

}

