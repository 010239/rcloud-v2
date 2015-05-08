package com.chinasofti.rcloud.common;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: JsonResult
 * @Description: TODO
 * @author shimeihua
 * @date 2014-9-12 上午11:02:02
 * 
 */
public class JsonResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Log logger = LogFactory.getLog(JsonResult.class);

	public JsonResult() {
		super();
	}
	private String status;// 200 请求正常
	private String errorMessage;// 501 请求异常
	private String errorCode;// 999999 未知异常
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	

}
