package com.chinasofti.rcloud.web.interceptor;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chinasofti.rcloud.common.SystemParametersCacheService;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.RequestMappingName;

public class PagePermissionInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = Logger
			.getLogger(PagePermissionInterceptor.class);
	
	private static final String LOGIN_URI = "/static/extends/login.jsp";
	private static final String ACCESS_DENY_URI = "/static/extends/404.jsp";
	
	@Autowired
	SystemParametersCacheService systemParameters;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod method = (HandlerMethod)handler;
		RequestMappingName requestMappingName = method.getMethodAnnotation(RequestMappingName.class);
		String value = requestMappingName.value();
		if(!CommonConstant.ROLE_PERMISSION_COMMON.equals(value)){
			//判断是否已经登录
			HttpSession session = request.getSession();
			UserEntityExt user = (UserEntityExt)session.getAttribute(CommonConstant.USER_INFO);
			if (user == null) {
				String currenturl = request.getRequestURI();
				String context = request.getContextPath();
				response.sendRedirect(context+LOGIN_URI+"?currenturl="+URLEncoder.encode(currenturl));
				return false;
			}else{
				String roleCode = user.getRoleCode();
				String context = request.getContextPath();
				Map roleMap = systemParameters.getRoleToPermissionMap();
				Map perMap = (Map)roleMap.get(roleCode);
				if(perMap.containsKey(value)){//权限足够
					return true;
				}else{//跳转错误页面
					session.setAttribute("errMsg", "您的权限不足!");
					response.sendRedirect(context+ACCESS_DENY_URI);
				}
			}
			
		}
		return true;
	}

}
