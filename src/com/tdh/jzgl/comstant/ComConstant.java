package com.tdh.jzgl.comstant;

public interface ComConstant
{
	/**
	 * HTTP请求响应状态
	 * 
	 */
	enum STATUS {
		/** 响应状态-成功 */
		SUCCESS("success", "成功"),

		/** 响应状态-异常 */
		FAIL("fail", "失败"),

		/** 响应状态-失败 */
		ERROR("error", "异常");

		String code;// 状态编码
		String desc;// 状态描述

		// 构造方法
		STATUS(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		// 成员方法
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
}
