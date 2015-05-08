package com.chinasofti.rcloud.web.cloudshop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.common.CommonConstants;
import com.chinasofti.rcloud.common.MD5;
import com.chinasofti.rcloud.common.PageParameter;
import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.common.cloudfoundry.UploadToCFIterface;
import com.chinasofti.rcloud.dao.ApplicationOrderEntityMapper;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.ApplicationEntityWithBLOBs;
import com.chinasofti.rcloud.domain.ApplicationTypeEntity;
import com.chinasofti.rcloud.domain.ApplyEntity;
import com.chinasofti.rcloud.domain.BusinessOrderEntity;
import com.chinasofti.rcloud.domain.SpaceEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.app.service.AppService;
import com.chinasofti.rcloud.portal.cloudservice.service.RcloudService;
import com.chinasofti.rcloud.portal.cloudshop.domain.ApplicationEntityExt;
import com.chinasofti.rcloud.portal.cloudshop.service.ApplicationPublishService;
import com.chinasofti.rcloud.portal.cloudshop.vo.ApplicationEntityVo;
import com.chinasofti.rcloud.portal.cloudshop.vo.ApplicationPreVo;
import com.chinasofti.rcloud.portal.cloudshop.vo.RaeAppVo;
import com.chinasofti.rcloud.portal.userorder.domain.ApplicationOrderEntityExt;
import com.chinasofti.rcloud.portal.userorder.service.ApplicationOrderService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.CommonUtil;
import com.chinasofti.rcloud.web.common.FileUtil;
import com.chinasofti.rcloud.web.common.LoginUtil;
import com.chinasofti.rcloud.web.common.RequestMappingName;
import com.chinasofti.rcloud.web.common.ResponseEntity;

/**
 * @author zhangjiaxing
 *
 *         2014年7月14日
 */
@Controller
public class ApplicationPublishController extends BasicController {
	private static Logger logger = Logger
			.getLogger(ApplicationPublishController.class);

	@Autowired
	private ApplicationPublishService applicationPublishService;

	@Autowired
	private AppService appService;

	@Autowired
	private UploadToCFIterface uploadToCFIterface;

	/**
	 * 用户发布的云应用列表
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_QUERY)
	@ResponseBody
	@RequestMapping("rest/applicationpublish")
	public ResponseEntity<Pagination<ApplicationEntityExt>> getApplicationPublish(
			HttpServletRequest request) {
		ResponseEntity<Pagination<ApplicationEntityExt>> responseEntity = new ResponseEntity<Pagination<ApplicationEntityExt>>();
		String userId = LoginUtil.getUserId();
		try {
			Map<String, Object> searchParams = CommonUtil
					.getParameterMap(request);
			PageParameter page = new PageParameter();
			if (!StringUtils.isEmpty(searchParams.get("page"))
					&& !StringUtils.isEmpty(searchParams.get("rows"))) {
				page.setCurrentPage(Integer.parseInt((String) searchParams
						.get("page")));
				page.setPageSize(Integer.parseInt((String) searchParams
						.get("rows")));
			}

			searchParams.put("page", page);
			searchParams.put("userId", userId);
			Pagination<ApplicationEntityExt> pagination = applicationPublishService
					.getApplicationPublishByPage(searchParams);
			responseEntity.setEntity(pagination);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * @Title: getApplicationPublishInfo
	 * @Description: 云应用详情---修改应用的
	 * @author shimeihua
	 * @param @param applicationId
	 * @param @return
	 * @return ResponseEntity<ApplicationEntityVo>
	 * @throws
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping("rest/applicationpublish/applicationPublishInfo/{applicationId}")
	public ResponseEntity<ApplicationEntityVo> getApplicationPublishInfo(
			@PathVariable("applicationId") String applicationId) {
		ResponseEntity<ApplicationEntityVo> responseEntity = new ResponseEntity<ApplicationEntityVo>();
		try {
			String userId = LoginUtil.getUserId();
			ApplicationEntityVo applicationEntityVo = applicationPublishService
					.getUpdateApplicationPublishInfo(applicationId,userId);
			String filename=FileUtil.getFileName(userId, applicationId);
			ApplicationEntity applicationEntity=applicationEntityVo.getApplicationEntity();
			if(applicationEntity!=null){
				if(1==applicationEntity.getDeployType()){
					applicationEntityVo.setBassApp(filename);
					
				}
			}
			
			
			
			responseEntity.setEntity(applicationEntityVo);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * @Title: toapplicationdeploy raesmh
	 * @Description: 已经购买的应用--部署=指向部署页面
	 * @author shimeihua
	 * @param @param applicationId
	 * @param @return
	 * @return ResponseEntity<ApplicationPreVo>
	 * @throws
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping("rest/applicationpublish/toapplicationdeploy/{applicationId}")
	public ResponseEntity<ApplicationPreVo> toapplicationdeploy(
			@PathVariable("applicationId") String applicationId) {
		ResponseEntity<ApplicationPreVo> responseEntity = new ResponseEntity<ApplicationPreVo>();
		ApplicationPreVo vo = new ApplicationPreVo();
		String userId = LoginUtil.getUserId();
		try {
			ApplicationEntity applicationEntity = applicationPublishService
					.getApplicationPublishInfo(applicationId);
			vo.setApplicationEntity(applicationEntity);
			List<BusinessOrderEntity> list = rcloudService
					.getCloudServiceOrder(userId,
							CommonConstants.RAE_SERVICE_ID);
			if (list != null && list.size() > 0) {// 开通服务
				vo.setOpenRaePreFlag(true);
				
				//接口
				List<SpaceEntity>  lists=uploadToCFIterface.getSpaceEntitys(LoginUtil.getUser());
				vo.setLists(lists);
				
			} else {
				// 提示 开通云引擎服务；
			}

			responseEntity.setEntity(vo);
		} catch (RCloudException re) {
			this.handleBusinessExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * 修改应用详情
	 * 
	 * @param applicationEntity
	 * @param request
	 * @return
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_ADMIN)
	@ResponseBody
	@RequestMapping(value = "rest/applicationpublish/updateApplication", method = RequestMethod.POST)
	public ResponseEntity<Boolean> updateApplicationPublishInfo(
			@ModelAttribute ApplicationEntityWithBLOBs applicationEntity,String applyDesc,
			HttpServletRequest request) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		try {
			// 修改 如果以前是后部署应用， 并且本次上传架包成功
			// 删除之前的，重新上传
			// war包地址变动 applicationId
			String userId = LoginUtil.getUserId();
			applicationEntity.setProviderId(userId);
			String beforepublicType = request.getParameter("beforepublicType");
			String isWarSu = request.getParameter("isWarSu");
			String publicType = applicationEntity.getDeployType().toString();

			String filewarTypes = applicationEntity.getBaseApp();// 暂存应用名字

			if ("success".equals(isWarSu)
					&& CommonConstants.DEPLOYMENT_TYPE_AFTER
							.equals(beforepublicType)) {
				// 删除以前war包 后部署 部署成功
				deleteMoveFile(applicationEntity.getApplicationId(),
						filewarTypes);
			}
			if (CommonConstants.DEPLOYMENT_TYPE_AFTER.equals(beforepublicType)
					&& CommonConstants.DEPLOYMENT_TYPE_PRE.equals(publicType)) {
				// 删除以前war包 如果之前是后部署   现在是预部署
				deleteFile(applicationEntity.getApplicationId(),
						userId);
			}
			if ("success".equals(isWarSu)
					&& CommonConstants.DEPLOYMENT_TYPE_AFTER.equals(publicType)) {// 如果本次修改成后部署，上传架包成功，移动
				deleteMoveFile(applicationEntity.getApplicationId(),
						filewarTypes);

			}

			applicationPublishService
					.updateApplicationPublishInfo(applicationEntity,applyDesc);
		} catch (RCloudException re) {
			this.handleServerExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * @Title: deleteMoveFile
	 * @Description: 删除或者移动架包
	 * @author shimeihua
	 * @param @param applicationId
	 * @return void
	 * @throws
	 */
	public void deleteMoveFile(String applicationId, String filewarTypes) {
		try {
			if (filewarTypes != null) {
				//deleteFiles
				FileUtil.moveFileToStore(applicationId, filewarTypes);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("FileUtil.moveFileToStore", e);
		}
	}
	public void deleteFile(String applicationId, String userId) {
		try {
				//deleteFiles
				FileUtil.deleteFiles(userId, applicationId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("FileUtil.deleteFiles", e);
		}
	}

	/**
	 * 取消发布的应用
	 * 
	 * @param applicationId
	 * @param request
	 * @return
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_ADMIN)
	@ResponseBody
	@RequestMapping(value = "rest/applicationpublish/deleteApplication/{applicationId}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> deleteApplicationPublish(
			@PathVariable("applicationId") String applicationId,
			HttpServletRequest request) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		try {
			applicationPublishService.deleteApplicationPublish(applicationId,
					LoginUtil.getUserName());
		} catch (RCloudException re) {
			this.handleServerExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * 发布新应用
	 * 
	 * @param applicationEntity
	 * @param request
	 * @return
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_ADMIN)
	@ResponseBody
	@RequestMapping(value = "rest/applicationpublish/insertApplication", method = RequestMethod.POST)
	public ResponseEntity<Boolean> insertApplicationPublish(
			@ModelAttribute ApplicationEntityWithBLOBs applicationEntity,
			@ModelAttribute ApplyEntity applyEntity, HttpServletRequest request) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		String userId = LoginUtil.getUserId();
		try {
			String applicationId = com.chinasofti.rcloud.common.CommonUtil
					.getId();
			applicationEntity.setApplicationId(applicationId);
			applicationPublishService.publishNewApplication(applicationEntity,
					userId, "", "", applyEntity);
			String filewarTypes = request.getParameter("filewarTypes");
			// war包地址变动 applicationId
			try {
				if (filewarTypes != null) {
					FileUtil.moveFileToStore(applicationId, filewarTypes);

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("FileUtil.moveFileToStore", e);
			}
		} catch (RCloudException re) {
			this.handleServerExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * @Title: offApplication
	 * @Description: 应用下架
	 * @author shimeihua
	 * @param @param applicationId
	 * @param @param request
	 * @param @return
	 * @return ResponseEntity<Boolean>
	 * @throws
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_ADMIN)
	@ResponseBody
	@RequestMapping(value = "rest/applicationpublish/offApplication/{applicationId}")
	public ResponseEntity<Boolean> offApplication(
			@PathVariable("applicationId") String applicationId,
			HttpServletRequest request) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		// String userId = LoginUtil.getUserId();
		// String [] apps=applicationId.split(",");
		try {
			// applicationPublishService.publishNewApplication(applicationEntity,
			// userId, "", "");
			applicationPublishService.deleteApplicationPublish(applicationId,
					LoginUtil.getUserName());
		} catch (RCloudException re) {
			this.handleServerExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_QUERY)
	@RequestMapping(value = "page/application/publish/list")
	public String goPublishListPage() {
		return "storeManager/published_application_list";
	}

	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_ADMIN)
	@RequestMapping(value = "page/application/publish")
	public String goPublishPage() {
		return "storeManager/apply_publish";
	}

	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping("common/applicationType/list")
	public ResponseEntity<List<ApplicationTypeEntity>> listAllApplicationType() {
		ResponseEntity<List<ApplicationTypeEntity>> responseEntity = new ResponseEntity<List<ApplicationTypeEntity>>();
		try {
			List<ApplicationTypeEntity> applicationTypeEntities = appService
					.findAppType();
			responseEntity.setEntity(applicationTypeEntities);
		} catch (RCloudException re) {
			this.handleServerExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}

		return responseEntity;
	}

	/**
	 * 
	 * @Title: listSpaceNameByUserId
	 * @Description: 根据不同的用户返回不同的工作空间
	 * @author sunlulu
	 * @param @return
	 * @return List<SpaceEntity>
	 * @throws
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping("common/spaceName/list")
	public List<SpaceEntity> listSpaceNameByUserId() {
		return uploadToCFIterface.getSpaceEntitys(LoginUtil.getUser());
	}

	/**
	 * 发布应用新的公用rest接口,调用该接口时需要校验用户名和密码
	 * 
	 * @param applicationEntity
	 * @param request
	 * @return 2014年11月10日
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping(value = "rest/applicationpublish/publishnewapplication", method = RequestMethod.POST)
	public ResponseEntity<Boolean> publishApplication(
			@ModelAttribute ApplicationEntityWithBLOBs applicationEntity,
			@PathVariable("userName") String userName,
			@PathVariable("password") String password,
			HttpServletRequest request) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		String userId = LoginUtil.getUserId();
		try {
			applicationPublishService.publishNewApplication(applicationEntity,
					userId, userName, MD5.GetMD5Code(password), null);
		} catch (RCloudException re) {
			this.handleServerExceptionByJson(re, responseEntity);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	/**
	 * @Title: deploymentApplication
	 * @Description: 部署应用
	 * @author shimeihua
	 * @param @param applicationEntity
	 * @param @param request
	 * @param @return
	 * @return ResponseEntity<Boolean>
	 * @throws
	 */
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_ADMIN)
	@ResponseBody
	@RequestMapping(value = "rest/applicationpublish/deploymentApplication", method = RequestMethod.POST)
	public ResponseEntity<Boolean> deploymentApplication(
			@ModelAttribute RaeAppVo raeAppVo, HttpServletRequest request) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		try {
			UserEntity userEntity=LoginUtil.getUser();//部署应用
			String provideId=request.getParameter("developId");
			if(provideId!=null&&!"".equals(provideId)){
				uploadToCFIterface.apps(raeAppVo, userEntity, LoginUtil.getUser().getcFClientToken(), raeAppVo.getAppliId(),provideId);
				//部署成功后，修改  deployTime  部署时间----
				String userId = LoginUtil.getUserId();	
				String orderNum=raeAppVo.getOrderNum();//订单号
				ApplicationOrderEntityExt ext=applicationOrderService.getBuyApplicationOrderInfo(userId, orderNum);
				Date deployTime = new Date();//设置--部署时间
				ext.setDeployTime(deployTime);
				applicationOrderEntityMapper.updateByPrimaryKey(ext);
				// 部署成功后，修改 deployTime 部署时间----
				responseEntity.setEntity(true);
			}else{
				responseEntity.setEntity(false);
			}
		} catch (Exception e) {
			  responseEntity.setStatus(CommonConstant.STATUS_SYSTEM_EXCEPTION);
			responseEntity.setErrorMessage(e.getMessage());
			logger.error("deploymentApplication", e);
			//this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_APP_QUERY)
	@ResponseBody
	@RequestMapping(value = "rest/applicationpublish/checkdomain/{domainName}/")
	public ResponseEntity<Boolean> checkDomainApp(@PathVariable("domainName") String domainName, HttpServletRequest request) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		try {
			int result=uploadToCFIterface.getRoutesNum(domainName, LoginUtil.getUser().getcFClientToken());
			if(result>0){
				responseEntity.setEntity(true);//已经存在
			}else{
				responseEntity.setEntity(false);
			}
			//responseEntity.setEntity(true);
		} catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}
	@RequestMappingName(value = CommonConstant.ROLE_PERMISSION_COMMON)
	@ResponseBody
	@RequestMapping(value = "rest/applicationpublish/buyappcheck/{applicationId}")
	public ResponseEntity<Boolean> buyappcheck(@PathVariable("applicationId") String applicationId,HttpServletRequest request) {
		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>();
		try {
			boolean buyFlag=false;
			String userId = LoginUtil.getUserId();
			Map<String,Object> searchParams=new HashMap<String,Object>();
			searchParams.put("orderStatus", 1);//1-服务中，2-暂停，3-结束，4-撤销   
			searchParams.put("applicationId", applicationId);
			searchParams.put("userId", userId);
			searchParams.put("orderTime", new Date());
			List<ApplicationOrderEntityExt> list=applicationOrderService.getApplicationOrderByBuyerAppId(searchParams);
	        if(list!=null&&list.size()>0){
	        	buyFlag=true;
	        }
	        responseEntity.setEntity(buyFlag);
		}  catch (Exception e) {
			this.handleServerExceptionByJson(e, responseEntity);
		}
		return responseEntity;
	}

	@Autowired
	RcloudService rcloudService;
	@Autowired
	ApplicationOrderService applicationOrderService;
	@Autowired
	ApplicationOrderEntityMapper applicationOrderEntityMapper;
	

}
