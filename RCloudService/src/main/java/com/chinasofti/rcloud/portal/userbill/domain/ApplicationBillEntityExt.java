package com.chinasofti.rcloud.portal.userbill.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.chinasofti.rcloud.domain.ApplicationBillEntity;

/**
 * @author zhangjiaxing
 *
 * 2014年7月10日
 */
public class ApplicationBillEntityExt extends ApplicationBillEntity{
	
	private static final long serialVersionUID = -2244398733169006384L;

	private String applicationName;
	
	private BigDecimal maintenanceCosts;
	
	private List<Map> dataList;
	
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public BigDecimal getMaintenanceCosts() {
		return maintenanceCosts;
	}
	public void setMaintenancecosts(BigDecimal maintenanceCosts) {
		this.maintenanceCosts = maintenanceCosts;
	}
	public List<Map> getDataList() {
		return dataList;
	}
	public void setDataList(List<Map> dataList) {
		this.dataList = dataList;
	}
}
