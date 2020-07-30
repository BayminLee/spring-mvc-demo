package com.tdh.jzgl.service;

import com.alibaba.fastjson.JSONArray;

public interface IDeptService {

    String getBmlist() throws Exception;
    JSONArray getBmTree() throws Exception;
    String removeDept(String[] bmdm);
}
