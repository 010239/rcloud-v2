package com.chinasofti.rcloud.portal.userbill.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.domain.BossDebtsRecordEntity;

public class BossDebtsEntityExt extends BossDebtsRecordEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8796886041576995597L;

	private String createDate;
	
	private BigDecimal totalCharge;
	
	private Integer chargeMonth;
	
	private Integer chargeYear;
	
	private List<Map> dataList;

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
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

	public List<Map> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map> dataList) {
		this.dataList = dataList;
	}
}
