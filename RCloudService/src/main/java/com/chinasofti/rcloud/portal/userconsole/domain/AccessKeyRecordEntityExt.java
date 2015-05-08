package com.chinasofti.rcloud.portal.userconsole.domain;

import com.chinasofti.rcloud.domain.AccessKeyRecordEntity;

public class AccessKeyRecordEntityExt extends AccessKeyRecordEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7604176158432619281L;
	
	private String creator;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
