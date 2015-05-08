package com.chinasofti.rcloud.portal.userorder.service.Impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.CommonConstants;
import com.chinasofti.rcloud.common.CommonUtil;
import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.common.cloudfoundry.UploadToCFIterface;
import com.chinasofti.rcloud.dao.ApplicationEntityMapper;
import com.chinasofti.rcloud.dao.ApplicationOrderEntityMapper;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.ApplicationEntityWithBLOBs;
import com.chinasofti.rcloud.domain.ApplicationOrderEntityWithBLOBs;
import com.chinasofti.rcloud.domain.BusinessOrderEntity;
import com.chinasofti.rcloud.domain.SpaceEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.cloudservice.service.RcloudService;
import com.chinasofti.rcloud.portal.cloudshop.vo.ApplicationMonthVo;
import com.chinasofti.rcloud.portal.cloudshop.vo.SaveSessAppOrder;
import com.chinasofti.rcloud.portal.message.domain.MessageDomain;
import com.chinasofti.rcloud.portal.message.service.MessageService;
import com.chinasofti.rcloud.portal.userconsole.service.UserInfoService;
import com.chinasofti.rcloud.portal.userorder.dao.ApplicationOrderEntityMapperExt;
import com.chinasofti.rcloud.portal.userorder.domain.ApplicationOrderEntityExt;
import com.chinasofti.rcloud.portal.userorder.service.ApplicationOrderService;

/**
 * @author zhangjiaxing
 *
 *         2014年7月4日
 */
@Service("applicationOrderService")
public class ApplicationOrderServiceImpl implements ApplicationOrderService {

	@Autowired
	private ApplicationOrderEntityMapperExt applicationOrderEntityMapperExt;

	@Autowired
	private ApplicationEntityMapper applicationEntityMapper;

	@Autowired
	private ApplicationOrderEntityMapper applicationOrderEntityMapper;

	private Logger logger = Logger.getLogger(ApplicationOrderServiceImpl.class);

	@Override
	public Pagination<ApplicationOrderEntityExt> getBuyApplicationOrderByPage(
			Map<String, Object> searchParams) throws Exception {
		Pagination<ApplicationOrderEntityExt> pagination = new Pagination<ApplicationOrderEntityExt>();
		List<ApplicationOrderEntityExt> applicationBuyOrderList = applicationOrderEntityMapperExt
				.getBuyApplicationOrderByPage(searchParams);
		if (applicationBuyOrderList == null) {
			logger.error("未查询到您购买的云应用订单！");
		}
		int count = applicationOrderEntityMapperExt
				.countApplicationBuyOrder(searchParams);
		pagination.setRows(applicationBuyOrderList);
		pagination.setTotal(count);
		return pagination;
	}

	public List<ApplicationOrderEntityExt> getApplicationOrderByBuyerAppId(
			Map<String, Object> searchParams) {
		List<ApplicationOrderEntityExt> applicationBuyOrderList = applicationOrderEntityMapperExt
				.getAppByBuyer(searchParams);
		return applicationBuyOrderList;
	}

	@Override
	public Pagination<ApplicationOrderEntityExt> getSaleApplicationOrderByPage(
			Map<String, Object> searchParams) throws Exception {
		Pagination<ApplicationOrderEntityExt> pagination = new Pagination<ApplicationOrderEntityExt>();
		List<ApplicationOrderEntityExt> applicationSaleOrderList = applicationOrderEntityMapperExt
				.getSaleApplicationOrderByPage(searchParams);
		if (applicationSaleOrderList == null) {
			logger.error("未查询到您售出的云应用订单！");
		}
		int count = applicationOrderEntityMapperExt
				.countApplicationSaleOrder(searchParams);
		pagination.setRows(applicationSaleOrderList);
		pagination.setTotal(count);
		return pagination;
	}

	@Override
	public ApplicationOrderEntityExt getBuyApplicationOrderInfo(String userId,
			String orderNumber) throws Exception {

		ApplicationOrderEntityExt applicationBuyOrderInfo = applicationOrderEntityMapperExt
				.getBuyApplicationOrderInfo(userId, orderNumber);
		return applicationBuyOrderInfo;
	}

	@Override
	public ApplicationOrderEntityExt getSaleApplicationOrderInfo(String userId,
			String orderNumber) throws Exception {

		ApplicationOrderEntityExt applicationSaleOrderInfo = applicationOrderEntityMapperExt
				.getSaleApplicationOrderInfo(userId, orderNumber);
		return applicationSaleOrderInfo;
	}

	// 2014-11-20 暂时废弃
	@Override
	public int insertApplicationOrderEntity(Map<String, Object> appBuyInfo,
			String userId) throws Exception {
		int payPattern = Integer
				.parseInt((String) appBuyInfo.get("payPattern"));
		int dueTime = Integer.parseInt((String) appBuyInfo.get("dueTime"));
		String orderDescription = (String) appBuyInfo.get("orderDescription");
		String applicationId = (String) appBuyInfo.get("applicationId");

		ApplicationOrderEntityWithBLOBs applicationOrder = new ApplicationOrderEntityWithBLOBs();
		String orderId = CommonUtil.getId();
		applicationOrder.setOrderId(orderId);
		String orderNum = CommonUtil.getId();
		applicationOrder.setOrderNumber(orderNum);
		applicationOrder.setBuyerId(userId);
		applicationOrder
				.setOrderStatus(CommonConstants.APPLICATION_STATUS_EFFECTIVE);
		applicationOrder.setOrderDescription(orderDescription);
		applicationOrder.setPayPattern(payPattern);
		if (payPattern == CommonConstants.PAY_PATTERN_ONCE) {
			applicationOrder
					.setAccountingTag(CommonConstants.ORDER_UNNEED_HANDLE);
		} else {
			applicationOrder.setAccountingTag(CommonConstants.ORDER_HANDLE);
		}

		// 得到应用的相关信息
		ApplicationEntityWithBLOBs application = applicationEntityMapper
				.selectByPrimaryKey(applicationId);
		applicationOrder.setApplicationId(applicationId);
		applicationOrder.setProviderId(application.getProviderId());
		applicationOrder.setMaintenanceCosts(application.getMaintenanceCosts());
		applicationOrder.setDeployType(application.getDeployType());

		// 设置起止时间
		Date createdTime = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(createdTime);
		int year = dueTime / 12;
		int currentYear = calendar.get(Calendar.YEAR);
		int endYear = currentYear + year;
		calendar.set(Calendar.YEAR, endYear);
		applicationOrder.setCreatedTime(createdTime);
		applicationOrder.setEndTime(calendar.getTime());

		applicationOrderEntityMapper.insert(applicationOrder);

		logger.info("生成订单成功！");

		return payPattern;
	}

	/**
	 * @Title: insertAoolicationOrder
	 * @Description: 插入订单信息
	 * @author shimeihua
	 * @param @param payPattern
	 * @param @param orderDescription
	 * @param @param application
	 * @param @param dueTime
	 * @param @param userId
	 * @param @param applicationId
	 * @param @return
	 * @return ApplicationOrderEntityWithBLOBs
	 * @throws
	 */
	public ApplicationOrderEntityWithBLOBs insertAoolicationOrder(
			int payPattern, String orderDescription,
			ApplicationEntity application, int dueTime, String userId,
			String applicationId) {
		ApplicationOrderEntityWithBLOBs applicationOrder = new ApplicationOrderEntityWithBLOBs();
		try {
			String orderId = CommonUtil.getId();
			applicationOrder.setOrderId(orderId);
			String orderNum = CommonUtil.getId();
			applicationOrder.setOrderNumber(orderNum);
			applicationOrder.setBuyerId(userId);
			applicationOrder
					.setOrderStatus(CommonConstants.APPLICATION_STATUS_EFFECTIVE);
			applicationOrder.setOrderDescription(orderDescription);
			applicationOrder.setPayPattern(payPattern);

			if (payPattern == CommonConstants.PAY_PATTERN_ONCE) {// 一次性
				applicationOrder
						.setAccountingTag(CommonConstants.ORDER_UNNEED_HANDLE);
				applicationOrder.setTotalCosts(BigDecimal.valueOf(application
						.getMaintenanceCosts().doubleValue() * dueTime)); // total_costs
			} else {
				applicationOrder.setAccountingTag(CommonConstants.ORDER_HANDLE);
				applicationOrder.setTotalCosts(application
						.getMaintenanceCosts());
			}

			applicationOrder.setApplicationId(applicationId);
			applicationOrder.setProviderId(application.getProviderId());
			applicationOrder.setMaintenanceCosts(application
					.getMaintenanceCosts());
			applicationOrder.setDeployType(application.getDeployType());
			applicationOrder.setMarkDelete(0);

			// 设置起止时间
			Date createdTime = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(createdTime);
			// BigDecimal ci = new BigDecimal(12);

			int year = dueTime / 12;
			int currentYear = calendar.get(Calendar.YEAR);
			int endYear = currentYear + year;
			calendar.set(Calendar.YEAR, endYear);
			applicationOrder.setCreatedTime(createdTime);
			applicationOrder.setEndTime(calendar.getTime());

			applicationOrderEntityMapper.insert(applicationOrder);

			logger.info("生成订单成功！");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return applicationOrder;
	}

	// smh 试用应用
	@Override
	public ApplicationMonthVo insertApplicationOrderEntityapp(
			Map<String, Object> appBuyInfo, UserEntity user) throws Exception {
		ApplicationMonthVo vos = new ApplicationMonthVo();
		int payPattern = Integer
				.parseInt((String) appBuyInfo.get("payPattern"));
		// String subDomain = (String) appBuyInfo.get("subDomain");
		int dueTime = Integer.parseInt((String) appBuyInfo.get("dueTime"));
		String orderDescription = (String) appBuyInfo.get("orderDescription");
		String applicationId = (String) appBuyInfo.get("applicationId");
		vos.setPayPattern(payPattern);//
		// 得到应用的相关信息
		ApplicationEntity application = applicationEntityMapper
				.selectByPrimaryKey(applicationId);
		vos.setApplicationEntity(application);
		if (payPattern == CommonConstants.PAY_PATTERN_MONTH) {// 月支付
			ApplicationOrderEntityWithBLOBs applicationOrder = this
					.insertAoolicationOrder(payPattern, orderDescription,
							application, dueTime, user.getUserId(),
							applicationId);
			if (applicationOrder != null) {
				vos.setOrderNum(applicationOrder.getOrderNumber());
			}
			if (CommonConstants.DEPLOYMENT_TYPE_PRE.equals(application
					.getDeployType().toString())) {// 预部署
				sendMessage(user, application.getChname(),
						application.getProviderId());
			} else {
				// 后部署 是否开通服务
				List<BusinessOrderEntity> list = rcloudService
						.getCloudServiceOrder(user.getUserId(),
								CommonConstants.RAE_SERVICE_ID);
				if (list != null && list.size() > 0) {// 开通服务
					// 进入部署rae页面
					vos.setOpenIsRae(true);
					List<SpaceEntity> lists = uploadToCFIterface
							.getSpaceEntitys(user);
					vos.setLists(lists);

				} else {
					// 提示 开通云引擎服务；
				}
			}
			vos.setMaintenanceCosts(applicationOrder.getTotalCosts().toString());
		} else {
			// 一次性付费 需要跳转到支付页面 把订单信息暂存session
			vos.setSaveSession(true);
			SaveSessAppOrder saveSessAppOrder = new SaveSessAppOrder();
			saveSessAppOrder.setOrderDescription(orderDescription);
			saveSessAppOrder.setDueTime(dueTime);
			saveSessAppOrder.setPayPattern(payPattern);
			vos.setMaintenanceCosts((BigDecimal.valueOf(application
					.getMaintenanceCosts().doubleValue() * dueTime)).toString());
			vos.setSaveSessAppOrder(saveSessAppOrder);
		}

		return vos;
	}

	// 用户id 购买者 向发布者发送消息 1.用户id 2.开发者id
	@Override
	public void sendMessage(UserEntity user, String appname, String developId) {
		MessageDomain m1 = new MessageDomain();

		m1.setContent("应用：" + appname + "购买成功，请尽快部署！");
		m1.setReceiverId(developId);// 接收者 ff80818147c3e2950147c3e295780000
		m1.setSendTime(new Date());
		m1.setStatus(false);// 状态：false-未读，true-已读
		try {
			if (user != null) {
				m1.setSender(user.getUserName());
			}
			messageService.saveMessage(m1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("sendMessage提醒部署消息异常", e);
		}
	}

	@Autowired
	MessageService messageService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	RcloudService rcloudService;
	@Autowired
	private UploadToCFIterface uploadToCFIterface;

}
