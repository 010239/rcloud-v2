package com.chinasofti.rcloud.web.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.common.cloudfoundry.UploadToCFIterface;
import com.chinasofti.rcloud.common.cloudfoundry.domain.CFClientToken;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;

@Controller
@RequestMapping(value = "/t1")
public class TestController {
	
	@Autowired
	private UploadToCFIterface uploadToCFIterface;
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value = "/t2", method = RequestMethod.GET)
	@ResponseBody
	public void getRandImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserEntityExt u = LoginUtil.getUser();
		CFClientToken token = u.getcFClientToken();
//		uploadToCFIterface.createApp(new Object(), u, token); //成功
//		uploadToCFIterface.uploadApp(null,token,null,null);                  //成功
//		uploadToCFIterface.getDomainId(token);				  //成功
//		uploadToCFIterface.createRoutes(token); //返回route_guid  //成功
		
//		uploadToCFIterface.bingAppRoutes(token);
		
//		uploadToCFIterface.getRoutesNum("xx", token); //成功
//		uploadToCFIterface.getRoutesNum("web", token);//成功
		
		uploadToCFIterface.stopAppsByUser(u, token);
	}
}