package com.chinasofti.rcloud.common.cloudfoundry;

import java.io.Serializable;

public class AccessToken implements Serializable {
	
    private String accessToken=null;
	
	private String expiresTime = null;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpiresTime() {
		return expiresTime;
	}

	public void setExpiresTime(String expiresTime) {
		this.expiresTime = expiresTime;
	}
	

}
