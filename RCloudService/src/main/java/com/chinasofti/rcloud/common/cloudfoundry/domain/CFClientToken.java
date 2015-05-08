package com.chinasofti.rcloud.common.cloudfoundry.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;

import com.chinasofti.rcloud.common.client.CFClientResource;
import com.chinasofti.rcloud.common.client.JSONUtil;
import com.chinasofti.rcloud.common.client.MyHttpClient;
import com.chinasofti.rcloud.domain.UserEntity;

public class CFClientToken {
	
	private String grantType;
	
	private String password;
	
	private String scope;
	
	private String userName;
	
	private Long expiresTime;
	
	private OAuthAccessToken acessToken;
	
	
	private static CFClientToken cfClientToken =null;
	
	private Logger logger = Logger.getLogger(CFClientToken.class);
	
	public static CFClientToken getAdminInstance() 
	{
		if(cfClientToken==null)
		{
			cfClientToken = new CFClientToken();
			cfClientToken.setGrantType(CFClientResource.PAAS_PLATFORM_GRANT_TYPE);
			cfClientToken.setPassword(CFClientResource.PAAS_PLATFORM_ADMIN_PW);
			cfClientToken.setUserName(CFClientResource.PAAS_PLATFORM_ADMIN_USER);
			cfClientToken.setScope(CFClientResource.PAAS_PLATFORM_ADMIN_SCOPE);
			try {
				cfClientToken.acessToken=cfClientToken.getAccessTokenByCF();
				cfClientToken.acessToken.setExpiresTime(System.currentTimeMillis()+Long.valueOf(cfClientToken.acessToken.getExpires_in())*1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return  cfClientToken;
	}
	
	private CFClientToken()
	{
		
	}
	public CFClientToken(UserEntity user) throws Exception
	{
		this.grantType = CFClientResource.PAAS_PLATFORM_GRANT_TYPE;
		this.password = user.getPassword();
		this.userName = user.getUserName();
		this.scope = CFClientResource.PAAS_PLATFORM_ADMIN_SCOPE;
		try {
			this.acessToken = this.getAccessTokenByCF();
			this.acessToken.setExpiresTime(System.currentTimeMillis()+Long.valueOf(this.acessToken.expires_in)*1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	public void setAcessToken(OAuthAccessToken acessToken) {
		this.acessToken = acessToken;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	private OAuthAccessToken getAccessTokenByCF() throws  Exception
	{
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(MyHttpClient.getNameValuePair("grant_type", this.getGrantType()));
		list.add(MyHttpClient.getNameValuePair("password", this.getPassword()));
		list.add(MyHttpClient.getNameValuePair("scope", this.getScope()));
		list.add(MyHttpClient.getNameValuePair("username", this.getUserName()));
		HttpEntity  entity = new UrlEncodedFormEntity(list);
		Header header = new BasicHeader("Authorization", "Basic Y2Y6");
		String result=MyHttpClient.postMethod(CFClientResource.PAAS_PLATFORM_TOKEN_URL, entity, header);
		
		logger.debug("getAccessTokenByCF_result------1------>" + result);
		if(result == null){ //第一次请求返回为null,再次发送请求
			result=MyHttpClient.postMethod(CFClientResource.PAAS_PLATFORM_TOKEN_URL, entity, header);
			logger.debug("getAccessTokenByCF_result------2------>" + result);
		}
		Map<String,Object> map = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		if(map.get("access_token")!=null)
		{
			OAuthAccessToken accessToken= (OAuthAccessToken) JSONUtil.JSONToObject(result, OAuthAccessToken.class);
			return accessToken;
		}else
		{
			throw new Exception(result);
		}
		
		
	}
	
	private OAuthAccessToken getAccessRefreshToken(OAuthAccessToken token) throws  IOException
	{
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(MyHttpClient.getNameValuePair("grant_type","refresh_token"));
		list.add(MyHttpClient.getNameValuePair("refresh_token", token.getRefresh_token()));
		HttpEntity  entity = new UrlEncodedFormEntity(list);
		Header header = new BasicHeader("Authorization", "Basic Y2Y6");
		String result=MyHttpClient.postMethod(CFClientResource.PAAS_PLATFORM_TOKEN_URL, entity, header);
//		ObjectMapper mapper = new  ObjectMapper();
		OAuthAccessToken accessToken= (OAuthAccessToken) JSONUtil.JSONToObject(result, OAuthAccessToken.class);
		return accessToken;
	}
	
   public OAuthAccessToken getAcessToken() throws Exception {
		
	   if(this.acessToken==null)
	   {
		   this.acessToken = this.getAccessTokenByCF();
		   this.acessToken.setExpiresTime(System.currentTimeMillis()+Long.valueOf(acessToken.expires_in));
	   }else if(this.acessToken.getExpiresTime().longValue()<=System.currentTimeMillis())
		{
			this.acessToken = this.getAccessRefreshToken(this.acessToken);
			this.acessToken.setExpiresTime(System.currentTimeMillis()+Long.valueOf(acessToken.expires_in));
		}
		return acessToken;
	}
	
	public static void main(String[] args) throws Exception {
//		ObjectMapper mapper = new  ObjectMapper();
	     CFClientToken.getAdminInstance();
		
//		token.getAccessRefreshToken(token.getAccessTokenByCF());
		
	}
	

}
