package com.chinasofti.rcloud.portal.userorder.domain;

import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.domain.ApplicationOrderEntityWithBLOBs;
import com.chinasofti.rcloud.domain.UserEntity;



/**
 * @author zhangjiaxing
 *
 * 2014年7月7日
 */
public class ApplicationOrderEntityExt extends ApplicationOrderEntityWithBLOBs{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8102844143857097655L;

	private ApplicationEntity application;
	
	private UserEntity user;


	public ApplicationEntity getApplication() {
		return application;
	}

	public void setApplication(ApplicationEntity application) {
		this.application = application;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	
}
