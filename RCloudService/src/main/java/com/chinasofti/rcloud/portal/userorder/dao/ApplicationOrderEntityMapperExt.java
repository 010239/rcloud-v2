package com.chinasofti.rcloud.portal.userorder.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.portal.userorder.domain.ApplicationOrderEntityExt;

/**
 * @author zhangjiaxing
 *
 * 2014年7月3日
 */
public interface ApplicationOrderEntityMapperExt extends BaseDao{
	
	/**
	 * 云应用订单购买查询
	 * @param searchParam
	 * @return
	 */
	List<ApplicationOrderEntityExt> getBuyApplicationOrderByPage(Map<String, Object> searchParams);
	
	/**
	 * 云应用订单售出查询
	 * @param searchParam
	 * @return
	 */
	List<ApplicationOrderEntityExt> getSaleApplicationOrderByPage(Map<String, Object> searchParams);
	List<ApplicationOrderEntityExt> getAppByBuyer(Map<String, Object> searchParams);
	

	/**
	 * 根据当前登录用户id和订单号查询购买的应用订单详情查询
	 * @param userId
	 * @param orderNumber
	 * @return
	 */
	ApplicationOrderEntityExt getBuyApplicationOrderInfo(@Param("userId") String userId,
														 @Param("orderNumber") String orderNumber);
	
	
	/**
	 * 根据当前登录用户id和订单号查询售出的应用订单详情查询
	 * @param userId
	 * @param orderNumber
	 * @return
	 */
	ApplicationOrderEntityExt getSaleApplicationOrderInfo(@Param("userId") String userId,
														  @Param("orderNumber") String orderNumber);
	
	
	/**
	 * 查询售出总订单总条数
	 * @param searchParam
	 * @return
	 */
	int countApplicationSaleOrder(Map<String, Object> searchParams);
	
	/**
	 * 查询购买总订单总条数
	 * @param searchParam
	 * @return
	 */
	int countApplicationBuyOrder(Map<String, Object> searchParams);

	
	/**
	 * 用以账单查询
	 * @param applicationOrderToBillEntityKey
	 * @return
	 */
	ApplicationOrderEntityExt getApplicationOrderInfo(@Param("orderId") String orderId);

	/**
     * 批量修改应用订单状态。
     * 
     * @param applicationOrderIdList
     * @param orderStatus
     */
	void batchUpdateOrderStatus(@Param("applicationOrderIdList") List<String> applicationOrderIdList, 
			@Param("orderStatus") int orderStatus);
	
	/**
	 * 批量修改应用订单状态为：批处理不需要处理
	 * 
	 * @param applicationOrderIdList
	 * @param accountingTag
	 */
	void batchUpdateAccountingTag(@Param("applicationOrderIdList") List<String> applicationOrderIdList, 
			@Param("accountingTag") int accountingTag);
	
	/**
	 * 根据售出者ID和应用ID查询应用成交数量
	 * @param applicationId
	 * @return
	 */
	int countApplicationPublish(@Param("applicationId") String applicationId);
}
