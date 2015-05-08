package com.chinasofti.rcloud.web.common;

import java.util.HashMap;
import java.util.Map;

public class ExceptionContant {
	
	/**
	 * 通用错误返回码
	 */
	public final static String RTN_CODE_ERROR = "999999";//未知异常
	
	public final static String RTN_CODE_SUCCESS = "000000";//成功代码
	
	// 应用操作相关的异常
	public final static String RTN_APPLICATION_NOT_FOUND = "000001";
	
	/**
	 * 用户信息
	 */
//	public final static String USER_NOT_EXIST = "user not exist";
	public final static String USER_NOT_EXIST = "101";
	public final static String USER_EXIST = "此用户名已经存在";
	public final static String EMAIL_EXIST = "此邮箱已注册";
//	public final static String AUTH_ERROR = "auth error";
	public final static String AUTH_ERROR = "102";
//	public final static String CHECK_CODE_ERROR = "check code error";
	public final static String CHECK_CODE_ERROR = "103";
	public final static String CHANGE_USER_PASSWORD_ERROR="password change error";
	
	public final static String SERVICE_NOT_EXIST = "104";
	
	public final static String USER_IS_DELETED = "105";
	
	public final static String USER_IS_FROZEN = "106";
	
	/**
	 * 错误描述信息
	 */
    public final static Map<String, String> ERROR_MSG = new HashMap<String, String>(){{
		 put("999999","请求过程中产生未知异常!");
		 put(RTN_APPLICATION_NOT_FOUND, "应用不存在！");
		 put(USER_NOT_EXIST, "用户不存在！");
		 put(SERVICE_NOT_EXIST, "服务不存在！");
		 put(USER_IS_DELETED, "用户已经被删除！");
		 put(USER_IS_FROZEN, "用户已经被冻结！");
	}};

}
