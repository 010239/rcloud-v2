package com.chinasofti.rcloud.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.chinasofti.rcloud.web.common.ResponseEntity;
import com.google.gson.Gson;

public class RestApiPermissionInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = Logger
			.getLogger(RestApiPermissionInterceptor.class);
	
	@Autowired
	SystemParametersCacheService systemParameters;

	private static final String ACCESS_DENY_MSG = "对不起，您没有访问权限！";
	private static final String SESSION_PROMPT_MSG = "您处于离线状态，请登录！";
	private static final String RESPONSE_ENCODING = "UTF-8";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod method = (HandlerMethod)handler;
		RequestMappingName requestMappingName = method.getMethodAnnotation(RequestMappingName.class);
		String value = requestMappingName.value();
		if(!CommonConstant.ROLE_PERMISSION_COMMON.equals(value)){			
			//判断是否已经登录
			HttpSession session = request.getSession();
			// 判断session是否已过期
			UserEntityExt user = (UserEntityExt)session.getAttribute(CommonConstant.USER_INFO);
			if (user == null) {
				this.handleAccessDeny(response, "999999", SESSION_PROMPT_MSG);
				return false;
			}else{
				String roleCode = user.getRoleCode();
				Map roleMap = systemParameters.getRoleToPermissionMap();
				Map perMap = (Map)roleMap.get(roleCode);
				if(perMap.containsKey(value)){//权限足够
					return true;
				}else{//跳转错误页面
					this.handleAccessDeny(response, "999999", ACCESS_DENY_MSG);
				}
			}
			
			
		}
		return true;
	}

	private void handleAccessDeny(HttpServletResponse response, String status,
			String errorMessage) throws IOException {
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>();
		responseEntity.setStatus(status);
		responseEntity.setErrorMessage(errorMessage);
		Gson gson = new Gson();
		String str = gson.toJson(responseEntity);
		response.setContentType("application/json");
		response.setCharacterEncoding(RESPONSE_ENCODING);
		PrintWriter pw = response.getWriter();
		pw.write(str);
		pw.flush();
		pw.close();
	}

}
