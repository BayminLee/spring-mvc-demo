package com.tdh.jzgl.controller;

import com.alibaba.fastjson.JSONObject;
import com.tdh.jzgl.comstant.ComConstant;
import com.tdh.jzgl.pojo.vo.ComResultVO;
import com.tdh.jzgl.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 控制类基类【公用封装处理】
 * 
 * @author 		zhangChao
 * 
 * @date		2018-9-20 11:02:49
 * 
 * @version     1.0
 *
 */
public class BaseController 
{
	/**
	 * 获取请求的基础路径
	 * 
	 * @param request
	 * 
	 * @return {java.lang.String}
	 */
	protected String getBasePath(HttpServletRequest request) {
		return CommonUtil.getContextPath(request);
	}
	
	
	/**
	 * 获取成功的ComResultVO
	 * 
	 * @return {ComResultVO}
	 */
	@SuppressWarnings("rawtypes")
	protected ComResultVO getSuccResBean() {
		return new ComResultVO(ComConstant.STATUS.SUCCESS);
	}
	
	/**
	 * 获取带有状态码的ComResultVO
	 * 
	 * @return {ComResultVO}
	 */
	@SuppressWarnings("rawtypes")
	protected ComResultVO getResultBean(ComConstant.STATUS code) {
		ComResultVO crv = new ComResultVO(code);
		return crv;
	}
	
	
	/**
	 * 获取带有状态码和提示信息的ComResultVO
	 * 
	 * @return {ComResultVO}
	 */
	@SuppressWarnings("rawtypes")
	protected ComResultVO getResultBean(ComConstant.STATUS code, String message) {
		ComResultVO crv = new ComResultVO();
		crv.setStatus(code);
		crv.setMessage(message);
		return crv;
	}
	
	
	/**
	 * 获取带有状态码和提示信息、用户数据的ComResultVO
	 * 
	 * @return {ComResultVO}
	 */
	protected ComResultVO<?> getResultBean(ComConstant.STATUS code, String message, Object data) {
		ComResultVO<Object> crv = new ComResultVO<Object> ();
		crv.setStatus(code);
		crv.setMessage(message);
		crv.setData(data);
		return crv;
	}
	
	/**
	 * 返回成功数据
	 * @param message
	 * @param data
	 * @return {ComResultVO}
	 */
	protected ComResultVO<?> success(String message, Object data){
		ComResultVO<Object> crv = new ComResultVO<Object> (ComConstant.STATUS.SUCCESS);
		crv.setMessage(message);
		if (null != data){
			crv.setData(data);
		}
		return crv;
	}
	/**
	 * 返回成功数据
	 * @param data
	 * @return {ComResultVO}
	 */
	protected ComResultVO<?> success(Object data){
		return success("", data);
	}
	/**
	 * 返回成功数据
	 * @param data
	 * @return {ComResultVO}
	 */
	protected ComResultVO<?> success(){
		return success(null);
	}
	/**
	 * 返回失败
	 * @param message
	 * @return {ComResultVO}
	 */
	protected ComResultVO<?> fail(String message){
		ComResultVO<Object> crv = new ComResultVO<Object> (ComConstant.STATUS.FAIL);
		crv.setMessage(message);
		return crv;
	}

	/**
	 * 返回错误
	 * @param message
	 * @return {ComResultVO}
	 */
	protected ComResultVO<?> error(String message){
		ComResultVO<Object> crv = new ComResultVO<Object> (ComConstant.STATUS.ERROR);
		crv.setErrorMsg(message);
		return crv;
	}
	
	protected JSONObject createJson(String key, Object value){
		JSONObject json = new JSONObject();
		json.put(key, value);
		return json;
	}
}
