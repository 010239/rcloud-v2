package com.chinasofti.rcloud.common;

import com.chinasofti.rcloud.common.constants.ExceptionContants;

/**
 * Created by mupeng on 14-4-25.
 */
public class RCloudException extends Exception  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5039891352386332066L;
	
	private String errorCode;

	public RCloudException() {
        super();
    }

    public RCloudException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
    
    public RCloudException(String errorCode) {
    	super(ExceptionContants.ERROR_MSG.get(errorCode));
    	this.errorCode = errorCode;
    }

    public RCloudException(Throwable cause) {
        super(cause);
    }

    public RCloudException(String msg, Throwable cause) {
        super(msg, cause);
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
    
}
