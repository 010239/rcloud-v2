package com.chinasofti.rcloud.portal.cloudshop.domain;

import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.UserEntity;

public class ApplicationEntityExt extends ApplicationEntity {
	
	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 1L;

	private UserEntity publisher;
	
	private Integer dealCount ;

	public UserEntity getPublisher() {
		return publisher;
	}

	public void setPublisher(UserEntity publisher) {
		this.publisher = publisher;
	}

	public Integer getDealCount() {
		return dealCount;
	}

	public void setDealCount(Integer dealCount) {
		this.dealCount = dealCount;
	}
	
}
