package com.chinasofti.rcloud.portal.cloudshop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chinasofti.common.util.PinYinUtil;
import com.chinasofti.rcloud.common.CommonConstants;
import com.chinasofti.rcloud.common.CommonUtil;
import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.dao.ApplicationEntityMapper;
import com.chinasofti.rcloud.dao.ApplyEntityMapper;
import com.chinasofti.rcloud.dao.UserEntityMapper;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.ApplicationEntityWithBLOBs;
import com.chinasofti.rcloud.domain.ApplyEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.domain.UserEntityExample;
import com.chinasofti.rcloud.portal.cloudshop.dao.ApplicationMapperExt;
import com.chinasofti.rcloud.portal.cloudshop.dao.ApplyMapperExt;
import com.chinasofti.rcloud.portal.cloudshop.domain.ApplicationEntityExt;
import com.chinasofti.rcloud.portal.cloudshop.service.ApplicationPublishService;
import com.chinasofti.rcloud.portal.cloudshop.vo.ApplicationEntityVo;
import com.chinasofti.rcloud.portal.userorder.dao.ApplicationOrderEntityMapperExt;

/**
 * @author zhangjiaxing
 *
 * 2014年7月14日
 */
@Service
public class ApplicationPublishServiceImpl implements ApplicationPublishService{
	
	@Autowired
	private ApplicationMapperExt applicationMapperExt;
	
	@Autowired
	private ApplicationEntityMapper applicationEntityMapper;
	
	@Autowired
	private ApplicationOrderEntityMapperExt applicationOrderEntityMapperExt;
	
	@Autowired
	private UserEntityMapper userEntityMapper;
	
	@Autowired
	private ApplyEntityMapper applyEntityMapper;

	private Logger logger = Logger.getLogger(ApplicationPublishServiceImpl.class);

	@Override
	public Pagination<ApplicationEntityExt> getApplicationPublishByPage(Map<String, Object> searchParams)
			throws Exception {
		Pagination<ApplicationEntityExt> pagination = new Pagination<ApplicationEntityExt>();
		List<ApplicationEntity> applicationList = applicationMapperExt.selectApplicationPublishByPage(searchParams);
		List<ApplicationEntityExt> applicationPublishList = new ArrayList<ApplicationEntityExt>();
		if (applicationList == null ) {
			logger.error("未查询到您相关的已发布应用信息！");
		}
		for (int i=0 ;i< applicationList.size();i++) {
			ApplicationEntityExt entityExt = new ApplicationEntityExt();
			entityExt.setApplicationId(applicationList.get(i).getApplicationId());
			entityExt.setApplicationName(applicationList.get(i).getApplicationName());
			entityExt.setCreatedTime(applicationList.get(i).getCreatedTime());
			entityExt.setDealCount(applicationOrderEntityMapperExt.countApplicationPublish(
					applicationList.get(i).getApplicationId()));
			entityExt.setLogoPath(applicationList.get(i).getLogoPath());
			entityExt.setChname(applicationList.get(i).getChname());
			entityExt.setStatus(applicationList.get(i).getStatus());
			entityExt.setShelveTime(applicationList.get(i).getShelveTime());//add 上架时间
			applicationPublishList.add(entityExt);
		}
		int count = applicationMapperExt.countApplicationPublish(searchParams);
		pagination.setRows(applicationPublishList);
		pagination.setTotal(count);
		return pagination;
	}

	@Override
	public ApplicationEntity getApplicationPublishInfo(String applicationId) throws Exception {
		ApplicationEntity applicationEntity = applicationEntityMapper.selectByPrimaryKey(applicationId);
		return applicationEntity;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ApplicationEntityVo getUpdateApplicationPublishInfo(String applicationId,String userId ) throws Exception {
		ApplicationEntityVo vo=new ApplicationEntityVo();
		try {
			ApplicationEntity applicationEntity = applicationEntityMapper.selectByPrimaryKey(applicationId);
			if(applicationEntity!=null){
				//if(0==applicationEntity.getStatus()|| 2==applicationEntity.getStatus()){//应用状态（0：待审核，1：审核通过，2：审核未通过，3:已下架） 
					Map searchParam=new HashMap();
					searchParam.put("userId", userId);
					searchParam.put("application_id", applicationId);
					ApplyEntity applyEntity=applyMapperExt.getApplyListByAppId(searchParam);
					String auditExplanation="";
					String applyExplanation="";
					if(applyEntity!=null){
						auditExplanation=applyEntity.getAuditExplanation()==null?"":applyEntity.getAuditExplanation();
						vo.setAuditExplanation(auditExplanation);
						applyExplanation=applyEntity.getApplyExplanation();
						vo.setApplyExplanation(applyExplanation);
					//}全部查询，去掉查询条件
				}
			}
			vo.setApplicationEntity(applicationEntity);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("getUpdateApplicationPublishInfo",e);
		}
		return vo;
		
		
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void updateApplicationPublishInfo(ApplicationEntityWithBLOBs applicationEntity,String applyDesc) throws Exception {
		ApplicationEntityWithBLOBs app = applicationEntityMapper.selectByPrimaryKey(applicationEntity.getApplicationId());
		Integer statusBefore=app.getStatus();
		app.setStatus(0);
		app.setChname(applicationEntity.getChname());
		if (!StringUtils.isEmpty(applicationEntity.getChname())) {
			app.setApplicationName(PinYinUtil.getFullSpell(applicationEntity.getChname()));
		}
		app.setBriefDescription(applicationEntity.getBriefDescription());
		app.setMaintenanceCosts(applicationEntity.getMaintenanceCosts());
		app.setContactPerson(applicationEntity.getContactPerson());
	   app.setContactPhone(applicationEntity.getContactPhone());
	   app.setDetailDescription(applicationEntity.getDetailDescription());
		app.setShowPortal(0);
//		if(applicationEntity.getBaseApp()!=null&&!"".equals(applicationEntity.getBaseApp())){
//			app.setBaseApp(applicationEntity.getBaseApp());
//		}
		app.setDeployType(applicationEntity.getDeployType());//add 部署类型
		app.setAllowdownload(applicationEntity.getAllowdownload());//add 是否允许下载
		app.setSubDomain(applicationEntity.getSubDomain());//add domian
		
		//??app.setBaseApp(applicationEntity.getApplicationId());
		//app.setAppVersion(entity.getAppVersion()+1);
		int record1 =applicationEntityMapper.updateByPrimaryKeySelective(app);
		if(0==statusBefore){//待审核  那么申请信息应该是修改，而不是重新添加
			Map searchParam=new HashMap();
			searchParam.put("userId", applicationEntity.getProviderId());
			searchParam.put("application_id", applicationEntity.getApplicationId());
			ApplyEntity applyEntity=applyMapperExt.getApplyListByAppId(searchParam);  
			applyEntity.setApplyExplanation(applyDesc);
			int record2 = applyEntityMapper.updateByPrimaryKey(applyEntity);
			if (record1 ==1 && record2 ==1 ) {
				logger.info("应用信息修改申请成功，请等待审核！");
			}
		}else{
		ApplyEntity applyEntity = new ApplyEntity();
		applyEntity.setApplyId(CommonUtil.getId());
		applyEntity.setApplyType("02");
		applyEntity.setApplyExplanation(applyDesc);
		applyEntity.setApplicationId(applicationEntity.getApplicationId());
		applyEntity.setApplyUser(applicationEntity.getProviderId());
		applyEntity.setApplyTime(new Date());
		applyEntity.setStatus(0);
		int record2 = applyEntityMapper.insert(applyEntity);
		if (record1 ==1 && record2 ==1 ) {
			logger.info("应用信息修改申请成功，请等待审核！");
		}
		}
		
		
	}

	@Override
	public void deleteApplicationPublish(String applicationId,String userName) throws Exception {
		ApplicationEntityWithBLOBs entity = new ApplicationEntityWithBLOBs();
		entity.setApplicationId(applicationId);
		entity.setStatus(3);
		entity.setOffShelveDescription("用户"+userName+"执行下架操作");
		
		int record =applicationEntityMapper.updateByPrimaryKeySelective(entity);		
		if (record ==1 ) {
			logger.info("应用下架成功！");
		}
	}
	@Override
	public void insertApplicationPublish(ApplicationEntityWithBLOBs applicationEntity, String userId,ApplyEntity applEntity) 
			throws Exception {
		
		Date nowTime = new Date();
		//String applicationId = CommonUtil.getId();
		String applicationId = applicationEntity.getApplicationId();
		applicationEntity.setApplicationId(applicationId);//s值，系统自动生成
		applicationEntity.setStatus(0);
		applicationEntity.setMarkDelete(0);
		applicationEntity.setProviderId(userId);//s 当前登录用户
		applicationEntity.setCreatedTime(nowTime);
		applicationEntity.setAppVersion("v1.0");//s 默认版本
		applicationEntity.setShowPortal(0);//s show_portal默认为0
		
		
		if (!StringUtils.isEmpty(applicationEntity.getChname())) {
			applicationEntity.setApplicationName(PinYinUtil.getFullSpell(applicationEntity.getChname()));//s 值采用字段chname的拼音
		}
		String applicationType = applicationEntity.getApplicationType();
		if (!StringUtils.isEmpty(applicationType)) { //根据应用类型插入不同logo
			if ( applicationType.equals(CommonConstants.APP_TYPE_GOVERNMENT)) { //政务类
				applicationEntity.setLogoPath("static/extends/images/upload/zhengwuLogo.jpg");
			}
			if ( applicationType.equals(CommonConstants.APP_TYPE_MEDICINE)) { //医药类
				applicationEntity.setLogoPath("static/extends/images/upload/yiyaoLogo.png");
			}
			if ( applicationType.equals(CommonConstants.APP_TYPE_TRAFFIC)) { //交通类
				applicationEntity.setLogoPath("static/extends/images/upload/jiaotongLogo.png");
			}
			if ( applicationType.equals(CommonConstants.APP_TYPE_EDUCATION)) { //教育类
				applicationEntity.setLogoPath("static/extends/images/upload/sr_pic1.jpg");
			}
		}
		int record1 = applicationEntityMapper.insertSelective(applicationEntity);
		
		ApplyEntity applyEntity = new ApplyEntity();
		applyEntity.setApplyId(CommonUtil.getId());
		applyEntity.setApplyType("01");
		if(applEntity!=null){
			applyEntity.setApplyExplanation(applEntity.getApplyExplanation());//add 申请说明
		}
		applyEntity.setApplicationId(applicationId);
		applyEntity.setApplyUser(userId);
		applyEntity.setApplyTime(nowTime);
		applyEntity.setStatus(0);
		int record2 = applyEntityMapper.insertSelective(applyEntity);
		if (record1 == 1 && record2 == 1 ) {
			logger.info("发布新应用申请成功，请等待审核！");
		}
		
		
		
	}

	@Override
	public void publishNewApplication(
			ApplicationEntityWithBLOBs applicationEntity, String userId,
			String userName, String password,ApplyEntity applyEntity) throws Exception {
		
		if ( userName.equals("") || password.equals("") || userName==null ||password ==null) { //如果不是公用rest接口
			this.insertApplicationPublish(applicationEntity, userId,applyEntity);
		} else {  //如果是公用rest接口
			UserEntityExample userExample = new UserEntityExample();
			userExample.createCriteria().andUserNameEqualTo(userName).andPasswordEqualTo(password);
			List<UserEntity> user = userEntityMapper.selectByExample(userExample);
			if ( user.size() != 0) {  //判断用户名密码是否有效
				this.insertApplicationPublish(applicationEntity, userId,applyEntity);
			} else {
				logger.info("用户名密码错误，应用发布失败！");
			}
		}
		
	}
	@Autowired
	ApplyMapperExt applyMapperExt;


}
