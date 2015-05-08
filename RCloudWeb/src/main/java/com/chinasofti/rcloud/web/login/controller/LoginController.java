/**
 * @Title: LoginController.java
 * @Package: com.chinasofti.rcloud.web.login.controller
 * @Author: sunlulu
 * @Date: 2014年7月10日 下午1:49:55
 * @Version: V1.0
 */
package com.chinasofti.rcloud.web.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.common.MD5;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.common.client.CFClientResource;
import com.chinasofti.rcloud.dao.OrganizationEntityMapper;
import com.chinasofti.rcloud.dao.UserEntityMapper;
import com.chinasofti.rcloud.domain.OrganizationEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.domain.UserEntityExample;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;
import com.chinasofti.rcloud.portal.login.service.UserService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.ExceptionContant;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;
import com.google.gson.Gson;

/**
 * @ClassName: LoginController
 * @Description: 用户登录
 * @Author: sunlulu.
 * @Date: 2014年7月10日 下午1:49:55
 *
 */
@Controller
public class LoginController extends BasicController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserEntityMapper userMapper;

	@Autowired
	private OrganizationEntityMapper orgMapper;

	private Logger logger = Logger.getLogger(LoginController.class);

	/**
	 * 
	 * @Description: 注销
	 * @param currenturl
	 *            当前退出的链接
	 * @param session
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value = "page/logout", method = RequestMethod.GET)
	public String logout(String currenturl, HttpSession session,
			HttpServletResponse response) {
		LoginUtil.clearSession();// 清空ThreadLocal的session
		session.invalidate();// 注销session
		LoginUtil.deleteCookie(response);// 删除共享信息用的cookie
		String url = CFClientResource.PAAS_PLATFORM_UAA
				+ "/logout.do?redirect=" + currenturl;
		return "redirect:" + url;
	}

	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping("page/console")
	public String console() {
		return "individual_center";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping("rest/login")
	@ResponseBody
	public ResponseEntity<Map> login(
			@RequestParam(value = "username", defaultValue = "") 	String username,
			@RequestParam(value = "password", defaultValue = "") 	String password,
			@RequestParam(value = "code", defaultValue = "") 	 	String code,
			@RequestParam(value = "currenturl", defaultValue = "") 	String currenturl,
			HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity<Map> responseEntity = new ResponseEntity<Map>();
		try {
			HttpSession session = LoginUtil.getSession();
			UserEntityExt user;
			if (CommonConstant.DEFAULT_CHECK_CODE.equals(code)) { // 首次登陆默认验证码1111
				user = userService.login(
						username, 
						password,
						CommonConstant.DEFAULT_CHECK_CODE,
						CommonConstant.DEFAULT_CHECK_CODE,
						System.currentTimeMillis());
			} else {
				/**
				 * 如果用户长时间没有登录,session失效，code为空
				 */
				if ((String) session.getAttribute(CommonConstant.CHECK_CODE) == null // 校验码
						&& session.getAttribute(CommonConstant.CHECK_CODE_CREATE_TIME) == null) {
					responseEntity.setErrorCode(CommonConstant.AUTH_CODE);
					responseEntity.setErrorMessage(CommonConstant.AUTH_CODE_MSG);
					return responseEntity;
				}

				user = userService.login(
						username,
						password,
						code,
						(String) session.getAttribute(CommonConstant.CHECK_CODE),
						(Long) session.getAttribute(CommonConstant.CHECK_CODE_CREATE_TIME));
			}
			if (user != null) {
				LoginUtil.initUserInfo(user, response);

				if (CommonConstant.ORG_DEVLOPER.equals(user.getRoleCode())) {
					UserEntity org = userService.selectByOrgIdAndRole(
							user.getOrganizationId(),
							CommonConstant.ORG_DEVLOPER);
					LoginUtil.getSession().setAttribute(
							CommonConstant.ORG_TEANTID, org.getUserId());
				}

				// 保存cookie
				// Cookie cookie = new Cookie(CommonConstant.COOKIE_TOKEN, user
				// .getcFClientToken().getAcessToken().access_token);
				// cookie.setMaxAge(Integer.parseInt(user.getcFClientToken()
				// .getAcessToken().expires_in));
				// cookie.setPath("/");
				// response.addCookie(cookie);
				Map entityMap = new HashMap();
				if (currenturl != null && !"".equals(currenturl)
						&& !"null".equals(currenturl)) {
					entityMap.put("currenturl", currenturl);
				} else {
					entityMap.put("currenturl", "");
				}
				responseEntity.setEntity(entityMap);
				responseEntity.setErrorMessage(CommonConstant.OPERATE_SUCCESS);
				return responseEntity;
			}
		} catch (RCloudException re) {
			logger.error("登录异常！！！", re);
			responseEntity.setErrorCode(re.getErrorCode());
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			logger.error("登录异常！！！", e);
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * 
	 * @Description: 根据用户名，返回用户信息，如果用户为空返回给前台提示
	 * @param username
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping("rest/checkout")
	@ResponseBody
	public ResponseEntity<UserEntity> toforgetpwd(
			@RequestParam(value = "username", defaultValue = "") String username) {
		ResponseEntity<UserEntity> responseEntity = new ResponseEntity<UserEntity>();
		try {
			UserEntity userEntity = userService.hasUserEntity(username);
			responseEntity.setEntity(userEntity);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			logger.error(e);
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * 
	 * @Description: 向用户发送验证码
	 * @param email
	 * @return
	 * @return ResponseEntity<String>
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping("rest/sendEmail")
	@ResponseBody
	public ResponseEntity<String> sendEmail(
			@RequestParam(value = "email", defaultValue = "") String email) {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>();
		try {
			String code = userService.sendEmail(email);
			HttpSession session = LoginUtil.getSession();
			session.setAttribute(CommonConstant.RANDOM_CODE, code);
			logger.debug("code :" + code);
			responseEntity.setEntity(CommonConstant.OPERATE_SUCCESS);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		}
		return responseEntity;
	}

	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping("rest/modifypwd")
	@ResponseBody
	public ResponseEntity<String> modifypwd(
			@RequestParam(value = "username", defaultValue = "") String username,
			@RequestParam(value = "authcode", defaultValue = "") String authcode,
			@RequestParam(value = "password", defaultValue = "") String password) {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>();
		HttpSession session = LoginUtil.getSession();
		try {
			if (authcode.equals(session
					.getAttribute(CommonConstant.RANDOM_CODE))) {
				UserEntity userEntity = userService.hasUserEntity(username);
				userEntity.setPassword(MD5.GetMD5Code(password));// 设置新密码
				userService.modifyUserPwd(userEntity);// 更新新密
				responseEntity.setEntity(CommonConstant.OPERATE_SUCCESS);
			} else {
				responseEntity.setStatus(CommonConstant.CODE_NUM);
				responseEntity.setEntity(CommonConstant.CODE_ERROR);
			}
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		}
		return responseEntity;
	}

	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping("rest/checkEmailcode")
	@ResponseBody
	public ResponseEntity<String> checkEmailcode(
			@RequestParam(value = "emailcode", defaultValue = "") String emailcode) {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>();
		HttpSession session = LoginUtil.getSession();

		if (emailcode.equals(session.getAttribute(CommonConstant.RANDOM_CODE))) {
			responseEntity.setEntity(CommonConstant.OPERATE_SUCCESS);
		} else {
			responseEntity.setStatus(CommonConstant.CODE_NUM);
			responseEntity.setEntity(CommonConstant.CODE_ERROR);
		}
		return responseEntity;
	}

	/**
	 * 
	 * @Description: 注册独立开发者
	 * @param email
	 * @param password
	 * @return
	 * @return ResponseEntity<String>
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping("rest/personRegist")
	@ResponseBody
	public ResponseEntity<String> personRegist(
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "password", defaultValue = "") String password) {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>();

		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(email);
		userEntity.setUserEmail(email);
		userEntity.setPassword(password);
		try {
			userService.saveUserEntity(userEntity);
			responseEntity.setEntity(CommonConstant.OPERATE_SUCCESS);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * 
	 * @Description: 注册机构管理员
	 * @param email
	 * @param password
	 * @return
	 * @return ResponseEntity<String>
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value = "rest/orgRegist", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> orgRegist(
			@RequestParam(value = "orgname", defaultValue = "") String orgname,
			@RequestParam(value = "orgdesc", defaultValue = "") String orgdesc,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "password", defaultValue = "") String password,
			HttpServletRequest request) {

		ResponseEntity<String> responseEntity = new ResponseEntity<String>();

		OrganizationEntity organizationEntity = new OrganizationEntity();
		organizationEntity.setOrganizationChname(orgname);
		organizationEntity.setOrganizationDescription(orgdesc);

		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(email);
		userEntity.setUserEmail(email);
		userEntity.setPassword(password);
		userEntity.setCompanyName(orgname);

		try {
			userService.saveUserEntityORG(userEntity, organizationEntity);
			responseEntity.setEntity(CommonConstant.SAVE_SUCCESS);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * 
	 * @Description: rest接口 for other people
	 * @param username
	 * @param password
	 * @param response
	 * @throws IOException
	 * @return void
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value = "api/authenticate/{username}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public Map authenticate(@PathVariable("username") String username,
			@PathVariable("password") String password,
			HttpServletResponse response) throws IOException {
		// {"res":{"tenant":"8a8be9a4475dcaef01475dcaef7e0000","isMatch":false},"code":"000000","msg":"请求成功"}
		Map map = new HashMap();
		Map res = new HashMap();
		try {
			UserEntity userEntity = userService.hasUserEntity(username);
			if (userEntity.getPassword().equals(MD5.GetMD5Code(password))) {
				res.put("tenant", userEntity.getTenant());
				res.put("isMatch", true);
				res.put("userId", userEntity.getUserId());
				res.put("uaaGuid", userEntity.getUaaGuid());

				map.put("res", res);
				map.put("code", CommonConstant.SUCCESS);
				map.put("msg", CommonConstant.SUCCESS_TXT);
			} else {
				res.put("isMatch", false);

				map.put("res", res);
				map.put("code", CommonConstant.SUCCESS);
				map.put("msg", CommonConstant.SUCCESS_TXT);
			}
		} catch (RCloudException re) {
			map.put("res", null);
			map.put("code", CommonConstant.FAILURE);
			map.put("msg", CommonConstant.FAILURE_TXT);
		} catch (Exception e) {
			map.put("res", null);
			map.put("code", CommonConstant.FAILURE);
			map.put("msg", CommonConstant.FAILURE_TXT);
		}
		return map;
	}

	/**
	 * 
	 * @Description: 向cf平台注册机构管理员
	 * @param usrname
	 * @param response
	 * @throws IOException
	 * @return void
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value = "api/regist/admin/{username}", method = RequestMethod.GET)
	public void registORGByName(@PathVariable("username") String username,
			HttpServletResponse response) throws IOException {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>();
		try {
			if (username != null && username.length() != 0) {
				UserEntityExample example = new UserEntityExample();
				example.createCriteria().andUserNameEqualTo(username.trim());
				List<UserEntity> userList = userMapper.selectByExample(example);
				if (userList != null && userList.size() > 0) {
					UserEntity user = userList.get(0);
					OrganizationEntity organizationEntity = new OrganizationEntity();
					organizationEntity.setOrganizationId(user
							.getOrganizationId());
					userService.registEntity(user, organizationEntity);
					responseEntity.setEntity(CommonConstant.OPERATE_SUCCESS);
				} else {
					responseEntity
							.setStatus(CommonConstant.STATUS_BUSINESS_EXCEPTION);
					responseEntity.setEntity(CommonConstant.OPERATE_FAIL);
				}
			} else {
				responseEntity
						.setStatus(CommonConstant.STATUS_BUSINESS_EXCEPTION);
				responseEntity.setEntity(CommonConstant.OPERATE_FAIL);
			}
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(new Gson().toJson(responseEntity));
		pw.flush();
		pw.close();
	}

	/**
	 * 
	 * @Description: 向cf平台注册用户和组织
	 * @param usrid
	 * @param orgid
	 * @param response
	 * @throws IOException
	 * @return void
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value = "api/regist/{usrid}/{orgid}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> registORG(
			@PathVariable("usrid") String usrid,
			@PathVariable("orgid") String orgid, HttpServletResponse response)
			throws IOException {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>();

		try {
			if (usrid != null && usrid.length() != 0 && orgid != null
					&& orgid.length() != 0) {
				UserEntity userEntity = new UserEntity();
				userEntity.setUserId(usrid);
				OrganizationEntity organizationEntity = new OrganizationEntity();
				organizationEntity.setOrganizationId(orgid);
				userService.registEntity(userEntity, organizationEntity);
				responseEntity.setEntity(CommonConstant.OPERATE_SUCCESS);
			} else {
				responseEntity
						.setStatus(CommonConstant.STATUS_BUSINESS_EXCEPTION);
				responseEntity.setEntity(CommonConstant.OPERATE_FAIL);
			}
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * 
	 * @Description: 获取用户信息
	 * @param uaaguid
	 * @param response
	 * @throws IOException
	 * @return void
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value = "api/userInfo/guid/{uaaguid}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserEntity> getUserByGuid(
			@PathVariable("uaaguid") String uaaguid,
			HttpServletResponse response) throws IOException {
		ResponseEntity<UserEntity> responseEntity = new ResponseEntity<UserEntity>();

		try {
			if (uaaguid != null && uaaguid.length() != 0) {
				UserEntity userEntity = userService.getUserByGuid(uaaguid);
				if (userEntity.getMarkDelete() == CommonConstant.USER_ARE_DELETED) {// 用户被软删除
					responseEntity.setStatus(ExceptionContant.USER_IS_DELETED);
					responseEntity.setErrorMessage(ExceptionContant.ERROR_MSG
							.get(ExceptionContant.USER_IS_DELETED));
				} else {
					if (userEntity.getStatus() == CommonConstant.USER_FROZEN) {// 用户已经被冻结
						responseEntity
								.setErrorCode(ExceptionContant.USER_IS_FROZEN);
					}
					if (userEntity != null
							&& userEntity.getOrganizationId() != null
							&& userEntity.getOrganizationId().length() > 0) {
						OrganizationEntity orgentity = orgMapper
								.selectByPrimaryKey(userEntity
										.getOrganizationId());
						responseEntity.setErrorMessage(orgentity
								.getOrganizationChname());
					}
					responseEntity.setEntity(userEntity);
				}
			} else {
				responseEntity
						.setStatus(CommonConstant.STATUS_BUSINESS_EXCEPTION);
				responseEntity.setErrorMessage("用户信息不存在！！！");
			}
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * 
	 * @Description: 获取用户信息
	 * @param userid
	 * @param response
	 * @throws IOException
	 * @return void
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value = "api/userInfo/userid/{userid}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserEntity> getUserByUserid(
			@PathVariable("userid") String userid, HttpServletResponse response)
			throws IOException {
		ResponseEntity<UserEntity> responseEntity = new ResponseEntity<UserEntity>();

		try {
			if (userid != null && userid.length() != 0) {
				UserEntity userEntity = userService.getUserByUserid(userid);
				if (userEntity.getMarkDelete() == CommonConstant.USER_ARE_DELETED) {// 用户被软删除
					responseEntity.setStatus(ExceptionContant.USER_IS_DELETED);
					responseEntity.setErrorMessage(ExceptionContant.ERROR_MSG
							.get(ExceptionContant.USER_IS_DELETED));
				} else {
					if (userEntity.getStatus() == CommonConstant.USER_FROZEN) {// 用户已经被冻结
						responseEntity
								.setErrorCode(ExceptionContant.USER_IS_FROZEN);
					}
					if (userEntity != null
							&& userEntity.getOrganizationId() != null
							&& userEntity.getOrganizationId().length() > 0) {
						OrganizationEntity orgentity = orgMapper
								.selectByPrimaryKey(userEntity
										.getOrganizationId());
						responseEntity.setErrorMessage(orgentity
								.getOrganizationChname());
					}
					responseEntity.setEntity(userEntity);
				}
			} else {
				responseEntity
						.setStatus(CommonConstant.STATUS_BUSINESS_EXCEPTION);
				responseEntity.setErrorMessage("用户信息不存在！！！");
			}
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * 
	 * @Description: 注册用户信息
	 * @param usrid
	 * @param response
	 * @throws IOException
	 * @return void
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value = "regist/{usrid}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> regist(@PathVariable("usrid") String usrid,
			HttpServletResponse response) throws IOException {

		ResponseEntity<String> responseEntity = new ResponseEntity<String>();
		try {
			if (usrid != null && usrid != "") {
				UserEntity userEntity = new UserEntity();
				userEntity.setUserId(usrid);
				userService.registEntity(userEntity);
			}
			responseEntity.setEntity(CommonConstant.OPERATE_SUCCESS);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		}
		return responseEntity;
	}

	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value = "rest/checkemail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> haveEmail(
			@RequestParam(value = "email", defaultValue = "") String email,
			HttpServletResponse response) throws IOException {

		ResponseEntity<String> responseEntity = new ResponseEntity<String>();
		try {
			userService.hasUserEntityEmail(email);
			responseEntity.setEntity(CommonConstant.OPERATE_SUCCESS);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		}
		return responseEntity;
	}
	
	/** 
	 * 用以判断用户是否登录
	* @Title: isLogin 
	* @author zhangjiaxing
	* @param @param response
	* @param @param request
	* @param @return
	* @return ResponseEntity<String>  
	* @throws 
	*/ 
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value = "rest/checkIsLogin", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> isLogin(HttpServletResponse response,HttpServletRequest request){

		ResponseEntity<String> responseEntity = new ResponseEntity<String>();
		
		//判断是否已经登录
		UserEntityExt user = LoginUtil.getUser();

		try {
			String isLogin = userService.isLogin(user);
			responseEntity.setEntity(isLogin);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		}
		return responseEntity;
	}
}
