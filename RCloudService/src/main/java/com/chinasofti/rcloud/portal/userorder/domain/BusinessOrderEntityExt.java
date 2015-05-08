package com.chinasofti.rcloud.portal.userorder.domain;

import com.chinasofti.rcloud.domain.BusinessOrderEntity;



public class BusinessOrderEntityExt extends BusinessOrderEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1442604293633987554L;

	private String prodName;
	
	private String pkgName;
	
	

	public String getProdName() {
		return prodName;
	}

	public void setProName(String prodName) {
		this.prodName = prodName;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

}
