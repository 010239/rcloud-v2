package com.chinasofti.rcloud.portal.cloudservice.domain;

import java.util.Date;

import com.chinasofti.rcloud.domain.ServiceEntity;

public class ServiceEntityExt extends ServiceEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3091955296335712343L;
	
	private Date expireDate ;
	
	private int status;

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
}
