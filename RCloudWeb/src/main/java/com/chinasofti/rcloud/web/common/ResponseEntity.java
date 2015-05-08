package com.chinasofti.rcloud.web.common;

/**
 * 所有的REST API返回对象
 *
 * Created by mupeng on 14-4-17.
 */
public class ResponseEntity<T> {
    // 返回对象
    private T entity;
    // 请求状态, 默认为200
    private String status = CommonConstant.STATUS_SUCCESS;
    // 错误信息， 默认为null
    private String errorMessage;
    
    private String errorCode;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

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
