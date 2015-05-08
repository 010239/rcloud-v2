package com.chinasofti.rcloud.web.tag;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.chinasofti.rcloud.common.SystemParametersCacheService;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;
import com.chinasofti.rcloud.web.common.CommonConstant;

public class PermissionTag extends TagSupport implements ApplicationContextAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5400022412586518893L;
	private Logger logger = Logger.getLogger(PermissionTag.class);
	
	private String permissionName;
	private int value;
	
	private PageContext pageContext;
	private ApplicationContext applicationContext;
	
	@Override
	public int doStartTag() throws JspException {
		HttpSession session = pageContext.getSession();
		logger.info("权限:" + permissionName);
		
		SystemParametersCacheService systemParameters = 
				(SystemParametersCacheService) applicationContext.getBean("SystemParametersCacheService");
		
		UserEntityExt user = (UserEntityExt)session.getAttribute(CommonConstant.USER_INFO);
		String roleCode = user.getRoleCode();
		Map roleMap = systemParameters.getRoleToPermissionMap();
		Map perMap = (Map)roleMap.get(roleCode);
		if(perMap.containsKey(permissionName)){//权限足够
			this.value = 1;
		} else {
			this.value = 0;
		}
		this.value = 1;
		
		return EVAL_PAGE;
	}
	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);
		this.pageContext = pageContext;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
}
