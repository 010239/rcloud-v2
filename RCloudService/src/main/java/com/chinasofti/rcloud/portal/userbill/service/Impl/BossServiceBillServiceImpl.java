package com.chinasofti.rcloud.portal.userbill.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.CommonUtil;
import com.chinasofti.rcloud.common.Pagination;
import com.chinasofti.rcloud.dao.BillingMonthDetailEntityMapper;
import com.chinasofti.rcloud.dao.RatesEntityMapper;
import com.chinasofti.rcloud.dao.TransactionRecordEntityMapper;
import com.chinasofti.rcloud.domain.BillingMonthEntity;
import com.chinasofti.rcloud.domain.TransactionRecordEntity;
import com.chinasofti.rcloud.domain.UserAccountEntity;
import com.chinasofti.rcloud.portal.userbill.dao.BillingMonthEntityMapperExt;
import com.chinasofti.rcloud.portal.userbill.dao.BossDebtsMapperExt;
import com.chinasofti.rcloud.portal.userbill.dao.BossServiceBillEntityMapperExt;
import com.chinasofti.rcloud.portal.userbill.domain.BossBillInfoEntityExt;
import com.chinasofti.rcloud.portal.userbill.domain.BossDebtsEntityExt;
import com.chinasofti.rcloud.portal.userbill.domain.BossServiceBillEntityExt;
import com.chinasofti.rcloud.portal.userbill.service.BossServiceBillService;
import com.chinasofti.rcloud.portal.userbill.vo.DebtsRecordVo;
import com.chinasofti.rcloud.portal.userconsole.dao.UserAccountMapperExt;
import com.chinasofti.rcloud.portal.userorder.dao.BusinessOrderEntityMapperExt;
import com.chinasofti.rcloud.portal.userorder.domain.BusinessOrderEntityExt;

@Service("bossServiceBillService")
public class BossServiceBillServiceImpl implements BossServiceBillService{
	 private static Logger logger = Logger.getLogger(BossServiceBillServiceImpl.class);

	@Autowired
	private BossServiceBillEntityMapperExt bossBillMapperExt;
	
	@Autowired
	private RatesEntityMapper ratesMapper;
	
	@Autowired
	private BillingMonthDetailEntityMapper billingMonthDetailEntityMapper;
	
	@Override
	public Pagination<BossServiceBillEntityExt> searchBillByPage(
			Map<String, Object> searchParams) throws Exception {
		
		Pagination<BossServiceBillEntityExt>  pagination = new Pagination<BossServiceBillEntityExt>();
		List<BossServiceBillEntityExt> rows = bossBillMapperExt.getBillByPage(searchParams);
		int total = bossBillMapperExt.countBillList(searchParams);
		
		pagination.setRows(rows);
		pagination.setTotal(total);
		return pagination;
	}


	@Override
	public List<BossBillInfoEntityExt> getBillInfo(Map<String, Object> searchParams)
			throws Exception {
		
		
		return bossBillMapperExt.getBillInfo(searchParams);
	}
	@Override
	public String updateArrearsBillingRenewals(DebtsRecordVo debtsRecordVo)throws Exception{
	try {
	//1、修改表boss_debts_record,  将字段status置为 1-已还，字段payment_date置当前日期；
		updateDebtsRecord(debtsRecordVo);
	//2. 用户账户 余额	
		updatePortalAccount(debtsRecordVo);
	//3.插入日志 portal_transaction_record	
		insertTransactionRecord(debtsRecordVo);
	//4.查询表boss_billing_month，获取值prod_id
		List<BillingMonthEntity> billingMonthEntityExts=getBillingMonthByCon(debtsRecordVo);
	//5、查询表portal_business_order,将查到的数据中的字段account_tag置为1-正常；status置为1-有效；restart_time置为当前时间
		if(billingMonthEntityExts != null){
			for (BillingMonthEntity billingMonthEntity : billingMonthEntityExts) {
				updateOrderInfo(billingMonthEntity);
			}
		}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("updateArrearsBillingRenewals", e);
			throw new Exception();
		}
		return null;
	}
	@Override
	public BossDebtsEntityExt getDebtsInfoByServiceBillId(String serviceBillId)
			throws Exception {
		BossDebtsEntityExt debtsInfo = bossDebtsMapperExt.getServiceDebtsRecordInfo(serviceBillId);
		return debtsInfo;
	}
	
	//---------------续费分步------
	/**
	* @Title: insertTransactionRecord 
	* @Description: 插入续费 日志
	* @param @param debtsRecordVo
	* @param @throws Exception
	* @return void  
	* @throws
	 */
	public void insertTransactionRecord(DebtsRecordVo debtsRecordVo)throws Exception{
		try {
			//3、增加日志，在表portal_transaction_record中插入一条日志记录，字段transaction_type取值为7-欠费账单续费；（7，已经用来提现了）
			UserAccountEntity userAccountEntityExt=userAccountMapperExt.getUserAccountById(debtsRecordVo.getUserId());
			insertTransactionRecordEntity(userAccountEntityExt,debtsRecordVo);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("insertTransactionRecord", e);
			throw new Exception();
		}
	}
	/**
	* @Title: updateDebtsRecord 
	* @Description: 修改欠款记录 ok
	* @param @param debtsRecordVo
	* @param @throws Exception
	* @return void  
	* @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	private void updateDebtsRecord(DebtsRecordVo debtsRecordVo)throws Exception{
		try {
			Map params=new HashMap();
			params.put("status", 1);
			params.put("payment_date", new Date());
			params.put("debts_Id", debtsRecordVo.getbId());
			bossDebtsMapperExt.updateDebtOfStatus(params);//1.操作：`portal_debts_record` 欠款记录状态为已还 ，补缴日期为今天
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("debtsRecordVo", e);
			throw new Exception();
		}
	}
	/**
	* @Title: updatePortalAccount 
	* @Description: 修改账户余额  ok
	* @param @param debtsRecordVo
	* @param @throws Exception
	* @return void  
	* @throws
	 */
	@SuppressWarnings("unused")
	private void updatePortalAccount(DebtsRecordVo debtsRecordVo)throws Exception{
		try {
			userAccountMapperExt.updateUserAccount(debtsRecordVo.getDebtsCosts().doubleValue(), debtsRecordVo.getUserId());//2.操作 修改表portal_user_account，将字段money_amount的值与续费金额累加在一起；
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("updatePortalAccount", e);
			throw new Exception();
		}
		
	}
	/**
	* @Title: insertTransactionRecordEntity 
	* @Description: 续费 插入日志 ok
	* @param @param userAccountEntityExt
	* @param @param debtsRecordVo
	* @return void  
	* @throws
	 */
	@SuppressWarnings("unused")
	private void insertTransactionRecordEntity(
			UserAccountEntity userAccountEntityExt,DebtsRecordVo debtsRecordVo)throws  Exception {
	try {
		TransactionRecordEntity recordEntity = new TransactionRecordEntity();
		recordEntity.setRecordId(CommonUtil.getId());
		recordEntity.setAccountId(userAccountEntityExt.getAccountId());
		recordEntity.setUserId(userAccountEntityExt.getUserId());
		recordEntity.setAccountBalance(userAccountEntityExt.getMoneyAmount());
		recordEntity.setTransactionAmount(debtsRecordVo.getDebtsCosts());
		recordEntity.setTransactionTime(new Date());
		recordEntity.setTransactionType(8);//段transaction_type取值为7-欠费账单续费；（7已经为提现了）
		recordEntity.setMoneyFlow(1);////字段money_flow的取值，0-流出，1-流入
		recordEntityMapper.insertSelective(recordEntity);
	} catch (Exception e) {
		// TODO: handle exception
		logger.error("updatePortalAccount", e);
		throw new Exception();
	}
	}
	/**
	* @Title: getBillingMonthByCon 
	* @Description: 或者产品id ok
	* @param @param debtsRecordVo
	* @param @return
	* @param @throws Exception
	* @return BillingMonthEntityExt  
	* @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	private List<BillingMonthEntity> getBillingMonthByCon(DebtsRecordVo debtsRecordVo)throws Exception{
		List<BillingMonthEntity> billingMonthEntity=null;
		try {
			//4、查询表boss_billing_month，获取值prod_id，条件为user_id,charge_month,charge_year,取值为boss_service_bill表中的user_id,charge_month,charge_year;
			Map monthmap=new HashMap();
			monthmap.put("charge_month", debtsRecordVo.getChargeMonth());
			monthmap.put("charge_year", debtsRecordVo.getChargeYear());
			monthmap.put("user_id", debtsRecordVo.getUserId());
			billingMonthEntity=billingMonthEntityMapperExt.getBillingMonthPro(monthmap);
		} catch (Exception e) {
			logger.error("insertTransactionRecord", e);
			throw new Exception();
		}
		return billingMonthEntity;
	}
	/**
	* @Title: updateOrderInfo 
	* @Description: 修改订单信息
	* @param @param billingMonthEntityExt
	* @param @throws Exception
	* @return void  
	* @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	private void updateOrderInfo(BillingMonthEntity billingMonthEntityExt)throws Exception{
		try {
//			5、查询表portal_business_order,将查到的数据中的字段account_tag置为1-正常；status置为1-有效；restart_time置为当前时间；查询条件为
			//   上一步中获取到的prod_id,userid,status,account_tag;status=0,account_tag=0
			Map ordermap=new HashMap();
			ordermap.put("prod_id", billingMonthEntityExt.getProdId());
			ordermap.put("user_id", billingMonthEntityExt.getUserId());
			BusinessOrderEntityExt businessOrderEntityExt=orderMapperExt.getOrderInfoByCon(ordermap);
			if(businessOrderEntityExt!=null){
				//查询表portal_business_order,将查到的数据中的字段account_tag置为1-正常；status置为1-有效；restart_time置为当前时间
				businessOrderEntityExt.setAccountTag(1);
				businessOrderEntityExt.setStatus(1);
				businessOrderEntityExt.setRestartTime(new Date());
				Map ordrmap=new HashMap();
				ordrmap.put("account_tag", 1);
				ordrmap.put("status", 1);
				ordrmap.put("restart_time", new Date());
				ordrmap.put("business_order_id", businessOrderEntityExt.getBusinessOrderId());
				orderMapperExt.updateOrderInfoStatus(ordrmap);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("insertTransactionRecord", e);
			throw new Exception();
		}
	}
    @Autowired
    BossDebtsMapperExt bossDebtsMapperExt;
    @Autowired
    UserAccountMapperExt userAccountMapperExt;
    @Autowired
    TransactionRecordEntityMapper recordEntityMapper;
    @Autowired
  BillingMonthEntityMapperExt billingMonthEntityMapperExt;
  @Autowired
    BusinessOrderEntityMapperExt orderMapperExt;
}
