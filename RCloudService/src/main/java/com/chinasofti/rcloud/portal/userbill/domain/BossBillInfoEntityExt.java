package com.chinasofti.rcloud.portal.userbill.domain;

import java.math.BigDecimal;

public class BossBillInfoEntityExt {
	private BigDecimal charge;
	private String billingMonthId;
	private String prodName;
	private String createTime;
	
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getBillingMonthId() {
		return billingMonthId;
	}
	public void setBillingMonthId(String billingMonthId) {
		this.billingMonthId = billingMonthId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
}
