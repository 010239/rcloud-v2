package com.chinasofti.rcloud.common.constants;

import java.util.HashMap;
import java.util.Map;

public class ExceptionContants {
	
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
	public final static String USER_FROZEN = "账户已被冻结";
//	public final static String AUTH_ERROR = "auth error";
	public final static String AUTH_ERROR = "102";
//	public final static String CHECK_CODE_ERROR = "check code error";
	public final static String CHECK_CODE_ERROR = "103";
	public final static String CHANGE_USER_PASSWORD_ERROR="password change error";
	
	public final static String SERVICE_NOT_EXIST = "104";
	
	public final static String PRODUCTS_NOT_EXIST = "107";
	
	public final static String MESSAGE_NOT_EXIST = "108";
	
	public final static String MESSAGE_PARAM_ERROR = "109";
	
	public final static String NOTICE_NOT_EXIST = "110";
	
	public final static String NOTICE_PARAM_ERROR = "111";
	
	public final static String USER_PWD_ERROR = "911";
	
	/**
	 * 错误描述信息
	 */
    public final static Map<String, String> ERROR_MSG = new HashMap<String, String>(){{
		 put("999999","请求过程中产生未知异常!");
		 put(RTN_APPLICATION_NOT_FOUND, "应用不存在！");
		 put(USER_NOT_EXIST, "用户不存在！");
		 put(SERVICE_NOT_EXIST, "服务不存在！");
		 put(PRODUCTS_NOT_EXIST, "该服务暂时没有相关产品发布!");
		 put(MESSAGE_NOT_EXIST, "该消息已经删除！");
		 put(MESSAGE_PARAM_ERROR, "传入的参数有误！");
		 put(NOTICE_NOT_EXIST, "该公告已经删除！");
		 put(NOTICE_PARAM_ERROR, "传入的参数有误！");
		 put(USER_PWD_ERROR, "用户名或密码错！");
	}};

}
