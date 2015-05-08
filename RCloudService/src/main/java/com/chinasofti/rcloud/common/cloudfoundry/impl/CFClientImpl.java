package com.chinasofti.rcloud.common.cloudfoundry.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.client.CFClientResource;
import com.chinasofti.rcloud.common.client.JSONUtil;
import com.chinasofti.rcloud.common.client.MyHttpClient;
import com.chinasofti.rcloud.common.cloudfoundry.CFClient;
import com.chinasofti.rcloud.common.cloudfoundry.domain.CFBean;
import com.chinasofti.rcloud.common.cloudfoundry.domain.CFClientToken;
import com.chinasofti.rcloud.common.cloudfoundry.domain.CreateOrgRequestFormat;
import com.chinasofti.rcloud.common.cloudfoundry.domain.CreateSpaceRequestFormat;
import com.chinasofti.rcloud.common.cloudfoundry.domain.QuotaBean;
import com.chinasofti.rcloud.common.cloudfoundry.domain.UpdateSpaceRequestFormat;
import com.chinasofti.rcloud.common.cloudfoundry.domain.CreateUserRequestFormat;
import com.chinasofti.rcloud.common.constants.ExceptionContants;
import com.chinasofti.rcloud.domain.OrganizationEntity;
import com.chinasofti.rcloud.domain.SpaceEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Service
public class CFClientImpl implements CFClient {	
	
	 private  CFClientToken admintoken = CFClientToken.getAdminInstance();
	 private static Logger logger = Logger.getLogger(CFClientImpl.class);

	@Override
	public void createOrgAdmin(UserEntity user) throws Exception {
		
		    //注册用户到cF平台
		    this.createUser(user);
			//注册用户的guid
			this.createUserByGuid(user.getUaaGuid());
			//将用户授权为组织管理者:用户
			this.authorizeToOrgUser(user);
			this.authorizeToOrgManager(user);
	}
	
	@Override
	public void createOrgDeveloper(UserEntity user,CFClientToken clientToken)throws Exception {
		//注册用户到
		this.createUser(user);
		this.createUserByGuid(user.getUaaGuid());
	
		//授权机构权限
		this.authorizeToOrgUser(user);	
	}
	
	@Override
	public void createDeveloper(UserEntity user) throws Exception {
		//注册用户
		this.createUser(user);
		//在cf平台中创建该用户
		this.createUserByGuid(user.getUaaGuid());
		//创建组织
		OrganizationEntity   organizationEntity = new OrganizationEntity();
		String email = user.getUserEmail();
		organizationEntity.setOrganizationName(getCFName(email, "org_"));
		this.createOrg(organizationEntity);		
		user.setCfOrgGuid(organizationEntity.getCfOrgGuid());
		
		//授权组织管理者
		this.authorizeToOrgUser(user);
		this.authorizeToOrgManager(user);
		
		CFClientToken clientToken = this.getUserToken(user);
		
		//创建空间
		SpaceEntity entity = new SpaceEntity();
		entity.setSpaceName(this.getCFName(email, "space_"));
		entity.setCfOrgGuid(organizationEntity.getCfOrgGuid());
		this.createSpace(entity,clientToken);		
		
		//授权空间管理
		this.authorizeToSpaceManager(user, entity,clientToken);
		//授权空间开发者
		this.authorizeToSpaceDeveloper(user, entity,clientToken);		
	}
	private static String getCFName(String str, String name) {		
		  name+=str.substring(str.indexOf("@")+1,str.length());
		  name+="_"+str.substring(0,str.indexOf("@"));
		  name=name.replaceAll("\\.", "_");
		return name;
	}

	@Override
	public void createOrg(OrganizationEntity org) throws Exception {
		
		    Header  header = MyHttpClient.getAuthorizationHeader(admintoken);
			Map map = new HashMap();
			map.put("name", org.getOrganizationName());
			map.put("billing_enabled", true);
			StringEntity stringEntity =new StringEntity(JSONUtil.ObjectToJSON(map));
			String result = MyHttpClient.postMethod(CFClientResource.PAAS_PLATFORM_CREATE_ORG_URL, stringEntity, header);
			Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
			Map meta=(Map) hashMap.get("metadata");
			if(meta!=null){
				org.setCfOrgGuid(meta.get("guid").toString());
			}  
			else
			{
				logger.info(result);
				throw new Exception(result);
			}
	}

	@Override
	public void createSpace(SpaceEntity space,CFClientToken clientToken) throws Exception{		
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		Map map = new HashMap();
		map.put("name", space.getSpaceName());
		map.put("organization_guid", space.getCfOrgGuid());
		StringEntity entity = new StringEntity(JSONUtil.ObjectToJSON(map));
		String result=MyHttpClient.postMethod(CFClientResource.getCreateSpaceUrl(), entity, header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		Map meta=(Map) hashMap.get("metadata");
		if(meta!=null){
			space.setCfSpaceGuid(meta.get("guid").toString());
		}  
		else
		{
			logger.info(result);
			throw new Exception(result);
		}		
	}
	

	@Override
	public void bindUserToSpace(UserEntity user, SpaceEntity space, CFClientToken clientToken)throws Exception {	
			this.authorizeToSpaceDeveloper(user, space,clientToken);		
	}

	@Override
	public void unBindUserToSpace(UserEntity user, SpaceEntity space, CFClientToken clientToken)throws Exception {	
			this.deleteSpaceDeveloper(user, space,clientToken);		
	}

	@Override
	public CFClientToken login(UserEntity user) throws Exception  {
		CFClientToken cfClientToken = null;
		try {
			cfClientToken = this.getUserToken(user);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return cfClientToken;		
	}

	@Override
	public void adjustPlan(OrganizationEntity org, String plan,CFClientToken clientToken) throws Exception{		
		Header header  = MyHttpClient.getAuthorizationHeader(clientToken);
		Map map = new HashMap();
		map.put("quota_definition_guid", plan);
		StringEntity stringEntity = new StringEntity(JSONUtil.ObjectToJSON(map));
		stringEntity.setContentType("application/json");
		String result = MyHttpClient.putMethod(CFClientResource.getAdjustPlanUrl(org.getCfOrgGuid()), stringEntity, header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		Map meta=(Map) hashMap.get("metadata");
		if(meta!=null){
			org.setCfOrgGuid(meta.get("guid").toString());
		}  
		else
		{
			logger.info(result);
			throw new Exception(result);
		}		
	}
	
	@Override
	public boolean changeUserPassword(UserEntity userEntity,String oldPassword) throws Exception {		
		return this.modifyUserPassword(userEntity, oldPassword);
	}
	
	private boolean modifyUserPassword(UserEntity userEntity,String oldPassword) throws Exception
	{
		Header header = MyHttpClient.getAuthorizationHeader(this.getUserToken(userEntity));
		Map map = new HashMap();
		map.put("password", userEntity.getPassword());
		map.put("oldPassword", oldPassword);
		StringEntity stringEntity = new StringEntity(JSONUtil.ObjectToJSON(map));
		stringEntity.setContentType("application/json");
		String result = MyHttpClient.putMethod(CFClientResource.getModifyUserPasswordUrl(userEntity.getUaaGuid()), stringEntity, header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		if(hashMap.get("status")==null)
		{
			logger.info(result);
			throw new Exception(ExceptionContants.CHANGE_USER_PASSWORD_ERROR);
		}else if(hashMap.get("status").equals("ok"))
		{
			return true;
		}else
		{
			return false;
		}
		
	}
	
	/**
	 * 授权用户到空间开发者
	 * @param user
	 * @param spaceEntity
	 * @throws Exception
	 */
	public void authorizeToSpaceDeveloper(UserEntity user,SpaceEntity spaceEntity, CFClientToken clientToken) throws Exception
	{
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		StringEntity stringEntity = new StringEntity("");
		stringEntity.setContentType("application/x-www-form-urlencoded");
		String result=MyHttpClient.putMethod(CFClientResource.getSpaceDeveloperUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		Map meta=(Map) hashMap.get("metadata");
		if(meta!=null){
			spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
		}  
		else
		{
			logger.info(result);
			throw new Exception(result);
		}
	}
	/**
	 * 授权到空间管理者
	 * @param user
	 * @param spaceEntity
	 * @throws Exception 
	 */
	public void authorizeToSpaceManager(UserEntity user,SpaceEntity spaceEntity) throws Exception
	{
		Header header = MyHttpClient.getAuthorizationHeader(admintoken);
		StringEntity stringEntity = new StringEntity("");
		stringEntity.setContentType("application/x-www-form-urlencoded");
		String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		Map meta=(Map) hashMap.get("metadata");
		if(meta!=null){
			spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
		}  
		else
		{
			logger.error(result);
			throw new Exception(result);
		}
	}
	
	
	
	/**
	 * 授权到空间管理者
	 * @param user
	 * @param spaceEntity
	 * @throws Exception 
	 */
	public void authorizeToSpaceManager(UserEntity user,SpaceEntity spaceEntity,CFClientToken clientToken) throws Exception
	{
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		StringEntity stringEntity = new StringEntity("");
		stringEntity.setContentType("application/x-www-form-urlencoded");
		String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		Map meta=(Map) hashMap.get("metadata");
		if(meta!=null){
			spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
		}  
		else
		{
			logger.info(result);
			throw new Exception(result);
		}
	}
	
	/**
	 * 授权用户到组织
	 * @param guid
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	private void authorizeToOrgUser(UserEntity user) throws Exception
	{
		Header header = MyHttpClient.getAuthorizationHeader(admintoken);
		StringEntity  stringEntity = new StringEntity("");
		stringEntity.setContentType("application/json");
		String result=MyHttpClient.putMethod(CFClientResource.getOrgUserUrl(user.getUaaGuid(), user.getCfOrgGuid()), stringEntity, header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		Map meta=(Map) hashMap.get("metadata");
		if(meta==null){
			logger.info(result);
		  throw new Exception(result);
		}else if(meta.get("guid").toString().equals(user.getCfOrgGuid())){
			user.setCfOrgGuid(meta.get("guid").toString());}
		else{
			logger.info(result);
			throw new Exception(result);
			}
	}
	
	/**
	 * 授权用户到组织管理者
	 * @param guid
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	private void authorizeToOrgManager(UserEntity user) throws Exception
	{
		Header header = MyHttpClient.getAuthorizationHeader(admintoken);
		StringEntity  stringEntity = new StringEntity("");
		stringEntity.setContentType("application/json");
		String result=MyHttpClient.putMethod(CFClientResource.getOrgManagerUrl(user.getUaaGuid(), user.getCfOrgGuid()), stringEntity, header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		Map meta=(Map) hashMap.get("metadata");
		if(meta==null){
			logger.info(result);
		  throw new Exception(result);
		}else if(meta.get("guid").toString().equals(user.getCfOrgGuid())){
			user.setCfOrgGuid(meta.get("guid").toString());}
		else{
			logger.info(result);
			throw new Exception(result);
			}
	}
	/**
	 * 创建UAA用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	private void createUser(UserEntity user) throws Exception
	{
		
		Header header = MyHttpClient.getAuthorizationHeader(admintoken);
		StringEntity  stringEntity = new StringEntity(this.createParamsForUser(user));
		stringEntity.setContentType("application/json");
		String result=MyHttpClient.postMethod(CFClientResource.PAAS_PLATFORM_CREATE_USER_URL, stringEntity, header);
		Map<String, Object> map=(Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		Object id =map.get("id");
		if(id==null)
		{
			logger.info(result);
			throw new Exception(result);
		}else
		{
			user.setUaaGuid(id.toString());
		}
	}
	/**
	 * 生成用户json数据
	 * @param user
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private String createParamsForUser(UserEntity user) throws Exception
	{
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> emailMap = new HashMap<String, Object>();
		List emails = new ArrayList();
		Map<String,Object> names= new HashMap<String, Object>();
		map.put("userName", user.getUserName());
		emailMap.put("value", user.getUserEmail());
		emails.add(emailMap);
		map.put("emails", emails);
		map.put("password", user.getPassword());
		names.put("givenName", user.getUserName());
		names.put("familyName", user.getUserName());
		map.put("name", names);
		return JSONUtil.ObjectToJSON(map);
	}
	/**
	 * 将用户在CF平台中创建
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	private String createUserByGuid(String guid) throws Exception{
		Header header = MyHttpClient.getAuthorizationHeader(admintoken);
		Map map = new HashMap();
		map.put("guid", guid);
		StringEntity  stringEntity = new StringEntity(JSONUtil.ObjectToJSON(map));
		stringEntity.setContentType("application/json");
		String result=MyHttpClient.postMethod(CFClientResource.PAAS_PLATFORM_CREATE_USER_GUID_URL, stringEntity, header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		Map meta=(Map) hashMap.get("metadata");
		if(meta==null){
			logger.info(result);
		  throw new Exception(result);
		}else if(meta.get("guid").toString().equals(guid)){
			return guid;
		}else{
			logger.info(result);
			throw new Exception(result);
		}			
	}
	
	
	private boolean deleteSpaceDeveloper(UserEntity user,SpaceEntity entity,CFClientToken clientToken) throws Exception
	{
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		String result=MyHttpClient.deleteMethod(CFClientResource.getSpaceDeleteUrl(entity.getCfSpaceGuid(), user.getUaaGuid()), header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		Map meta=(Map) hashMap.get("metadata");
		if(meta==null){
			logger.info(result);
		  throw new Exception(result);
		}else if(meta.get("guid").toString().equals(entity.getCfSpaceGuid())){
			return true;}
		else{
			logger.info(result);
			throw new Exception(result);
		}
	}
	
	/**
	 * 获取用户的Token
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	private CFClientToken getUserToken(UserEntity user) throws Exception
	{
		CFClientToken clientToken = null;
		try {
			clientToken = new CFClientToken(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return clientToken;
	}
	
	private void createSpace(SpaceEntity spaceEntity) throws Exception
	{
		Header header = MyHttpClient.getAuthorizationHeader(admintoken);
		Map map = new HashMap();
		map.put("name", spaceEntity.getSpaceName());
		map.put("organization_guid", spaceEntity.getCfOrgGuid());
		StringEntity entity = new StringEntity(JSONUtil.ObjectToJSON(map));
		String result=MyHttpClient.postMethod(CFClientResource.getCreateSpaceUrl(), entity, header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		Map meta=(Map) hashMap.get("metadata");
		if(meta!=null){
			spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
		}  
		else
		{
			logger.info(result);
			throw new Exception(result);
		}
	}
	
//	@Override
	public boolean updateUser(UserEntity userEntity,CFClientToken clientToken) throws Exception {
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		Map map  = new HashMap();
		List list = new ArrayList();
		list.add(userEntity.getUserEmail());
		map.put("emals", list);
		StringEntity stringEntity = new StringEntity(JSONUtil.ObjectToJSON(map));
		stringEntity.setContentType("application/json");
		String  result = MyHttpClient.putMethod(CFClientResource.getUpdataUserUrl(userEntity.getUaaGuid()), stringEntity, header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		if(hashMap.get("id")!=null)
		{
			return true;
		}else
		{
			throw new Exception(result);
		}
	}
	
	@Override
	public List<QuotaBean> getQuotaList(CFClientToken clientToken) throws Exception {
		Header header = MyHttpClient.getAuthorizationHeader(admintoken);
		String result = MyHttpClient.getMethod(CFClientResource.PAAS_PLATFORM_QUOTA_LIST_URL,header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		List list = (List) hashMap.get("resources");
		String s=JSONUtil.ObjectToJSON(list);
		ObjectMapper mapper = new ObjectMapper();
		org.codehaus.jackson.type.JavaType javaType=mapper.getTypeFactory().constructParametricType(ArrayList.class, CFBean.class);
		List<CFBean> entities=mapper.readValue(s, javaType);
		List<QuotaBean> qlist = new ArrayList<QuotaBean>();
		for(CFBean cfe:entities)
		{
			if(cfe.getEntity().getName().equals("free"))
			{
				qlist.add(cfe.getEntity());
			}
			if(cfe.getEntity().getName().equals("business"))
			{
				qlist.add(cfe.getEntity());
			}
			if(cfe.getEntity().getName().equals("enterprise"))
			{
				qlist.add(cfe.getEntity());
			}
			if(cfe.getEntity().getName().equals("basic"))
			{
				qlist.add(cfe.getEntity());
			}
		}
		
		return qlist;
	}

	public void getUser(UserEntity user)throws Exception{
		String username = user.getUserName();
		Header header = MyHttpClient.getAuthorizationHeader(admintoken);
		URL url = new URL(CFClientResource.PAAS_PLATFORM_UAA+"/Users?attributes=id,userName,emails,name&filter=userName eq '"+username+"'");
		URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
		String result = MyHttpClient.getMethod(uri.toString(),header);
		Map<String,Object> hashMap = (Map<String, Object>) JSONUtil.JSONToObject(result, Map.class);
		List list = (List) hashMap.get("resources");
		Map map = (Map)list.get(0);
		String userName = (String)map.get("userName");
		String id = (String)map.get("id");
		List list2 = (List)map.get("emails");
		String email = (String)((Map)list2.get(0)).get("value");
		user.setUserEmail(email);
		user.setUaaGuid(id);
	}
	
	/**
	 * 测试接口，创建独立开发者(采用李云的步骤)
	 */
	private void testCreateUserByAssociate(String email)throws Exception{
		Header header = MyHttpClient.getAuthorizationHeader(this.admintoken);
		Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").excludeFieldsWithoutExposeAnnotation().create();
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//create organization
		String orgName = "org_";
		String orgGuid = "";
		Map map1 = new HashMap();
		orgName = getCFName(email, orgName);
		map1.put("name", orgName);
		String parameters = gson.toJson(map1);
		System.out.println("create organization parameters:"+parameters);
		StringEntity stringEntity =new StringEntity(parameters);
		String url = "http://api.guoyu.csipaas.com/v2/organizations";
		String result = MyHttpClient.postMethod(url, stringEntity, header);
		System.out.println("create organization result:"+result);
		HashMap<String,Object> hashMap = (HashMap<String, Object>) JSONUtil.JSONToObject(result, HashMap.class);
		HashMap meta=(HashMap) hashMap.get("metadata");
		if(meta!=null){
			orgGuid = meta.get("guid").toString();
		}  
		else
		{
			logger.info(result);
			throw new Exception(result);
		}
		System.out.println("orgName:"+orgName+"  orgGuid:"+orgGuid);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//create user
		String uaaGuid = "";
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> emailMap = new HashMap<String, Object>();
		List emails = new ArrayList();
		Map<String,Object> names= new HashMap<String, Object>();
		map.put("userName", email);
		emailMap.put("value", email);
		emails.add(emailMap);
		map.put("emails", emails);
		map.put("password", "111111");
		names.put("givenName", email);
		names.put("familyName", email);
		map.put("name", names);
		String parameters1 = gson.toJson(map);
		System.out.println("create uaa-user parameters:"+parameters1);
		StringEntity  stringEntity1 = new StringEntity(parameters1);
		stringEntity1.setContentType("application/json");
		String url1 = "http://uaa.guoyu.csipaas.com/Users";
		String result1=MyHttpClient.postMethod(url1, stringEntity1, header);
		System.out.println("create uaa-user result:"+result1);
		HashMap<String, Object> mapl=(HashMap<String, Object>) JSONUtil.JSONToObject(result1, HashMap.class);
		Object id =mapl.get("id");
		if(id==null)
		{
			logger.info(result1);
			throw new Exception(result1);
		}else
		{
			uaaGuid = id.toString();
		}
		System.out.println("uaaName:"+email+"  uaaGuid:"+uaaGuid);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//create cf-user	
		Map map2 = new HashMap();
		map2.put("guid", uaaGuid);
		String parameters2 = gson.toJson(map2);
		System.out.println("create cf-user parameters:"+parameters2);
		StringEntity  stringEntity2 = new StringEntity(parameters2);
		stringEntity2.setContentType("application/json");
		String url2 = "http://api.guoyu.csipaas.com/v2/users";
		String result2=MyHttpClient.postMethod(url2, stringEntity2, header);
		System.out.println("create cf-user result:"+result2);
		HashMap<String,Object> hashMap2 = (HashMap<String, Object>) JSONUtil.JSONToObject(result2, HashMap.class);
		HashMap meta2=(HashMap) hashMap2.get("metadata");
		if(meta2==null){
			logger.info(result2);
		  throw new Exception(result2);
		}else if(meta2.get("guid").toString().equals(uaaGuid)){
			System.out.println("uaaName:"+email+"  cfGuid:"+uaaGuid);
		}else{
			logger.info(result2);
			throw new Exception(result2);
		}
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//associate organization-user/manager to user	
		StringEntity stringEntity3 = new StringEntity("");
		stringEntity3.setContentType("application/x-www-form-urlencoded");
		String url3 = "http://api.guoyu.csipaas.com/v2/organizations/"+orgGuid+"/users/"+uaaGuid;
		String result3=MyHttpClient.putMethod(url3, stringEntity3, header);
		System.out.println("associate org-users to user:"+result3);
		HashMap<String,Object> hashMap3 = (HashMap<String, Object>) JSONUtil.JSONToObject(result3, HashMap.class);
		HashMap meta3=(HashMap) hashMap3.get("metadata");
		if(meta3!=null){
			System.out.println("success:associate org-users to user");			
		}
		
		StringEntity  stringEntity4 = new StringEntity("");
		stringEntity4.setContentType("application/json");
		String url4 = "http://api.guoyu.csipaas.com/v2/organizations/"+orgGuid+"/managers/"+uaaGuid;
		String result4=MyHttpClient.putMethod(url4, stringEntity4, header);		
		System.out.println("associate org-managers to user:"+result4);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    //create space	
		String spaceName = "space_";
		String spaceGuid = "";
		
		UserEntity user = new UserEntity();
		user.setUserName(email);
		user.setPassword("111111");
		CFClientToken clientToken = this.getUserToken(user);
		Header header5 = MyHttpClient.getAuthorizationHeader(clientToken);
		Map map5 = new HashMap();
		map5.put("name", getCFName(email, spaceName));
		map5.put("organization_guid", orgGuid);
		String parameters5 = gson.toJson(map5);
		System.out.println("create space parameters:"+parameters5);
		StringEntity entity5 = new StringEntity(parameters5);
		String url5 = "http://api.guoyu.csipaas.com/v2/spaces";
		String result5=MyHttpClient.postMethod(url5, entity5, header);
		System.out.println("create space result:"+result5);
		HashMap<String,Object> hashMap5 = (HashMap<String, Object>) JSONUtil.JSONToObject(result5, HashMap.class);
		HashMap meta5=(HashMap) hashMap5.get("metadata");
		if(meta5!=null){
			spaceGuid = meta5.get("guid").toString();
		}  
		else
		{
			logger.info(result5);
			throw new Exception(result5);
		}		
		System.out.println("spaceName:"+spaceName+" spaceGuid:"+spaceGuid);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//associate space-manager/developer to user
		StringEntity stringEntity6 = new StringEntity("");
		stringEntity6.setContentType("application/x-www-form-urlencoded");
		String url6 = "http://api.guoyu.csipaas.com/v2/spaces/"+spaceGuid+"/managers/"+uaaGuid;
		String result6=MyHttpClient.putMethod(url6, stringEntity6, header5);
		System.out.println("associate space-manager to user result:"+result6);
		
		StringEntity stringEntity7 = new StringEntity("");
		stringEntity7.setContentType("application/x-www-form-urlencoded");
		String url7 = "http://api.guoyu.csipaas.com/v2/spaces/"+spaceGuid+"/developers/"+uaaGuid;
		String result7=MyHttpClient.putMethod(url7, stringEntity7, header5);
		System.out.println("associate space-developer to user result:"+result7);
		
	}
	
	
	/**
	 * 测试接口，创建独立开发者(采用原版本的注册流程)
	 */
	private void testCreateUser(String email) throws Exception
	{
		Header header = MyHttpClient.getAuthorizationHeader(this.admintoken);
		Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").excludeFieldsWithoutExposeAnnotation().create();
		
		//创建组织机构
		String orgName = "org_";
		
		CreateOrgRequestFormat format = new CreateOrgRequestFormat();
		format.setName(getCFName(email, orgName));
		format.setBilling_enabled(true);
		
		String requeststr = gson.toJson(format);
		System.out.println("organization request parameters:"+requeststr);
		StringEntity se = new StringEntity(requeststr, "UTF-8");
		se.setContentType("application/json; charset=UTF-8");
		String url = "http://api.guoyu.csipaas.com/v2/organizations";
		String result = MyHttpClient.postMethod(url, se, header);
		
		System.out.println("create organizations:"+result);		
		String orgGuid = "";		
		HashMap<String,Object> hashMap = (HashMap<String, Object>) JSONUtil.JSONToObject(result, HashMap.class);
		HashMap meta=(HashMap) hashMap.get("metadata");
		HashMap entity = (HashMap) hashMap.get("entity");
		if(meta!=null){
			orgGuid = meta.get("guid").toString();
			orgName = entity.get("name").toString();
			System.out.println("orgName:"+orgName+"orgGuid:"+orgGuid);
		}  
		else
		{
			logger.info(result);
			throw new Exception(result);
		}
		////////////////////////////////////////////////////////////////////////////////////////////////
		//创建工作空间
		//String orgGuid = "9514af79-7328-43c3-bb9e-2a81210f31ec";
		
		CreateSpaceRequestFormat format1 = new CreateSpaceRequestFormat();
		String spaceName = "space_";
		format1.setName(getCFName(email, spaceName));
		format1.setOrganization_guid(orgGuid);
		
		String requeststr1 = gson.toJson(format1);
		System.out.println("space request parameters:"+requeststr1);
		StringEntity se1 = new StringEntity(requeststr1, "UTF-8");
		se1.setContentType("application/json; charset=UTF-8");
		String url1 = "http://api.guoyu.csipaas.com/v2/spaces";
		String result1 = MyHttpClient.postMethod(url1, se1, header);
		
		System.out.println("create spaces:"+result1);		
		String spaceGuid = "";		
		HashMap<String,Object> hashMap1 = (HashMap<String, Object>) JSONUtil.JSONToObject(result1, HashMap.class);
		HashMap meta1=(HashMap) hashMap1.get("metadata");
		HashMap entity1 = (HashMap) hashMap1.get("entity");
		if(meta1!=null){
			spaceGuid = meta1.get("guid").toString();
			spaceName = entity1.get("name").toString();
			System.out.println("spaceName:"+spaceName+"spaceGuid:"+spaceGuid);
		}  
		else
		{
			logger.info(result1);
			throw new Exception(result1);
		}
        ////////////////////////////////////////////////////////////////////////////////////////////////
		//创建用户
		//String spaceGuid = "30c82373-01c3-4ffd-b915-737c706f0083";
		
		UserEntity user = new UserEntity();
		user.setUserName(email);
		user.setUserEmail(email);
		user.setPassword("111111");
		
		
		//Header header = MyHttpClient.getAuthorizationHeader(admintoken);
		StringEntity  stringEntityUser = new StringEntity(this.createParamsForUser(user));
		stringEntityUser.setContentType("application/json");
		String urlUser = "http://uaa.guoyu.csipaas.com/Users";
		String resultUser=MyHttpClient.postMethod(urlUser, stringEntityUser, header);
		HashMap<String, Object> mapUser=(HashMap<String, Object>) JSONUtil.JSONToObject(resultUser, HashMap.class);
		Object id =mapUser.get("id");
		if(id==null)
		{
			logger.info(resultUser);
			throw new Exception(resultUser);
		}else
		{
			user.setUaaGuid(id.toString());
		}
		
		//this.createUser(user);
		CreateUserRequestFormat format2 = new CreateUserRequestFormat();
		List<String> spaceGuids = new ArrayList<String>();
		List<String> organizationGuids = new ArrayList<String>();
		spaceGuids.add(spaceGuid);
		organizationGuids.add(orgGuid);
		format2.setGuid(user.getUaaGuid());
		format2.setAdmin(false);
		format2.setDefaultspaceguid(spaceGuid);
		format2.setSpace_guids(spaceGuids);
		format2.setOrganization_guids(organizationGuids);
		
		
		String requeststr2 = gson.toJson(format2);
		System.out.println("user request parameters:"+requeststr2);
		StringEntity se2 = new StringEntity(requeststr2, "UTF-8");
		se2.setContentType("application/json; charset=UTF-8");
		String url2 = "http://api.guoyu.csipaas.com/v2/users";
		String result2 = MyHttpClient.postMethod(url2, se2, header);
		
		System.out.println("create users:"+result2);		
		String uaaGuid = "";		
		HashMap<String,Object> hashMap2 = (HashMap<String, Object>) JSONUtil.JSONToObject(result2, HashMap.class);
		HashMap meta2=(HashMap) hashMap2.get("metadata");
		if(meta2!=null){
			uaaGuid = meta2.get("guid").toString();
			System.out.println("userName:"+user.getUserName()+"uaaGuid:"+uaaGuid);
		}  
		else
		{
			logger.info(result2);
			throw new Exception(result2);
		}
        ////////////////////////////////////////////////////////////////////////////////////////////////
		//更新空间信息
		//String uaaGuid = "0eea94c3-6e60-471d-a072-b08188876c6b";
		
		UpdateSpaceRequestFormat format3 = new UpdateSpaceRequestFormat();
		List<String> list = new ArrayList<String>();
		list.add(uaaGuid);
		format3.setDeveloper_guids(list);
		format3.setManager_guids(list);
		format3.setAuditor_guids(list);
		
		String requeststr3 = gson.toJson(format3);
		System.out.println("update space request parameters:"+requeststr3);
		StringEntity se3 = new StringEntity(requeststr3, "UTF-8");
		se3.setContentType("application/json; charset=UTF-8");
		String url3 = "http://api.guoyu.csipaas.com/v2/spaces/"+spaceGuid+"?collection-method=replace";
		String result3 = MyHttpClient.putMethod(url3, se3, header);
		
		System.out.println("create users:"+result3);	
		HashMap<String,Object> hashMap3 = (HashMap<String, Object>) JSONUtil.JSONToObject(result3, HashMap.class);
		HashMap meta3=(HashMap) hashMap3.get("metadata");
		if(meta3!=null){
			String mySpaceGuid = meta3.get("guid").toString();
			System.out.println("mySpaceGuid:"+mySpaceGuid);
		}  
		else
		{
			logger.info(result3);
			throw new Exception(result3);
		}
	}
	
	/**
	 * 测试接口，授予人员组织机构权限
	 */
	private void testListAuthorizeToOrganizationUsers() throws Exception
	{
//		UserEntity user = new UserEntity();
//		user.setUserName("night_2000@126.com");
//		user.setPassword("111111");
//		CFClientToken clientToken = this.getUserToken(user);
		Header header = MyHttpClient.getAuthorizationHeader(this.admintoken);
		StringEntity stringEntity = new StringEntity("");
		stringEntity.setContentType("application/x-www-form-urlencoded");
		//String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		String url = "http://api.guoyu.csipaas.com/v2/organizations/f5cc3f0f-bc7f-40bd-bbb0-56d6ed6cd948/users/f5cc3f0f-bc7f-40bd-bbb0-56d6ed6cd948";
		String result=MyHttpClient.putMethod(url, stringEntity, header);
		HashMap<String,Object> hashMap = (HashMap<String, Object>) JSONUtil.JSONToObject(result, HashMap.class);
		HashMap meta=(HashMap) hashMap.get("metadata");
		if(meta!=null){
			//spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
			
		}  
		else
		{
			//logger.info(result);
			//throw new Exception(result);
		}
		System.out.println("result:"+result);
		
		StringEntity  stringEntity1 = new StringEntity("");
		stringEntity1.setContentType("application/json");
		String url1 = "http://api.guoyu.csipaas.com/v2/organizations/f5cc3f0f-bc7f-40bd-bbb0-56d6ed6cd948/managers/f5cc3f0f-bc7f-40bd-bbb0-56d6ed6cd948";
		String result1=MyHttpClient.putMethod(url1, stringEntity1, header);
		
		System.out.println("result1:"+result1);
	}
	
	/**
	 * 测试接口，列出所有组织机构
	 */
	private void testListAllorg() throws Exception
	{
		Header header = MyHttpClient.getAuthorizationHeader(this.admintoken);
		StringEntity stringEntity = new StringEntity("");
		stringEntity.setContentType("application/json;charset=utf-8");
		//String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		String url = "http://api.guoyu.csipaas.com/v2/organizations";
		String result=MyHttpClient.getMethod(url, header);//.putMethod(url, stringEntity, header);
		HashMap<String,Object> hashMap = (HashMap<String, Object>) JSONUtil.JSONToObject(result, HashMap.class);
		HashMap meta=(HashMap) hashMap.get("metadata");
		if(meta!=null){
			//spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
			System.out.println(result);
		}  
		else
		{
			//logger.info(result);
			//throw new Exception(result);
		}
	}
	
	/**
	 * 测试接口，列出所有空间管理员
	 */
	private void testListAllspaceManagers(String userName,String password,String spaceGuid) throws Exception
	{
		UserEntity user = new UserEntity();
		user.setUserName(userName);
		user.setPassword(password);
		CFClientToken clientToken = this.getUserToken(user);
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		StringEntity stringEntity = new StringEntity("");
		stringEntity.setContentType("application/json;charset=utf-8");
		//String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		String url = "http://api.guoyu.csipaas.com/v2/spaces/"+spaceGuid+"/managers";
		String result=MyHttpClient.getMethod(url, header);//.putMethod(url, stringEntity, header);
		HashMap<String,Object> hashMap = (HashMap<String, Object>) JSONUtil.JSONToObject(result, HashMap.class);
		HashMap meta=(HashMap) hashMap.get("metadata");
		if(meta!=null){
			//spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
			//System.out.println(result);
		}  
		else
		{
			//logger.info(result);
			//throw new Exception(result);
		}
		System.out.println(result);
	}
	
	/**
	 * 测试接口，列出所有空间开发人员
	 */
	private void testListAllspaceDevelopers(String userName,String password,String spaceGuid) throws Exception
	{
		UserEntity user = new UserEntity();
		user.setUserName(userName);
		user.setPassword(password);
		CFClientToken clientToken = this.getUserToken(user);
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		StringEntity stringEntity = new StringEntity("");
		stringEntity.setContentType("application/json;charset=utf-8");
		//String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		String url = "http://api.guoyu.csipaas.com/v2/spaces/"+spaceGuid+"/developers";
		String result=MyHttpClient.getMethod(url, header);//.putMethod(url, stringEntity, header);
		HashMap<String,Object> hashMap = (HashMap<String, Object>) JSONUtil.JSONToObject(result, HashMap.class);
		HashMap meta=(HashMap) hashMap.get("metadata");
		if(meta!=null){
			//spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
			//System.out.println(result);
		}  
		else
		{
			//logger.info(result);
			//throw new Exception(result);
		}
		System.out.println(result);
	}
	
	/**
	 * 测试接口，列举所有的组织机构管理员
	 */
	private void testListAllorgManagers(String orgGuid) throws Exception
	{
		UserEntity user = new UserEntity();
		user.setUserName("night_2000@126.com");
		user.setPassword("111111");
		CFClientToken clientToken = this.getUserToken(user);
		Header header = MyHttpClient.getAuthorizationHeader(this.admintoken);
		StringEntity stringEntity = new StringEntity("");
		stringEntity.setContentType("application/json;charset=utf-8");
		//String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		String url = "http://api.guoyu.csipaas.com/v2/organizations/"+orgGuid;
		String result=MyHttpClient.getMethod(url, header);//.putMethod(url, stringEntity, header);
		HashMap<String,Object> hashMap = (HashMap<String, Object>) JSONUtil.JSONToObject(result, HashMap.class);
		HashMap meta=(HashMap) hashMap.get("metadata");
		if(meta!=null){
			//spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
			//System.out.println(result);
		}  
		else
		{
			//logger.info(result);
			//throw new Exception(result);
		}
		System.out.println(result);
	}
	
	/**
	 * 测试接口，授权空间开发人员
	 */
	private void testAuthorizetoSpaceDeveloper() throws Exception
	{
		UserEntity user = new UserEntity();
		user.setUserName("night_2000@126.com");
		user.setPassword("111111");
		CFClientToken clientToken = this.getUserToken(user);
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		StringEntity stringEntity = new StringEntity("");
		stringEntity.setContentType("application/x-www-form-urlencoded");
		//String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		String url = "http://api.guoyu.csipaas.com/v2/spaces/8779e16f-9c01-451f-94bc-2b19beeaa16a/developers/f5cc3f0f-bc7f-40bd-bbb0-56d6ed6cd948";
		String result=MyHttpClient.putMethod(url, stringEntity, header);
		HashMap<String,Object> hashMap = (HashMap<String, Object>) JSONUtil.JSONToObject(result, HashMap.class);
		HashMap meta=(HashMap) hashMap.get("metadata");
		if(meta!=null){
			//spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
			System.out.println(result);
		}  
		else
		{
			//logger.info(result);
			//throw new Exception(result);
		}
	}
	
	/**
	 * 测试接口，授权空间管理员
	 */
	private void testAuthorizetoSpaceManager() throws Exception
	{
		UserEntity user = new UserEntity();
		user.setUserName("night_2000@126.com");
		user.setPassword("111111");
		CFClientToken clientToken = this.getUserToken(user);
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		Header header2 = MyHttpClient.getAuthorizationHeader(this.admintoken);
		
		
		StringEntity stringEntity1 = new StringEntity("");
		stringEntity1.setContentType("application/x-www-form-urlencoded");
		//String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		String url1 = "http://api.guoyu.csipaas.com/v2/organizations/f5cc3f0f-bc7f-40bd-bbb0-56d6ed6cd948/users/f5cc3f0f-bc7f-40bd-bbb0-56d6ed6cd948";
		String result1=MyHttpClient.putMethod(url1, stringEntity1, header2);
		
		System.out.println("result1:"+result1);
		
		StringEntity stringEntity2 = new StringEntity("");
		stringEntity2.setContentType("application/json;charset=utf-8");
		//String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		String url2 = "http://api.guoyu.csipaas.com/v2/users/f5cc3f0f-bc7f-40bd-bbb0-56d6ed6cd948/spaces/8779e16f-9c01-451f-94bc-2b19beeaa16a";
		String result2=MyHttpClient.putMethod(url2, stringEntity2, header2);
		
		System.out.println("result2:"+result2);
		
		
		
		StringEntity stringEntity = new StringEntity("");
		stringEntity.setContentType("application/x-www-form-urlencoded");
		//String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		String url = "http://api.guoyu.csipaas.com/v2/spaces/8779e16f-9c01-451f-94bc-2b19beeaa16a/managers/f5cc3f0f-bc7f-40bd-bbb0-56d6ed6cd948";
		String result=MyHttpClient.putMethod(url, stringEntity, header);
		System.out.println("result:"+result);
//		HashMap<String,Object> hashMap = (HashMap<String, Object>) JSONUtil.JSONToObject(result, HashMap.class);
//		HashMap meta=(HashMap) hashMap.get("metadata");
//		if(meta!=null){
//			//spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
//			System.out.println(result);
//		}  
//		else
//		{
//			//logger.info(result);
//			//throw new Exception(result);
//		}
	}
	
	
	/**
	 * 测试接口，获取所有空间
	 */
	private void testListAllSpace() throws Exception
	{
		//CFClientToken clientToken = this.getUserToken(user);
		Header header = MyHttpClient.getAuthorizationHeader(admintoken);
		StringEntity stringEntity = new StringEntity("");
		stringEntity.setContentType("application/json;charset=utf-8");
		//String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		String url = "http://api.guoyu.csipaas.com/v2/spaces";
		String result=MyHttpClient.getMethod(url, header);//.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
		HashMap<String,Object> hashMap = (HashMap<String, Object>) JSONUtil.JSONToObject(result, HashMap.class);
		HashMap meta=(HashMap) hashMap.get("metadata");
		if(meta!=null){
			//spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
			System.out.println(result);
		}  
		else
		{
			logger.info(result);
			throw new Exception(result);
		}
	}
	
	public static void main(String[] args) throws Exception {
		//CFClientToken clientToken = this.getUserToken(user);
//		CFClientImpl cf = new CFClientImpl();
//		List<QuotaBean> list = cf.getQuotaList(cf.admintoken);
//		for(QuotaBean b:list){
//			System.out.println(b.toString());
//		}
		//cf.testCreateUserByAssociate("rcloudtest4@126.com");
		//cf.testCreateUser("rcloudtest2@126.com");
		//cf.testListAuthorizeToOrganizationUsers();
		//cf.testListAllorg();
		//cf.testListAllSpace();
		//cf.testAuthorizetoSpaceManager();
		//cf.testAuthorizetoSpaceDeveloper();
		//cf.testListAllspaceManagers("rcloudtest4@126.com","111111","5f2f9c45-f6d9-4cee-9909-5e5542a6ac90");
		//cf.testListAllspaceDevelopers("rcloudtest4@126.com","111111","5f2f9c45-f6d9-4cee-9909-5e5542a6ac90");
		//cf.testListAllorgManagers("9514af79-7328-43c3-bb9e-2a81210f31ec");
//		Header header = MyHttpClient.getAuthorizationHeader(cf.admintokenadmintoken);
//		StringEntity stringEntity = new StringEntity("");
//		stringEntity.setContentType("application/x-www-form-urlencoded");
//		String result=MyHttpClient.putMethod(CFClientResource.getSpaceManagerUrl(spaceEntity.getCfSpaceGuid(), user.getUaaGuid()), stringEntity, header);
//		HashMap<String,Object> hashMap = (HashMap<String, Object>) JSONUtil.JSONToObject(result, HashMap.class);
//		HashMap meta=(HashMap) hashMap.get("metadata");
//		if(meta!=null){
//			spaceEntity.setCfSpaceGuid(meta.get("guid").toString());
//		}  
//		else
//		{
//			logger.info(result);
//			throw new Exception(result);
//		}
//		String username = "rcloud_test@126.com";
//		CFClientImpl cfClientImpl = new CFClientImpl();
//		Header header = MyHttpClient.getAuthorizationHeader(cfClientImpl.admintoken);
//		URL url = new URL("http://uaa.csipaas.com/Users?attributes=id,userName,password,emails,name&filter=userName eq '"+username+"'");
//		URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
//		//HttpClient client    = new DefaultHttpClient();
//		String result = MyHttpClient.getMethod(uri.toString(),header);
//		HashMap<String,Object> hashMap = (HashMap<String, Object>) JSONUtil.JSONToObject(result, HashMap.class);
//		List list = (List) hashMap.get("resources");
//		Map map = (Map)list.get(0);
//		String userName = (String)map.get("userName");
//		List list2 = (List)map.get("emails");
//		String email = (String)((Map)list2.get(0)).get("value");
//		System.out.println("userName:"+userName+"email:"+email);
//		UserEntity user = new UserEntity();
//		user.setUserName("kangyang822@163.com");
//		user.setUserEmail("kangyang822@163.com");
//		user.setPassword("abc");
//		user.setPassword("abc");
////		59872274-1bd7-462f-8991-e46150878c32
//		cfClientImpl.createUser(user);
//		CFClientToken clientToken =cfClientImpl.getUserToken(user);
//		OrganizationEntity organizationEntity = new OrganizationEntity();
//		organizationEntity.setOrganizationName("aBCDs1212125");
//		cfClientImpl.createOrg(organizationEntity);
//		cfClientImpl.getQuotaList(cfClientImpl.admintoken);
		
//		System.out.println(clientToken.getAcessToken().getAccess_token());
		
	}


	
	

}
