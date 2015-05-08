package com.chinasofti.rcloud.portal.userconsole.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chinasofti.rcloud.common.Algorithm3DES;
import com.chinasofti.rcloud.common.AlgorithmData;
import com.chinasofti.rcloud.common.CommonConstants;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.common.SystemParametersCacheService;
import com.chinasofti.rcloud.common.constants.BusinessConstants;
import com.chinasofti.rcloud.common.constants.ExceptionContants;
import com.chinasofti.rcloud.dao.AccessKeyRecordEntityMapper;
import com.chinasofti.rcloud.dao.UserEntityMapper;
import com.chinasofti.rcloud.domain.AccessKeyRecordEntity;
import com.chinasofti.rcloud.domain.BusinessOrderEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.log.service.LogService;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;
import com.chinasofti.rcloud.portal.userconsole.dao.AccessKeyRecordEntityMapperExt;
import com.chinasofti.rcloud.portal.userconsole.service.UseServiceCredentialService;
import com.chinasofti.rcloud.portal.userorder.dao.BusinessOrderEntityMapperExt;

@Service("useServiceCredentialService")
public class UseServiceCredentialServiceImpl implements
		UseServiceCredentialService {

	//	private static final long VALI_TIMEOUT = 120;

	@Autowired
	private AccessKeyRecordEntityMapper accessKeyRecordEntityMapper;

	@Autowired
	private AccessKeyRecordEntityMapperExt accessKeyRecordEntityMapperExt;

	@Autowired
	private UserEntityMapper userEntityMapper;

	@Autowired
	private SystemParametersCacheService cacheService;

	@Autowired
	private BusinessOrderEntityMapperExt businessOrderEntityMapperExt;

	@Autowired
	private LogService logService;

	/**
	 * 生成服务凭证
	 * 
	 * @param entity
	 * @throws Exception
	 */
	@Override
	public void insertServiceCredential(UserEntityExt user) throws Exception {
		String orgGuid = user.getOrganizationId();
		String userGuid = user.getUaaGuid();
		String userId = user.getUserId();

		// 组装accessKeyId
		int i = (int) (Math.random() * 9000 + 1000);
		// String
		// serviceName=accessKeyRecordEntityMapperExt.getServiceNameById(serviceId);
		String accessKeyId = BusinessConstants.SERVICECREDENTIAL_MARK + "-" + i;

		// 组装accessKeySecret
		AlgorithmData data = new AlgorithmData();
		data.setDataMing(BusinessConstants.SERVICECREDENTIAL_MARK);
		Algorithm3DES.encryptMode(data);
		String accessKeySecret = data.getKey();

		Date d = new Date();
		AccessKeyRecordEntity entity = new AccessKeyRecordEntity();
		entity.setAccessKeyId(accessKeyId);
		entity.setAccessKeySecret(accessKeySecret);
		entity.setStatus(BusinessConstants.SERVICECREDENTIAL_ACTIVATE);
		entity.setOrgGuid(orgGuid);
		entity.setUserGuid(userGuid);
		entity.setUserId(userId);
		entity.setCreatedTime(d);
		entity.setActiveTime(d);

		accessKeyRecordEntityMapper.insertSelective(entity);

		// 操作日志
		logService.insertLog(user, "服务凭证生成", "生成服务凭证" + accessKeyId);

	}

	/**
	 * 激活服务凭证
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean activateServiceCredential(String id, String orderId)
			throws Exception {
		int i = accessKeyRecordEntityMapperExt.validOrderCount(orderId);
		if (i > 0) {
			AccessKeyRecordEntity entity = new AccessKeyRecordEntity();
			entity.setAccessKeyId(id);
			// entity.setServiceOrderId(orderId);
			entity.setStatus(BusinessConstants.SERVICECREDENTIAL_ACTIVATE);
			entity.setActiveTime(new Date());
			accessKeyRecordEntityMapperExt.activateServiceCredential(entity);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 注销服务凭证
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean cancelServiceCredential(String id, UserEntityExt user)
			throws Exception {
		accessKeyRecordEntityMapperExt.cancelServiceCredential(id,
				BusinessConstants.SERVICECREDENTIAL_LOGOUT);
		logService.insertLog(user, "服务凭证撤销", "撤销服务凭证" + id);
		return true;
	}

	/**
	 * 服务凭证验证
	 * 
	 * @param jsonStr
	 * @param accessKeyId
	 * @param desStr
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map verifyServiceCredential(String jsonStr, String accessKeyId,
			String desStr, String serviceName) throws Exception {
		// 报文转MD5签名
		String md5Code = jsonStr;
		Map resultMap = new HashMap();

		// MD5签名做3DES加密
		HashMap<String, String> map = accessKeyRecordEntityMapperExt
				.getAccessKeySecretById(accessKeyId);
		if (map == null || map.isEmpty()) {
			resultMap.put("resCode",
					BusinessConstants.SERVICECREDENTIALVERIFY_NOEFFICACY);
			return resultMap;
		}
		String accessKeySecret = map.get("secret");
		String userId = (String) map.get("userId");
		AlgorithmData data = new AlgorithmData();
		data.setDataMing(md5Code);
		data.setKey(accessKeySecret);
		Algorithm3DES.encryptMode(data);
		String dataMi = data.getDataMi();
		// 校验3DES签名,如果相等，校验服务时间是否有效,无效则将服务置为失效状态
		if (dataMi.equals(desStr)) {
			Map<String, String> service = cacheService.getServiceMap();
			UserEntity user = userEntityMapper.selectByPrimaryKey(userId);
			String person = userId;
			// 若机构开发者，判断对应的机构管理员名下的订单
			if (CommonConstants.ORG_DEVLOPER.equals(user.getRoleCode())) {
				person = user.getTenant();
			}
			String serviceId = service.get(serviceName);
			Map<String, String> parameter = new HashMap<String, String>();
			parameter.put("userId", person);
			parameter.put("serviceId", serviceId);
			List<BusinessOrderEntity> orderList = businessOrderEntityMapperExt
					.getOrder(parameter);
			if (orderList != null && orderList.size() > 0) {
				BusinessOrderEntity entity = orderList.get(0);
				if (CommonConstants.SERVICE_STATUS_EFFECTIVE == entity
						.getStatus()) {
					String tenant = user.getTenant();
					resultMap.put("userId", userId);
					resultMap.put("uaaGuid", user.getUaaGuid());
					resultMap.put("tenant", tenant);
					resultMap.put("resCode",
							BusinessConstants.SERVICECREDENTIALVERIFY_SUCCESS);
				} else {
					resultMap
							.put("resCode",
									BusinessConstants.SERVICECREDENTIALVERIFY_ORDERINVALID);
				}
			} else {
				resultMap.put("resCode",
						BusinessConstants.SERVICECREDENTIALVERIFY_ORDERINVALID);
			}
		} else {
			resultMap.put("resCode",
					BusinessConstants.SERVICECREDENTIALVERIFY_NOMATCH);
		}
		return resultMap;
	}

	@Override
	public boolean getServiceOrderStatus(String tenantId, String serviceName)
			throws Exception {
		Map<String, String> serviceMap = cacheService.getServiceMap();
		String serviceId = serviceMap.get(serviceName);

		if (StringUtils.isEmpty(serviceId)) {
			throw new RCloudException(ExceptionContants.SERVICE_NOT_EXIST);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", tenantId);
		map.put("serviceId", serviceId);
		List<BusinessOrderEntity> orderList = businessOrderEntityMapperExt
				.getOrder(map);
		if (orderList != null && orderList.size() > 0) {
			BusinessOrderEntity entity = orderList.get(0);
			if (CommonConstants.SERVICE_STATUS_EFFECTIVE == entity.getStatus()) {
				return true;
			}
		}
		return false;
	}
}
