package com.chinasofti.rcloud.common;
/**
 * 
 * @description：restsdk 统一处理异常
 * @creater：zhyh   
 * @createrTime：2014年5月13日 下午2:22:13   
 * @modifier：zhyh   
 * @modifyTime：2014年5月13日 下午2:22:13   
 * @changeNote：   
 * @version 
 *
 */
public class RCloudRestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	public RCloudRestException() {
	    super();
	}
	  
	public RCloudRestException(String msg, Throwable cause) {
	    super(msg);
	    super.initCause(cause);  
	}
	public RCloudRestException(String msg) {
	    super(msg);
	}
	
	public RCloudRestException(Throwable cause) {
	    super();
	    super.initCause(cause);
	}
}
