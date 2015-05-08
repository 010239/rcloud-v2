package com.chinasofti.rcloud.portal.userbill.vo;

import java.math.BigDecimal;
/**
* @ClassName: DebtsRecordVo
* @Description: 续费页面
* @author shimeihua
* @date 2014年10月16日 下午4:33:29
*
 */

public class DebtsRecordVo {
	public DebtsRecordVo(){}
	private String bId;
	private String userId;
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	private String userName;
	
	private Integer chargeMonth;
	
	private Integer chargeYear;
	
	private String serviceBillId;
	
	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getChargeMonth() {
		return chargeMonth;
	}

	public void setChargeMonth(Integer chargeMonth) {
		this.chargeMonth = chargeMonth;
	}

	public Integer getChargeYear() {
		return chargeYear;
	}

	public void setChargeYear(Integer chargeYear) {
		this.chargeYear = chargeYear;
	}

	public String getServiceBillId() {
		return serviceBillId;
	}

	public void setServiceBillId(String serviceBillId) {
		this.serviceBillId = serviceBillId;
	}

	public BigDecimal getDebtsCosts() {
		return debtsCosts;
	}

	public void setDebtsCosts(BigDecimal debtsCosts) {
		this.debtsCosts = debtsCosts;
	}
	private BigDecimal debtsCosts;
	
	
	
	
	
}
