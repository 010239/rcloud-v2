package com.chinasofti.rcloud.portal.userconsole.domain;

import com.chinasofti.rcloud.domain.TransactionRecordEntity;

public class TransactionRecordEntityExt extends TransactionRecordEntity {
	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 1L;
	private String dateShow;

	public String getDateShow() {
		return dateShow;
	}

	public void setDateShow(String dateShow) {
		this.dateShow = dateShow;
	}
}
