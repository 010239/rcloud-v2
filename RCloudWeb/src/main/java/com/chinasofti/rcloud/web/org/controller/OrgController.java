/**
 * @Title: OrgController.java
 * @Package: com.chinasofti.rcloud.web.org.controller
 * @Author: sunlulu
 * @Date: 2014年7月26日 上午10:33:38
 * @Version: V1.0
 */
package com.chinasofti.rcloud.web.org.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.domain.SpaceEntity;
import com.chinasofti.rcloud.domain.SpaceToUserEntityKey;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.log.service.LogService;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;
import com.chinasofti.rcloud.portal.login.service.UserService;
import com.chinasofti.rcloud.portal.organization.service.OrganizationManagerService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;

/**
 * @ClassName: OrgController
 * @Description: 机构管理
 * @Author: sunlulu
 * @Date: 2014年7月26日 上午10:33:38
 *
 */
@Controller
public class OrgController extends BasicController {

	@Autowired
	private OrganizationManagerService organizationManagerService;

	@Autowired
	private UserService userService;
	@Autowired
	private LogService logService;
	
	private Logger logger = Logger.getLogger(OrgController.class);
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_ORG_ADMIN)
	@RequestMapping("page/org/spacepage")
	public String getPage() {
		return "orgManager/orgList";
	}

	@RequestMappingName(CommonConstant.ROLE_PERMISSION_ORG_ADMIN)
	@RequestMapping("page/org/createspace")
	public String getCreate() {		
		return "orgManager/createspace";
	}

	@RequestMappingName(CommonConstant.ROLE_PERMISSION_ORG_ADMIN)
	@RequestMapping("page/org/createdevloper")
	public String getCreateDevloper() {
		return "orgManager/createdevloper";
	}

	/**
	 * 
	 * @Description: 跳转到绑定机构开发者
	 * @param spaceId 工作空间ID
	 * @param request
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @return String 
	 * @throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_ORG_ADMIN)
	@RequestMapping(value = "page/org/bingdevloper/{spaceId}", method = RequestMethod.GET)
	public String bingDevloper(@PathVariable("spaceId") String spaceId,
			HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {

		try {
			UserEntityExt user = LoginUtil.getUser();
			Map<String, Object> mapSearch = new HashMap<String, Object>();
			mapSearch.put("orgId", user.getOrganizationId());
			mapSearch.put("rolecode", CommonConstant.ORG_DEVLOPER);
			List<Map<String, String>> list = userService.selectByMap(mapSearch);
			model.addAttribute("list", list);
			model.addAttribute("spaceID", spaceId);
		} catch (Exception e) {
			logger.error("开发者绑定出错!!!", e);
			return this.handleServerExceptionByPage (e);
		}
		return "orgManager/bingdevloper";
	}
	/**
	 * 
	 *@Description: 已绑定的机构开发者信息
	 *@param spaceId
	 *@param request
	 *@param model
	 *@param redirectAttributes
	 *@return
	 *@return List<SpaceToUserEntityKey>
	 *@throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_ORG_ADMIN)
	@RequestMapping(value = "rest/org/checkorg/{spaceId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SpaceToUserEntityKey>> checkorg(
			@PathVariable("spaceId") String spaceId,
			HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		List<SpaceToUserEntityKey> list = null;
		ResponseEntity<List<SpaceToUserEntityKey>> responseEntity = new ResponseEntity<List<SpaceToUserEntityKey>>();
		try {
			UserEntityExt user = LoginUtil.getUser();
			Map<String, Object> mapSearch = new HashMap<String, Object>();
			mapSearch.put("userId", user.getUserId());
			mapSearch.put("spaceId", spaceId);
			list = userService.selectBySpaceIdNotUserId(mapSearch);
			responseEntity.setEntity(list);
			//model.addAttribute("list", list);
			//model.addAttribute("spaceID", spaceId);
		} catch(RCloudException e){
			logger.error("已绑定的机构开发者信息执行异常！！！", e);
			this.handleBusinessExceptionByJson(e, responseEntity);
		}catch (Exception e) {
			logger.error("已绑定的机构开发者信息执行异常！！！", e);
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	/**
	 * 
	 *@Description: 返回JSON格式的字符串信息
	 *@param request
	 *@param model
	 *@return
	 *@return ResponseEntity<Map>
	 *@throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_ORG_ADMIN)
	@ResponseBody
	@RequestMapping("rest/org/spaceinfo")
	public ResponseEntity<Map> getAllCloudService1(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		ResponseEntity<Map> responseEntity = new ResponseEntity<Map>();
		try {
			UserEntityExt user = LoginUtil.getUser();
			List<HashMap<String, String>> list = organizationManagerService
					.getOrganizationInfo(user.getOrganizationId());

			for (HashMap<String, String> hashMap : list) {
				String space_id = hashMap.get("space_id");
				Map<String, Object> mapsearch = new HashMap<String, Object>();
				mapsearch.put("userId", user.getUserId());
				mapsearch.put("spaceId", space_id);
//				List<SpaceToUserEntityKey> userList = userService
//						.selectBySpaceIdNotUserId(mapsearch);
//				hashMap.put("countDev", "" + userList.size());
				int nums = userService.selectNumsBySpaceIdNotUserId(mapsearch);
				hashMap.put("countDev", "" + nums);
			}
//			map.put("rows", list);
//			map.put("total", 1);
			Map listmap = new HashMap();
			listmap.put("rows", list);
			responseEntity.setEntity(listmap);

		} catch (RCloudException re) {
			logger.error("查询组织机构信息出错!!!", re);
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			logger.error("查询组织机构信息出错!!!", e);
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	/**
	 * 
	 *@Description: 保存用户的工作空间信息
	 *@param spaceEntity
	 *@param model
	 *@param redirectAttributes
	 *@param request
	 *@param session
	 *@return
	 *@return String 跳转到工作空间列表页
	 *@throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_ORG_ADMIN)
	@RequestMapping(value = "page/org/savespace", method = RequestMethod.POST)
	public String save(@Validated SpaceEntity spaceEntity, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpSession session) {
		UserEntityExt user = LoginUtil.getUser();
		try {
			userService.saveSpace(user, spaceEntity);
			//记录操作日志
			String operation = CommonConstant.SPACE_OPERATION ;
			String description = CommonConstant.SPACE_DESC + spaceEntity.getSpaceChname();
			logService.insertLog(user, operation, description);
		} catch(RCloudException e){
			logger.error("保存工作空间出错", e);
			return this.handleBusinessExceptionByPage(e);
		}catch (Exception e) {
			logger.error("保存工作空间出错", e);
			return this.handleServerExceptionByPage(e);
		}
		return "orgManager/orgList";
	}
	/**
	 * 
	 *@Description: 保存机构开发者信息
	 *@param userEntity
	 *@param model
	 *@param redirectAttributes
	 *@param request
	 *@param session
	 *@return
	 *@return String 跳转到工作空间列表页
	 *@throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_ORG_ADMIN)
	@RequestMapping(value = "page/org/savedevloper", method = RequestMethod.POST)
	public String saveDevloper(@Validated UserEntity userEntity, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpSession session) {
		UserEntityExt user = LoginUtil.getUser();
		
		try {
			userService.saveUserEntityDevleper(userEntity, user);
			String operation = CommonConstant.ORG_DEVELOPER_OPERATION;
			String description = CommonConstant.ORG_DEVELOPER_DESC + userEntity.getUserName();
			logService.insertLog(user, operation, description);
		} catch (RCloudException e) {
			logger.error("保存机构开发者出错", e);
			this.handleBusinessExceptionByPage(e);
			//return "orgManager/orgError";
		}catch(Exception e){
			logger.error("保存机构开发者出错", e);
			this.handleServerExceptionByPage(e);
		}
		return "orgManager/orgList";
	}
	/**
	 * 
	 *@Description: 绑定机构开发者
	 *@param userids  需要绑定的用户Id数组
	 *@param spaceId  对应的工作空间
	 *@param model
	 *@param redirectAttributes
	 *@param request
	 *@param session
	 *@return
	 *@return String 跳转到工作空间列表页
	 *@throws
	 */
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_ORG_ADMIN)
	@RequestMapping(value = "page/org/bingdevloper", method = RequestMethod.POST)
	public String bingDevloper(
			@RequestParam(value = "userId",required=false) String[] userids,
			@RequestParam(value = "spaceId") String spaceId, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpSession session) {
		UserEntityExt user = LoginUtil.getUser();
		try {
			List<UserEntity> listDev = new ArrayList<UserEntity>();
			if(userids!=null){
				for (String string : userids) {
					UserEntity u = new UserEntity();
					u.setUserId(string);
					listDev.add(u);
				}
			}
			userService.bingEntityDevleper(user, listDev, spaceId);
		} catch (RCloudException e) {
			logger.error("绑定机构开发者出错", e);
			this.handleBusinessExceptionByPage(e);
		}catch(Exception e){
			logger.error("绑定机构开发者出错", e);
			this.handleServerExceptionByPage(e);
		}
		return "orgManager/orgList";
	}
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_ORG_ADMIN)
	@RequestMapping(value = "rest/checkUserName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> haveUserName(@RequestParam(value = "userName", defaultValue = "") String userName,
			HttpServletResponse response) throws IOException {

		ResponseEntity<String> responseEntity = new ResponseEntity<String>();
		try {
			userService.hasUserName(userName);
			responseEntity.setEntity(CommonConstant.OPERATE_SUCCESS);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		}
		return responseEntity;
	}
	
	/**
	 * 根据uaaguid获取组织名和机构名
	 * @param uaaGuid
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "api/getOrgAndSpaceName/{uaaGuid}", method = RequestMethod.GET)
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	public ResponseEntity<Map<String, String>> getInfo(@PathVariable("uaaGuid") String uaaGuid,
			HttpServletResponse response) throws IOException {
		ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<Map<String, String>>();
		try {
			String roleCode = organizationManagerService.getRoleCode(uaaGuid);
			//如果是独立开发者
			if (roleCode.equals(CommonConstant.DEVLOPER_ENTITY) ) {
				responseEntity.setEntity(organizationManagerService.getInDevInfo(uaaGuid));
			}
			//如果是机构开发者或者机构管理员
			if (roleCode.equals(CommonConstant.ORG_DEVLOPER)   || roleCode.equals(CommonConstant.ORG_ENTITY) ) {
				responseEntity.setEntity(organizationManagerService.getOrgAndSpaInfo(uaaGuid));
			}
			
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
		
	}


}
