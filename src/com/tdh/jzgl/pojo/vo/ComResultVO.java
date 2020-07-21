package com.tdh.jzgl.pojo.vo;

import com.tdh.jzgl.comstant.ComConstant;

/***
 * 
 * @description 数据响应结果统一封装
 * 
 * @author  zhangChao
 * 
 * @date	2018-9-20 10:58:14
 *
 * @param <T>
 */
public class ComResultVO<T>
{
	/**状态：success-成功，fail-失败，error-异常*/ 
	private String status;
	
	/**提示信息*/
	private String message;
	
	/**响应数据信息*/
	private T Data;
	
	/**异常时异常信息*/
	private String errorMsg;

	public ComResultVO(){
		
	}
	/**
	 * @param status
	 * @param message
	 * @return
	 * @Description 标准构造方法
	 * @author 李训好
	 * @date 2020/6/23 12:38
	 */
	public ComResultVO(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public ComResultVO(String status, String message , T Data) {
		super();
		this.status = status;
		this.message = message;
		this.Data =Data;
	}
	public static ComResultVO error(String msg) {
		return new ComResultVO(ComConstant.STATUS.FAIL.getCode(), msg);
	}

	public static ComResultVO error(String code, String msg) {
		return new ComResultVO(code, msg);
	}

	public static ComResultVO ok() {
		return new ComResultVO(ComConstant.STATUS.SUCCESS.getCode(), "");
	}

	public static ComResultVO ok(String code) {
		return new ComResultVO(code, "");
	}

	public static ComResultVO ok(Object object) {
		return new ComResultVO(ComConstant.STATUS.SUCCESS.getCode(), "",object);
	}

	public ComResultVO(ComConstant.STATUS status){
		setStatus(status);
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(ComConstant.STATUS status) {
		this.status = status.getCode();
		this.message = status.getDesc();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return Data;
	}

	public void setData(T data) {
		Data = data;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
