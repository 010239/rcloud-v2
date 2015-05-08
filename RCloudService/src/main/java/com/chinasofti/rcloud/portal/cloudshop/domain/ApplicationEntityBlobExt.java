package com.chinasofti.rcloud.portal.cloudshop.domain;

import com.chinasofti.rcloud.domain.ApplicationEntity;

public class ApplicationEntityBlobExt  extends ApplicationEntity{

	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 1L;
	private String briefDescription;

	public String getBriefDescription() {
		return briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}
	
}
