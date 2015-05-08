package com.chinasofti.rcloud.web.common;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.web.common.base.jackson.JacksonMap;

/**
 * Created by mupeng on 14-4-25.
 */
@Controller
public class BasicController {

	private static Logger logger = Logger.getLogger(BasicController.class);

	protected JacksonMap jacksonMapper = JacksonMap.getInstance();

	private String runException = "服务器发生未知异常!!!";

	/**
	 * 处理Rest API请求产生的业务异常。
	 *
	 * @param re
	 * @param responseEntity
	 */
	protected void handleBusinessExceptionByJson(RCloudException re,
			ResponseEntity<?> responseEntity) {
		responseEntity.setErrorMessage(re.getMessage());
		responseEntity.setErrorCode(re.getErrorCode());
		responseEntity.setStatus(CommonConstant.STATUS_BUSINESS_EXCEPTION);
	}

	/**
	 * 处理Rest API请求产生的服务器异常。
	 *
	 * @param e
	 * @param responseEntity
	 */
	protected void handleServerExceptionByJson(Exception e,
			ResponseEntity<?> responseEntity) {
		responseEntity.setErrorMessage(this.runException);
		responseEntity.setStatus(CommonConstant.STATUS_SYSTEM_EXCEPTION);
		logger.error("后台异常：", e);
	}

	/**
	 * 处理页面跳转之前产生的业务异常。
	 *
	 * @param re
	 */
	protected String handleBusinessExceptionByPage(RCloudException re) {
		HttpSession session = LoginUtil.getSession();
		session.setAttribute("errMsg", re.getMessage());
		logger.error("后台异常：", re);
		return "404";
	}

	/**
	 * 处理页面跳转之前产生的服务器异常。
	 *
	 * @param e
	 */
	protected String handleServerExceptionByPage(Exception e) {
		HttpSession session = LoginUtil.getSession();
		session.setAttribute("errMsg", this.runException);
		logger.error("后台异常：", e);
		return "404";
	}
}
