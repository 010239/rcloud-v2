package com.chinasofti.rcloud.portal.login.service;

public interface AuthenticationService {
	
	public boolean authenticate(String username,String password)throws Exception;

}
