/**
 * @Title: LoginUtil.java
 * @Package: com.chinasofti.rcloud.web.common
 * @Author: sunlulu
 * @Date: 2014年7月10日 下午2:20:18
 * @Version: V1.0
 */
package com.chinasofti.rcloud.web.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.chinasofti.rcloud.common.client.CFClientResource;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;

/**
 * @ClassName: LoginUtil
 * @Description: TODO
 * @Author: sunlulu
 * @Date: 2014年7月10日 下午2:20:18
 *
 */
public class LoginUtil {
	private static Logger logger=Logger.getLogger(LoginUtil.class);
	// 通过把session放到ThreadLocal中，实现人员信息的static方式获取
	public static ThreadLocal<HttpSession> sessions = new ThreadLocal<HttpSession>();
	
	private static final String COOKIE_PATH = "/";
	private static final int COOKIE_MAX_AGE = -1;
	private static final String COOKIE_SEPERATOR = "|";
	private static final String COOKIE_NAME="RCLOUD";
	
	/**
	 * 设置session到ThreadLocal中
	 * @param session
	 */
	public static void setSession(HttpSession session){
		sessions.set(session);
	}
	
	/**
	 * 初始用户信息、用户权限到当前用户session中
	 * @param session
	 * @param tregUser
	 * @param resMap
	 */
	public static void initUserInfo(UserEntityExt user,HttpServletResponse response) throws Exception{
		HttpSession session = sessions.get();
		session.setAttribute(CommonConstant.USER_INFO, user);//把用户信息放到session中
		String[] content = new String[] {user.getUserId(),user.getcFClientToken().getAcessToken().access_token,String.valueOf(user.getcFClientToken().getAcessToken().getExpiresTime())};
		response.addCookie(cookieGenerator(content));
	}

	/**
	 * 清空ThreadLocal中的session
	 */
	public static void clearSession(){
		sessions.set(null);
	}
	/**
	 * 
	 *@Description: 获取当前session
	 *@return
	 *@return HttpSession
	 *@throws
	 */
	public static HttpSession getSession(){
		HttpSession session = sessions.get();
		if(session == null) return null;
		return session;
	}
	
	/**
	 * 获取当前登陆用户信息
	 * @return
	 */
	public static UserEntityExt getUser(){
		HttpSession session = sessions.get();
		if(session == null) return null;
		return (UserEntityExt) session.getAttribute(CommonConstant.USER_INFO);
	}
	
	/**
	 * 获取登陆用户的UUID
	 * @return
	 */
	public static String getUserId(){
		UserEntityExt user = getUser();//获取登陆用户信息
		if(user == null) return null;
		return user.getUserId();
	}
	
	/**
	 * 获取登陆用户名称
	 * @return
	 */
	public static String getUserName(){
		UserEntityExt user = getUser();//获取登陆用户信息
		if(user == null) return null;
		return user.getUserName();
	}
	
	/**
	 * 生成rcloud平台共享信息用的cookie
	 * @return
	 */
	public static Cookie cookieGenerator(String[] cookieTokens)throws Exception{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cookieTokens.length; i++) {
			sb.append(cookieTokens[i]);
			if (i < cookieTokens.length - 1) {
				sb.append(COOKIE_SEPERATOR);
			}
		}
		String value = sb.toString();
		sb = new StringBuilder(new String(new Base64().encode(value.getBytes())));
//		while (sb.charAt(sb.length() - 1) == '=') {
//			sb.deleteCharAt(sb.length() - 1);
//		}
		Cookie cookie = new Cookie(COOKIE_NAME, sb.toString());
		cookie.setPath(COOKIE_PATH);
		cookie.setMaxAge(COOKIE_MAX_AGE);
		cookie.setDomain(CFClientResource.PAAS_PLATFORM_COOKIE_DOMAIN);
		return cookie;
	}
	
	/**
	 * 删除rcloud平台共享信息用的cookie
	 * @return
	 */
	public static void deleteCookie(HttpServletResponse response){
		Cookie cookie = new Cookie(COOKIE_NAME,"");
		cookie.setPath(COOKIE_PATH);
		cookie.setMaxAge(0);
		cookie.setDomain(CFClientResource.PAAS_PLATFORM_COOKIE_DOMAIN);
		response.addCookie(cookie);     
    }
	
}
