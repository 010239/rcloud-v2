package com.chinasofti.rcloud.portal.cloudservice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.CommonConstants;
import com.chinasofti.rcloud.common.CommonUtil;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.common.constants.ExceptionContants;
import com.chinasofti.rcloud.dao.BusinessOrderEntityMapper;
import com.chinasofti.rcloud.dao.CurveSegmentsEntityMapper;
import com.chinasofti.rcloud.dao.PkgDetailEntityMapper;
import com.chinasofti.rcloud.dao.PkgEntityMapper;
import com.chinasofti.rcloud.dao.PkgInstanceEntityMapper;
import com.chinasofti.rcloud.dao.ProductsEntityMapper;
import com.chinasofti.rcloud.dao.ServiceEntityMapper;
import com.chinasofti.rcloud.domain.BusinessOrderEntity;
import com.chinasofti.rcloud.domain.CurveSegmentsEntityExample;
import com.chinasofti.rcloud.domain.PkgDetailEntityExample;
import com.chinasofti.rcloud.domain.PkgDetailEntityKey;
import com.chinasofti.rcloud.domain.PkgInstanceEntity;
import com.chinasofti.rcloud.domain.ProductsEntity;
import com.chinasofti.rcloud.domain.ProductsEntityExample;
import com.chinasofti.rcloud.domain.ServiceEntity;
import com.chinasofti.rcloud.domain.ServiceEntityExample;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.cloudservice.domain.ServiceEntityExt;
import com.chinasofti.rcloud.portal.cloudservice.service.RcloudService;
import com.chinasofti.rcloud.portal.log.service.LogService;
import com.chinasofti.rcloud.portal.userorder.dao.BusinessOrderEntityMapperExt;

/**
 * @author zhangjiaxing
 *
 *         2014年9月24日
 */
@Service
public class RcloudServiceImpl implements RcloudService {

	@Autowired
	private ProductsEntityMapper productsEntityMapper;

	@Autowired
	private PkgEntityMapper pkgEntityMapper;

	@Autowired
	private BusinessOrderEntityMapper businessOrderEntityMapper;

	@Autowired
	private BusinessOrderEntityMapperExt businessOrderEntityMapperExt;

	@Autowired
	private PkgDetailEntityMapper pkgDetailMapper;

	@Autowired
	private CurveSegmentsEntityMapper curveSegmentsMapper;

	@Autowired
	private PkgInstanceEntityMapper pkgInstanceMapper;

	@Autowired
	private ServiceEntityMapper serviceMapper;

	@Autowired
	private LogService logService;

	@Override
	public ProductsEntity getserviceList(String serviceId) throws Exception {
		ProductsEntityExample example = new ProductsEntityExample();
		// 条件：产品生效日期小于今天，失效日期大于今天，状态为已发布
		Date now = new Date();
		example.createCriteria().andServiceIdEqualTo(serviceId)
				.andValidDateLessThan(now).andExpireDateGreaterThan(now)
				.andStatusEqualTo(1);
		List<ProductsEntity> list = productsEntityMapper
				.selectByExample(example);
		if (list == null || list.size() == 0)
			throw new RCloudException(
					ExceptionContants.ERROR_MSG
							.get(ExceptionContants.PRODUCTS_NOT_EXIST),
					ExceptionContants.PRODUCTS_NOT_EXIST);
		ProductsEntity products = list.get(0);
		return products;
	}

	@Override
	public void insertBusinessOrder(String userId, String prodId, String pkgId,
			UserEntity user, String prodName, String serviceId)
			throws Exception {

		BusinessOrderEntity order = new BusinessOrderEntity();
		Date nowDate = new Date();
		int pkgType = pkgEntityMapper.selectByPrimaryKey(pkgId).getPkgType();
		// 如果为固定费率
		if (pkgType == 2) {
			PkgDetailEntityExample detailExample = new PkgDetailEntityExample();
			detailExample.createCriteria().andPkgIdEqualTo(pkgId);
			List<PkgDetailEntityKey> list = pkgDetailMapper
					.selectByExample(detailExample);

			String businessOrderId = CommonUtil.getId();

			order.setBusinessOrderId(businessOrderId);
			order.setUserId(userId);
			order.setProdId(prodId);
			order.setPkgId(pkgId);
			order.setBusinessOrderType(0);
			order.setStatus(CommonConstants.SERVICE_STATUS_EFFECTIVE);
			order.setCreateTime(nowDate);
			order.setPayPattern(2);
			order.setMarkDelete(0);
			order.setValidDate(nowDate);
			order.setAccountTag(1);
			businessOrderEntityMapper.insertSelective(order);

			CurveSegmentsEntityExample curveExample = new CurveSegmentsEntityExample();
			for (int i = 0; i < list.size(); i++) {
				PkgInstanceEntity pkgInstance = new PkgInstanceEntity();
				curveExample.clear();
				curveExample
						.createCriteria()
						.andStatusEqualTo(
								CommonConstants.SERVICE_STATUS_EFFECTIVE)
						.andValidDateLessThan(nowDate)
						.andExpireDateGreaterThan(nowDate)
						.andRateIdEqualTo(list.get(i).getRateId());
				String segmentId = curveSegmentsMapper
						.selectByExample(curveExample).get(0).getSegmentId();
				pkgInstance.setBusinessOrderId(businessOrderId);
				pkgInstance.setPkgId(pkgId);
				pkgInstance.setRateId(list.get(i).getRateId());
				pkgInstance.setSegmentId(segmentId);
				pkgInstanceMapper.insert(pkgInstance);
			}

			// 如果为非固定费率
		} else {
			order.setBusinessOrderId(CommonUtil.getId());
			order.setUserId(userId);
			order.setProdId(prodId);
			order.setPkgId(pkgId);
			order.setBusinessOrderType(1);
			order.setStatus(CommonConstants.SERVICE_STATUS_EFFECTIVE);
			order.setCreateTime(nowDate);
			order.setPayPattern(2);
			order.setMarkDelete(0);
			order.setValidDate(nowDate);
			order.setAccountTag(1);
			businessOrderEntityMapper.insertSelective(order);
		}
		String chname = serviceMapper.selectByPrimaryKey(serviceId).getChname();
		String description = "开通服务" + chname + "，产品" + prodName + "开通";
		logService.insertLog(user, "开通服务", description);
	}

	@Override
	public Map<String, List> getServicePlatform(String userId) throws Exception {

		Map<String, List> data = new HashMap<String, List>();

		List<ServiceEntityExt> listOpen = new ArrayList<ServiceEntityExt>();
		List<ServiceEntity> listClose = new ArrayList<ServiceEntity>();

		List<HashMap> list = businessOrderEntityMapperExt
				.getServiceList(userId);
		List<String> serviceIdList = new ArrayList<String>();

		ServiceEntityExample exampleService = new ServiceEntityExample();

		// 如果有已经开通的服务
		if (list.size() > 0) {
			for (int j = 0; j < list.size(); j++) {
				serviceIdList.add((String) list.get(j).get("serviceId"));
			}
			exampleService.createCriteria().andMarkDeleteEqualTo(0)
					.andServiceIdIn(serviceIdList);
			List<ServiceEntity> ser = serviceMapper
					.selectByExampleWithBLOBs(exampleService);
			for (int i = 0; i < ser.size(); i++) {
				ServiceEntityExt serviceEntityExt = new ServiceEntityExt();
				serviceEntityExt.setServiceId(ser.get(i).getServiceId());
				serviceEntityExt.setChname(ser.get(i).getChname());
				serviceEntityExt.setCreatedTime(ser.get(i).getCreatedTime());
				serviceEntityExt.setCreator(ser.get(i).getCreator());
				serviceEntityExt.setLogoPath(ser.get(i).getLogoPath());
				serviceEntityExt.setManageUrl(ser.get(i).getManageUrl());
				serviceEntityExt.setServiceDescription(ser.get(i)
						.getServiceDescription());
				serviceEntityExt.setMarkDelete(ser.get(i).getMarkDelete());
				serviceEntityExt.setServiceName(ser.get(i).getServiceName());
				serviceEntityExt
						.setShowDetailUrl(ser.get(i).getShowDetailUrl());
				serviceEntityExt.setShowOrder(ser.get(i).getShowOrder());
				serviceEntityExt
						.setShowPriceHour(ser.get(i).getShowPriceHour());
				serviceEntityExt
						.setShowPriceYear(ser.get(i).getShowPriceYear());

				serviceEntityExt.setStatus(Integer.parseInt(list.get(i)
						.get("status").toString()));
				listOpen.add(serviceEntityExt);
			}
			ServiceEntityExample exampleService2 = new ServiceEntityExample();
			exampleService2.createCriteria().andServiceIdNotIn(serviceIdList)
					.andMarkDeleteEqualTo(0);
			listClose = serviceMapper.selectByExample(exampleService2);

			data.put("open", listOpen);
			data.put("close", listClose);
			// 如果没有开通的服务
		} else {
			exampleService.createCriteria().andMarkDeleteEqualTo(0);
			listClose = serviceMapper.selectByExample(exampleService);
			data.put("open", listOpen);
			data.put("close", listClose);
		}
		return data;
	}

	@Override
	public List<BusinessOrderEntity> getCloudServiceOrder(String userId,
			String serviceId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("serviceId", serviceId);
		List<BusinessOrderEntity> orderList = businessOrderEntityMapperExt
				.getOrder(map);
		return orderList;
	}

}
