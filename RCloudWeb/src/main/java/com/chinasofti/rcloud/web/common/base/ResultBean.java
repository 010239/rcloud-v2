package com.chinasofti.rcloud.web.common.base;

public class ResultBean {
	
	private String desc = "";//描述信息
	private String code = "0";//0表示成功，其他数字表示各种失败
	private Object result = null;
	
	public ResultBean() {
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
