package com.chinasofti.rcloud.web.common.base;

public class BaseResult {
	
	private boolean success;
	private int code;
	private String message;
	private String result;
	private int origCode;
	private String origMessage;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getOrigCode() {
		return origCode;
	}

	public void setOrigCode(int origCode) {
		this.origCode = origCode;
	}

	public String getOrigMessage() {
		return origMessage;
	}

	public void setOrigMessage(String origMessage) {
		this.origMessage = origMessage;
	}

	@Override
	public String toString() {
		return "PortalRestfulResult:\n\tsuccess=" + success + "\n\tcode="
				+ code + "\n\tmessage=" + message + "\n\tresult=" + result
				+ "\n\torigCode=" + origCode + "\n\torigMessage=" + origMessage;
	}
	
}
