package com.chinasofti.rcloud.common.cloudfoundry.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.CommonConstants;
import com.chinasofti.rcloud.common.client.CFClientResource;
import com.chinasofti.rcloud.common.client.JSONUtil;
import com.chinasofti.rcloud.common.client.MyHttpClient;
import com.chinasofti.rcloud.common.cloudfoundry.UploadToCFIterface;
import com.chinasofti.rcloud.common.cloudfoundry.domain.CFClientToken;
import com.chinasofti.rcloud.dao.SpaceEntityMapper;
import com.chinasofti.rcloud.dao.SpaceToUserEntityMapper;
import com.chinasofti.rcloud.domain.SpaceEntity;
import com.chinasofti.rcloud.domain.SpaceEntityExample;
import com.chinasofti.rcloud.domain.SpaceToUserEntityExample;
import com.chinasofti.rcloud.domain.SpaceToUserEntityKey;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.portal.cloudshop.vo.RaeAppVo;

@Service
public class UploadToCFImpl implements UploadToCFIterface {

	private Logger logger = Logger.getLogger(UploadToCFImpl.class);

	private static ResourceBundle resource = ResourceBundle
			.getBundle("application");
	public static String STORE_PATH = resource.getString("upload.file.path");

	@Autowired
	private SpaceToUserEntityMapper spaceToUserEntityMapper;

	@Autowired
	private SpaceEntityMapper spaceEntityMapper;

	public List<SpaceEntity> getSpaceEntitys(UserEntity userEntity) {
		List<SpaceEntity> spaceEntitys = new ArrayList<SpaceEntity>();
		if (CommonConstants.DEVLOPER_ENTITY.equals(userEntity.getRoleCode())) {// 如果是独立开发者
			String spaceName = this.getCFName(userEntity.getUserEmail(),
					"space_");// 独立开发者的space_guid是自己拼接的
			SpaceEntity spaceEntity = new SpaceEntity();
			spaceEntity.setSpaceName(spaceName);
			spaceEntity.setSpaceChname(spaceName);
			spaceEntitys.add(spaceEntity);
		} else { // 机构开发者 和机构管理员 查询对应的工作空间
			SpaceToUserEntityExample example = new SpaceToUserEntityExample();
			example.createCriteria().andUserIdEqualTo(userEntity.getUserId());
			List<SpaceToUserEntityKey> list = spaceToUserEntityMapper
					.selectByExample(example);
			for (SpaceToUserEntityKey spaceToUserEntityKey : list) {
				SpaceEntity spaceEntity = spaceEntityMapper
						.selectByPrimaryKey(spaceToUserEntityKey.getSpaceId());
				spaceEntitys.add(spaceEntity);
			}
		}
		return spaceEntitys;
	}

	public void stopAppsByUser(UserEntity userEntity, CFClientToken clientToken)
			throws Exception {
		List<SpaceEntity> spaceEntitys = this.getSpaceEntitys(userEntity);// 获取用户对应的工作空间名space_name
		List<String> spaceGuids = new ArrayList<String>();// 存放用户对应的space_guids,根据space_guid过滤对应的应用
		for (SpaceEntity spaceEntity : spaceEntitys) {// 保存space_guid到数组
			String space_guid = this.getSpaceGuidBySpaceNameFromUAA(
					spaceEntity.getSpaceName(), clientToken);
			spaceGuids.add(space_guid);
		}
		if (spaceGuids.size() > 0) {
			Header header = MyHttpClient.getAuthorizationHeader(clientToken);
			
			for (String spaceGuid : spaceGuids) {
				StringBuffer urls = new StringBuffer(
						CFClientResource.getCreateAppsUrl(true));
				urls.append("?q=space_guid:").append(spaceGuid);
				logger.info("url:"+urls.toString());
				String result = MyHttpClient.getMethod(urls.toString(), header);
				Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
						.JSONToObject(result, Map.class);
				
				List resources = (List) hashMap.get("resources");
				if (resources != null && resources.size() > 0) {
					
					for (Object object : resources) {
						Map meta = (Map) ((Map) object).get("metadata");
						if (meta != null) {// 设置返回的应用guid
							String guid = meta.get("guid").toString();
							logger.info("get guid info:" + guid);
							this.stopApp(guid, clientToken);
						} else {
							throw new Exception(result);
						}
					}
					
//					Map meta = (Map) ((Map) resources.get(0)).get("metadata");
//					if (meta != null) {// 设置返回的应用guid
//						String guid = meta.get("guid").toString();
//						logger.info("get guid info:" + guid);
//						this.stopApp(guid, clientToken);
//					} else {
//						throw new Exception(result);
//					}
				}
			}
			
//			StringBuffer urls = new StringBuffer(
//					CFClientResource.getCreateAppsUrl(true));
//			
//			urls.append("?q=space_guid IN ");
//			for (String spaceGuid : spaceGuids) {
//				urls.append(spaceGuid).append(",");
//			}
//			String url = urls.substring(0, urls.lastIndexOf(","));
//			logger.info("url:"+url);
//			String result = MyHttpClient.getMethod(url, header);
//			Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
//					.JSONToObject(result, Map.class);
//
//			List resources = (List) hashMap.get("resources");
//			if (resources != null && resources.size() > 0) {
//				Map meta = (Map) ((Map) resources.get(0)).get("metadata");
//				if (meta != null) {// 设置返回的应用guid
//					String guid = meta.get("guid").toString();
//					logger.info("get guid info:" + guid);
//					this.stopApp(guid, clientToken);
//				} else {
//					throw new Exception(result);
//				}
//			}
		}
	}

	public String getSpaceGuidBySpaceNameFromUAA(String spaceName,
			CFClientToken clientToken) throws Exception {
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		String url = CFClientResource.getListSpaceUrl() + "?q=name:"
				+ spaceName;
		String result = MyHttpClient.getMethod(url, header);
		Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
				.JSONToObject(result, Map.class);
		List resources = (List) hashMap.get("resources");
		if (resources != null && resources.size() > 0) {
			Map meta = (Map) ((Map) resources.get(0)).get("metadata");
			if (meta != null) {// 设置返回的应用guid
				return meta.get("guid").toString();
			} else {
				throw new Exception(result);
			}
		} else {
			throw new Exception("{\"code\":13131414}"); // 工作空间不可用
		}

	}

	/**
	 * 
	 * @Title: getSpaceGuid
	 * @Description: 通过spaceName 获取 space_guid 如果数据库中存在则直接返回
	 *               如果数据库中没有，通过UAA中的spaceName获取space_guid 这个方法跟业务逻辑相关的方法
	 * @author sunlulu
	 * @param @param spaceName
	 * @param @param clientToken
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	private String getSpaceGuid(String spaceName, CFClientToken clientToken)
			throws Exception {
		SpaceEntityExample example = new SpaceEntityExample();
		example.createCriteria().andSpaceNameEqualTo(spaceName); // 查询表portal_space
		List<SpaceEntity> spaceEntitys = spaceEntityMapper
				.selectByExample(example);
		SpaceEntity spaceEntity = null;
		if (spaceEntitys.size() != 0) {
			spaceEntity = spaceEntitys.get(0);
		}
		if (spaceEntity != null && spaceEntity.getCfSpaceGuid() != null) { // 如果有space_guid,返回
			return spaceEntity.getCfSpaceGuid();
		} else { // 如果没有space_guid ,通过space_name 获取space_guid
			return this.getSpaceGuidBySpaceNameFromUAA(spaceName, clientToken);
		}
	}

	public void createApp(RaeAppVo raeAppVo, UserEntity userEntity,
			CFClientToken clientToken) throws Exception {
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		// 查看传入的值
		logger.info(new ReflectionToStringBuilder(raeAppVo).toString());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", raeAppVo.getDomainNam()); // 创建应用的名字和域名一致
		map.put("memory", raeAppVo.getMeorySize());
		map.put("disk_quota", raeAppVo.getHiskSize());

		// String spaceName = "test676443";//独立开发者 测试账号 459041478@qq.com 123456
		// String spaceName = "t_sll_space_001464903";
		// t_sll_001@163.com(机构管理员测试账号)(下的机构开发者t_sll_space_002@163.com)
		String spaceName = raeAppVo.getSpaceName();

		if ("400003".equals(userEntity.getRoleCode())) {// 如果是独立开发者
			spaceName = this.getCFName(userEntity.getUserEmail(), "space_");// 独立开发者的space_guid是自己拼接的
			map.put("space_guid", this.getSpaceGuid(spaceName, clientToken)); // 获取space_guid
		} else {// 机构管理员和机构开发者
			map.put("space_guid", this.getSpaceGuid(spaceName, clientToken)); // 获取space_guid
		}
		StringEntity entity = new StringEntity(JSONUtil.ObjectToJSON(map));
		String result = MyHttpClient.postMethod(
				CFClientResource.getCreateAppsUrl(true), entity, header);
		Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
				.JSONToObject(result, Map.class);

		Map meta = (Map) hashMap.get("metadata");
		if (meta != null) {// 设置返回的应用guid
			logger.info("app_guid ---->" + meta.get("guid").toString());
			raeAppVo.setAppGuid(meta.get("guid").toString()); // 保存app_guid
		} else {
			throw new Exception(result);
		}
	}

	public String getDomainId(CFClientToken clientToken) throws Exception {
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		String result = MyHttpClient.getMethod(
				CFClientResource.getDomainsUrl(), header);
		Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
				.JSONToObject(result, Map.class);
		List resources = (List) hashMap.get("resources");
		Map meta = (Map) ((Map) resources.get(0)).get("metadata");
		if (meta != null) {// 设置返回的应用guid
			return meta.get("guid").toString();
		} else {
			throw new Exception(result);
		}
	}

	public void createRoutes(RaeAppVo raeAppVo, CFClientToken clientToken)
			throws Exception {
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);

		String domainguId = this.getDomainId(clientToken);
		// String spaceName = "t_sll_space_001464903"; // 测试账号
		String spaceGuid = this.getSpaceGuid(raeAppVo.getSpaceName(),
				clientToken); // 根据spaceName返回space_guid

		Map<String, String> map = new HashMap<String, String>();
		map.put("domain_guid", domainguId);
		map.put("space_guid", spaceGuid);
		map.put("host", raeAppVo.getDomainNam()); // 域名绑定，此处域名和应用名相同

		StringEntity entity = new StringEntity(JSONUtil.ObjectToJSON(map));
		String result = MyHttpClient.postMethod(
				CFClientResource.createRoutesUrl(), entity, header);
		Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
				.JSONToObject(result, Map.class);
		Map meta = (Map) hashMap.get("metadata");
		if (meta != null) {// 设置返回的应用guid
			logger.info("route_guid ----->" + meta.get("guid").toString()); // 返回route_guid
			raeAppVo.setRouteGuid(meta.get("guid").toString());
		} else {
			throw new Exception(result);
		}
	}

	/**
	 * 应用和route绑定
	 */
	public void bingAppRoutes(RaeAppVo raeAppVo, CFClientToken clientToken)
			throws Exception {

		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		// 测试账号机构开发者t_sll_space_002@163.com
		// String app_guid = "ebcd102e-4e80-46e5-9aa5-67842db0b877";
		// String route_guid = "35599479-62a6-449e-9886-879cb9221358";
		String app_guid = raeAppVo.getAppGuid();
		String route_guid = raeAppVo.getRouteGuid();

		String result = MyHttpClient.putMethod(
				CFClientResource.bingAppandRoute(route_guid, app_guid), header);
		Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
				.JSONToObject(result, Map.class);
		Map meta = (Map) hashMap.get("metadata");
		if (meta != null) {// 设置返回的应用guid
			logger.info("bingAppandroute_guid--->"
					+ meta.get("guid").toString());
		} else {
			throw new Exception(result);
		}

	}

	private String getCFName(String str, String name) {
		name += str.substring(str.indexOf("@") + 1, str.length());
		name += "_" + str.substring(0, str.indexOf("@"));
		name = name.replaceAll("\\.", "_");
		return name;
	}

	public void uploadApp(RaeAppVo raeAppVo, CFClientToken clientToken,
			String userId, String applicationId) throws Exception {

		// 测试账号机构开发者t_sll_space_002@163.com
		// String app_guid = "ebcd102e-4e80-46e5-9aa5-67842db0b877";
		String app_guid = raeAppVo.getAppGuid(); // 上传到那个应用里

		Header header = MyHttpClient.getAuthorizationHeader(clientToken);

		StringBuffer destsb = new StringBuffer(); // 找到上传到服务器上的文件
		destsb.append(STORE_PATH).append(File.separator).append(userId)
				.append(File.separator).append(applicationId);

		File srcfile = new File(destsb.toString()); // 动态获取文件
		File file = null;
		if (srcfile.isDirectory() && srcfile.listFiles().length > 0) {
			file = srcfile.listFiles()[0];// 只保留一份文件
		} else {
			logger.error("src file path :'" + destsb + "',size:'"
					+ "' ,upload file can not null,check the file is exists");
			throw new Exception("deploy file:'" + destsb + "',size:'"
					+ srcfile.listFiles().length + "',has no file to upload");
		}

		List<Map> listMap = new ArrayList<Map>();
		Map map = new HashMap();
		map.put("fn", file.getAbsoluteFile().toString());
		map.put("size", file.length());
		map.put("sha1", file.hashCode());
		listMap.add(map);

		MultipartEntityBuilder mulentity = MultipartEntityBuilder.create();
		mulentity.addBinaryBody("application", file);
		mulentity.addTextBody("resources", JSONUtil.ObjectToJSON(listMap));
		HttpEntity entity = mulentity.build();

		String result = MyHttpClient.putMethod(
				CFClientResource.getUploadAppUrl(app_guid), entity, header);

		logger.info("upload result--------->" + result);
		if (!"{}".equals(result)) { // {}代表部署成功
			Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
					.JSONToObject(result, Map.class);
			Map meta = (Map) hashMap.get("entity");
			if (meta != null) {
				logger.info("upload_guid--->" + meta.get("guid").toString());
			} else {
				throw new Exception(result);
			}
		}
	}

	@Override
	public void apps(RaeAppVo raeAppVo, UserEntity userEntity,
			CFClientToken clientToken, String applicationId, String developId)
			throws Exception {
		this.createApp(raeAppVo, userEntity, clientToken);// 购买者 id ok
		this.createRoutes(raeAppVo, clientToken);
		this.bingAppRoutes(raeAppVo, clientToken);
		this.uploadApp(raeAppVo, clientToken, developId,// 开发者
				applicationId);
		this.startApp(raeAppVo.getAppGuid(), clientToken);
	}

	public String startApp(String guid, CFClientToken clientToken)
			throws Exception {
		String result = "";
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		Map map = new HashMap();
		map.put("state", "STARTED");
		StringEntity stringEntity = new StringEntity(JSONUtil.ObjectToJSON(map));
		stringEntity.setContentType("application/json;charset=utf-8");
		String url = CFClientResource.PAAS_PLATFORM_APPS_LIST_URL + "/" + guid;
		result = MyHttpClient.putMethod(url, stringEntity, header);
		logger.info("start upp,result:" + result);
		return result;
	}

	/**
	 * 
	 * @Title: stopApp	停止应用
	 * @Description: TODO
	 * @author sunlulu
	 * @param @param guid
	 * @param @param clientToken
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	public String stopApp(String guid, CFClientToken clientToken)
			throws Exception {
		String result = "";
		Header header = MyHttpClient.getAuthorizationHeader(clientToken);
		Map map = new HashMap();
		map.put("state", "STOPPED");
		StringEntity stringEntity = new StringEntity(JSONUtil.ObjectToJSON(map));
		stringEntity.setContentType("application/json;charset=utf-8");
		String url = CFClientResource.PAAS_PLATFORM_APPS_LIST_URL + "/" + guid;
		result = MyHttpClient.putMethod(url, stringEntity, header);
		logger.info("stop upp,result:" + result);
		return result;
	}

	// // 创建应用
	// public void startApp(String appId, CFClientToken clientToken)
	// throws Exception {
	// Header header = MyHttpClient.getAuthorizationHeader(clientToken);
	// }
	public int getRoutesNum(String domainName, CFClientToken clientToken)
			throws Exception {

		CFClientToken admintoken = CFClientToken.getAdminInstance(); // 使用管理员查询domain
		Header header = MyHttpClient.getAuthorizationHeader(admintoken);
		String url = CFClientResource.getRoutes() + "?q=host:" + domainName;
		String result = MyHttpClient.getMethod(url, header);
		Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
				.JSONToObject(result, Map.class);
		Double meta = (Double) hashMap.get("total_results");
		if (meta != null) {// 设置返回的应用guid
			return meta.intValue();
		} else {
			throw new Exception(result);
		}
	}

}
