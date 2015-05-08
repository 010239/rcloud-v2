package com.chinasofti.rcloud.portal.userbill.domain;


import com.chinasofti.rcloud.domain.BossServiceBillEntity;

public class BossServiceBillEntityExt extends BossServiceBillEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6538857162612225803L;
	
	
	private String prodName;
	
	private String createTime;
	

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



}
