/**
 * @Title: SessionFilter.java
 * @Package: com.chinasofti.rcloud.web.common
 * @Author: sunlulu
 * @Date: 2014年7月10日 下午2:14:33
 * @Version: V1.0
 */
package com.chinasofti.rcloud.web.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.chinasofti.rcloud.web.common.LoginUtil;

/**
 * @ClassName: SessionFilter
 * @Description: 往LoginUtil中的ThreadLocal 存放session
 * @Author: sunlulu
 * @Date: 2014年7月10日 下午2:14:33
 *
 */
public class SessionFilter implements Filter{

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try{
			LoginUtil.setSession(((HttpServletRequest)request).getSession(true));//往ThreadLocal放置session信息
			chain.doFilter(request,response);
		}catch (Exception e) {
			LoginUtil.clearSession();//清空ThreadLocal中session;
			e.printStackTrace();
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
	
}

