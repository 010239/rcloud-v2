package com.chinasofti.rcloud.portal.login.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.rcloud.common.CommonConstants;
import com.chinasofti.rcloud.common.CommonUtil;
import com.chinasofti.rcloud.common.EmailUtil;
import com.chinasofti.rcloud.common.MD5;
import com.chinasofti.rcloud.common.PinyinUtil;
import com.chinasofti.rcloud.common.RCloudException;
import com.chinasofti.rcloud.common.UUIDHexGenerator;
import com.chinasofti.rcloud.common.cloudfoundry.CFClient;
import com.chinasofti.rcloud.common.cloudfoundry.domain.CFClientToken;
import com.chinasofti.rcloud.common.constants.ExceptionContants;
import com.chinasofti.rcloud.dao.ApplyEntityMapper;
import com.chinasofti.rcloud.dao.OrganizationEntityMapper;
import com.chinasofti.rcloud.dao.SpaceEntityMapper;
import com.chinasofti.rcloud.dao.SpaceToUserEntityMapper;
import com.chinasofti.rcloud.dao.UserAccountEntityMapper;
import com.chinasofti.rcloud.dao.UserEntityMapper;
import com.chinasofti.rcloud.domain.ApplyEntity;
import com.chinasofti.rcloud.domain.OrganizationEntity;
import com.chinasofti.rcloud.domain.SpaceEntity;
import com.chinasofti.rcloud.domain.SpaceToUserEntityExample;
import com.chinasofti.rcloud.domain.SpaceToUserEntityKey;
import com.chinasofti.rcloud.domain.UserAccountEntity;
import com.chinasofti.rcloud.domain.UserEntity;
import com.chinasofti.rcloud.domain.UserEntityExample;
import com.chinasofti.rcloud.portal.login.domain.UserEntityExt;
import com.chinasofti.rcloud.portal.login.service.UserService;
import com.chinasofti.rcloud.portal.organization.dao.OrganizationManagerMapperExt;
import com.chinasofti.rcloud.portal.organization.service.OrganizationManagerService;

/**
 * @author DongZhukai
 * @date 14-7-1.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserEntityMapper userEntityMapper;

	@Autowired
	private UserAccountEntityMapper userAccountEntityMapper;

	@Autowired
	private OrganizationEntityMapper organizationEntityMapper;

	@Autowired
	private SpaceEntityMapper spaceEntityMapper;

	@Autowired
	private ApplyEntityMapper applyEntityMapper;

	@Autowired
	private OrganizationManagerService organizationManagerService;

	@Autowired
	private SpaceToUserEntityMapper spaceToUserEntityMapper;

	@Autowired
	private OrganizationManagerMapperExt organizationManagerMapperExt;

	@Autowired
	private CFClient cfClient;

	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public boolean register(UserEntity user) throws RCloudException {

		return false;
	}

	@Override
	public UserEntityExt login(String username, String passwd, String code,
			String oldcode, Long createTime) throws RCloudException {
		UserEntity userEntity = this.hasUser(username); // 查询用户是否存在
		int hasUser = 1;
		UserAccountEntity userAccountEntity = null;
		if (userEntity == null) { //如果用户不存在
//			throw new RCloudException(ExceptionContants.USER_NOT_EXIST);
			hasUser = 0;
			userEntity = new UserEntity();
			UUIDHexGenerator g = new UUIDHexGenerator();
			userEntity.setUserId((String) g.generate());
			userEntity.setRoleCode(CommonConstants.DEVLOPER_ENTITY);
			userEntity.setCreateTime(new Date());
			userEntity.setUserName(username);
			userEntity.setTenant(userEntity.getUserId());
			userAccountEntity = new UserAccountEntity();
			userAccountEntity.setAccountId((String) g.generate());
			userAccountEntity.setUserId(userEntity.getUserId());
			userAccountEntity.setMoneyAmount(new BigDecimal(0));
		}else if(userEntity.getMarkDelete() == 1){ //如果标记用户已删除
			throw new RCloudException(ExceptionContants.USER_NOT_EXIST);
		} 
		else if (userEntity.getStatus() == CommonConstants.USER_FROZEN) {// 账户被冻结
			logger.error(ExceptionContants.USER_FROZEN);
			throw new RCloudException(ExceptionContants.USER_FROZEN);
		}
		UserEntityExt userEntityExt = null; // 扩展Token
		userEntity.setPassword(passwd);
		long currTime = System.currentTimeMillis();
		if (oldcode.equals(code) && (currTime - createTime) < 5 * 60 * 1000) { // 判断密码和校验码是否相等
			CFClientToken cFClientToken = null;
			try {
				cFClientToken = cfClient.login(userEntity);
				if (cFClientToken != null) {
					if (hasUser == 0) {
						cfClient.getUser(userEntity);
						userEntity.setMarkDelete(CommonConstants.USER_ARE_HERE);
						userEntity.setStatus(CommonConstants.USER_NORMAL);
						userEntity.setPassword(MD5.GetMD5Code(passwd));
						userEntityMapper.insert(userEntity);
						userAccountEntityMapper.insert(userAccountEntity);
					}
					userEntityExt = new UserEntityExt();
					userEntityExt.setAreaName(userEntity.getAreaName());
					userEntityExt.setBankAccount(userEntity.getBankAccount());
					userEntityExt.setCompanyName(userEntity.getCompanyName());
					userEntityExt.setCreateTime(userEntity.getCreateTime());
					userEntityExt.setFixedPhone(userEntity.getFixedPhone());
					userEntityExt.setMarkDelete(userEntity.getMarkDelete());
					userEntityExt.setMobilePhone(userEntity.getMobilePhone());
					userEntityExt.setOrganizationId(userEntity
							.getOrganizationId());
					userEntityExt.setCfOrgGuid(userEntity.getCfOrgGuid());
					userEntityExt.setPassword(userEntity.getPassword());
					userEntityExt.setRealName(userEntity.getRealName());
					userEntityExt.setRoleCode(userEntity.getRoleCode());
					userEntityExt.setStreatName(userEntity.getStreatName());
					userEntityExt.setUaaGuid(userEntity.getUaaGuid());
					userEntityExt.setUserEmail(userEntity.getUserEmail());
					userEntityExt.setUserId(userEntity.getUserId());
					userEntityExt.setUserEmail(userEntity.getUserEmail());
					userEntityExt.setUserName(userEntity.getUserName());
					userEntityExt.setTenant(userEntity.getTenant());
					userEntityExt.setcFClientToken(cFClientToken);
				} else {
					logger.error(ExceptionContants.AUTH_ERROR);
					throw new RCloudException(ExceptionContants.AUTH_ERROR);
				}
			} catch (Exception e) {
				logger.error(e);
				throw new RCloudException(ExceptionContants.USER_NOT_EXIST);
			}// 登录cf平台
			return userEntityExt;
		} else {
			logger.error(ExceptionContants.CHECK_CODE_ERROR);
			throw new RCloudException(ExceptionContants.CHECK_CODE_ERROR);
		}
		// }else {
		// throw new RCloudException(ExceptionContants.USER_FROZEN);
		// }

		// } else {
		// throw new RCloudException(ExceptionContants.USER_NOT_EXIST);
		// }
	}

	@Override
	public UserEntity hasUserEntity(String username) throws RCloudException {
		UserEntityExample example = new UserEntityExample();
		example.createCriteria().andUserNameEqualTo(username).andMarkDeleteEqualTo(0);
		List<UserEntity> list = userEntityMapper.selectByExample(example);// 查询用户是否存在
		UserEntity user = null;
		if (list.size() != 0) {
			user = list.get(0);
		} else {
			throw new RCloudException(ExceptionContants.USER_NOT_EXIST);
		}
		return user;
	}

	public UserEntity hasUser(String username) throws RCloudException {
		UserEntityExample example = new UserEntityExample();
		example.createCriteria().andUserNameEqualTo(username).andMarkDeleteEqualTo(0);
		List<UserEntity> list = userEntityMapper.selectByExample(example);// 查询用户是否存在
		UserEntity user = null;
		if (list != null && list.size() != 0) {
			user = list.get(0);
		}
		return user;
	}

	/**
	 * 查看Email是否已经存在 存在为true 不存在false
	 */
	@Override
	public void hasUserEntityEmail(String email) throws RCloudException {
		UserEntityExample example = new UserEntityExample();
		example.createCriteria().andUserEmailEqualTo(email).andMarkDeleteEqualTo(0); //0表示未删除
		
		List<UserEntity> list = userEntityMapper.selectByExample(example);// 查询用户是否存在
		if (list.size() != 0) {
			throw new RCloudException(ExceptionContants.EMAIL_EXIST);
		}
	}
	
	@Override
	public void hasUserName(String userName) throws RCloudException {
		UserEntityExample example = new UserEntityExample();
		example.createCriteria().andUserNameEqualTo(userName);
		List<UserEntity> list = userEntityMapper.selectByExample(example);// 查询用户是否存在
		if (list.size() != 0) {
			throw new RCloudException(ExceptionContants.USER_EXIST);
		}
	}

	/**
	 * 
	 * @Description: 收集前台信息，保存独立开发者 1.先保存数据到表portal_user 2.再发起（保存）申请到表boss_apply
	 *               3.带后台审核通过以后，调用rest接口（包含用户ID和机构ID[机构ID为空]） 4.最后到cf平台注册用户信息
	 *               5.把返回UserEntity数据中的password存成MD5值，uaa_guid的值,更新到portal_user
	 * @param userEntity
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	@Override
	public boolean saveUserEntity(UserEntity userEntity) throws RCloudException {
		try {
			// step1:收集数据保存到表portal_user
			UUIDHexGenerator g = new UUIDHexGenerator();
			String userId = (String) g.generate();
			userEntity.setUserId(userId);
			userEntity.setRoleCode(CommonConstants.DEVLOPER_ENTITY);
			userEntity.setCreateTime(new Date());
			userEntity.setTenant(userId);
			userEntity.setMarkDelete(CommonConstants.USER_ARE_HERE);
			userEntity.setStatus(CommonConstants.USER_NORMAL);
			userEntityMapper.insert(userEntity);

			UserAccountEntity userAccountEntity = new UserAccountEntity();
			userAccountEntity.setAccountId((String) g.generate());
			userAccountEntity.setUserId(userEntity.getUserId());
			userAccountEntity.setMoneyAmount(new BigDecimal(0));
			userAccountEntityMapper.insert(userAccountEntity);

			this.registEntity(userEntity);
			// step2:发起申请到boss_apply
			// ApplyEntity applyEntity = new ApplyEntity();
			// applyEntity.setApplyId((String)g.generate());
			// applyEntity.setApplyType(APPLY_TYPE);
			// applyEntity.setApplyExplanation(APPLY_EXPLAN);
			// applyEntity.setApplyUser(userEntity.getUserId());//外键对应userID
			// applyEntity.setApplyTime(new Date());
			// applyEntity.setStatus(STATUS);
			// applyEntityMapper.insert(applyEntity);
		} catch (Exception e) {
			logger.error(e);
			throw new RCloudException(e);
		}
		return true;
	}

	/**
	 * 
	 * @Description: 保存机构管理者 1.先保存数据到表portal_user 2.再发起（保存）申请到表boss_apply
	 *               3.带后台审核通过以后，调用rest接口（包含用户ID和机构ID） 4.最后到cf平台注册用户信息
	 *               5.把返回UserEntity数据中的password存成MD5值，uaa_guid的值,更新到portal_user
	 * @param userEntity
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	@Override
	public boolean saveUserEntityORG(UserEntity userEntity,
			OrganizationEntity organizationEntity) throws RCloudException {
		try {
			// step1:收集数据保存到表portal_user
			UUIDHexGenerator g = new UUIDHexGenerator();
			// 保存组织信息
			organizationEntity.setOrganizationId((String) g.generate());
			organizationEntity.setOrganizationName(PinyinUtil
					.cn2Spell(organizationEntity.getOrganizationChname())
					+ CommonUtil.getRamdomSix());
			organizationEntityMapper.insert(organizationEntity);
			// 保存用户信息
			String userId = (String) g.generate();
			userEntity.setUserId(userId);
			userEntity.setRoleCode(CommonConstants.ORG_ENTITY);
			userEntity.setCreateTime(new Date());
			userEntity
					.setOrganizationId(organizationEntity.getOrganizationId());
			userEntity.setTenant(userId);
			userEntity.setMarkDelete(CommonConstants.USER_ARE_HERE);
			userEntity.setStatus(CommonConstants.USER_NORMAL);
			userEntityMapper.insert(userEntity);
			// 保存用户对应的account信息
			UserAccountEntity userAccountEntity = new UserAccountEntity();
			userAccountEntity.setAccountId((String) g.generate());
			userAccountEntity.setUserId(userEntity.getUserId());
			userAccountEntity.setMoneyAmount(new BigDecimal(0));
			userAccountEntityMapper.insert(userAccountEntity);

			// step2:发起申请到boss_apply
			ApplyEntity applyEntity = new ApplyEntity();
			applyEntity.setApplyId((String) g.generate());
			applyEntity.setApplyType(CommonConstants.APPLY_TYPE);
			applyEntity.setApplyExplanation(CommonConstants.APPLY_EXPLAN);
			applyEntity.setApplyUser(userEntity.getUserId());// 申请人和email的名字相同暂用email
			applyEntity.setApplyTime(new Date());
			applyEntity.setStatus(CommonConstants.STATUS);
			applyEntityMapper.insert(applyEntity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RCloudException(e);
		}
		return true;
	}

	/**
	 * 注册结构开发者
	 * 
	 * @param userEntity
	 *            收集机构开发者的信息
	 * @param loginuser
	 *            登录机构管理员信息
	 */
	@Override
	public boolean saveUserEntityDevleper(UserEntity testDevloper,
			UserEntityExt loginuser) throws RCloudException {

		UUIDHexGenerator g = new UUIDHexGenerator();
		testDevloper.setUserId((String) g.generate());
		testDevloper.setRoleCode(CommonConstants.ORG_DEVLOPER);
		testDevloper.setCreateTime(new Date());
		testDevloper.setOrganizationId(loginuser.getOrganizationId());// 机构管理员的组织ID
		testDevloper.setCfOrgGuid(loginuser.getCfOrgGuid());// 机构管理员在cf平台注册的ID
		testDevloper.setTenant(loginuser.getTenant()); // 机构管理员的Tenant
		testDevloper.setMarkDelete(CommonConstants.USER_ARE_HERE);
		testDevloper.setStatus(CommonConstants.USER_NORMAL);
		// 保存机构开发者到account表中
		UserAccountEntity userAccountEntity = new UserAccountEntity();
		userAccountEntity.setAccountId((String) g.generate());
		userAccountEntity.setUserId(testDevloper.getUserId());
		userAccountEntity.setMoneyAmount(new BigDecimal(0));

		try {// 两步入库动作，机构开发者和机构开发者对应的account
			userEntityMapper.insert(testDevloper);
			userAccountEntityMapper.insert(userAccountEntity);

			cfClient.createOrgDeveloper(testDevloper,
					loginuser.getcFClientToken()); // 注册机构开发者到cf平台

			testDevloper
					.setPassword(MD5.GetMD5Code(testDevloper.getPassword())); // 注册成功以后，修改成MD5以后的值
			userEntityMapper.updateByPrimaryKey(testDevloper);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RCloudException(e);
		}// 注册机构开发者
		return true;
	}

	/**
	 * 保存工作空间
	 * 
	 * @param userEntity
	 *            用户登录的user信息
	 * @param spaceEntity
	 *            空间信息
	 */
	@Override
	public boolean saveSpace(UserEntityExt userEntity, SpaceEntity spaceEntity)
			throws RCloudException {
		CFClientToken clientToken = userEntity.getcFClientToken();
		OrganizationEntity organizationEntity = organizationEntityMapper
				.selectByPrimaryKey(userEntity.getOrganizationId());
		UUIDHexGenerator g = new UUIDHexGenerator();
		spaceEntity.setSpaceId((String) g.generate());
		spaceEntity.setCfOrgGuid(organizationEntity.getCfOrgGuid()); // 设置组织在cf平台上的数据
		spaceEntity.setOrganizationId(organizationEntity.getOrganizationId());
		spaceEntity.setSpaceName(PinyinUtil.cn2Spell(spaceEntity
				.getSpaceChname()) + CommonUtil.getRamdomSix());

		SpaceToUserEntityKey paceToUserEntityKey = new SpaceToUserEntityKey();
		paceToUserEntityKey.setSpaceId(spaceEntity.getSpaceId());
		paceToUserEntityKey.setUserId(userEntity.getUserId());

		try {
			// 为保证事物，先进行入库动作，一旦入库有问题，执行回滚
			spaceEntityMapper.insert(spaceEntity);
			spaceToUserEntityMapper.insert(paceToUserEntityKey);
			// 注册cf平台
			cfClient.createSpace(spaceEntity, clientToken);
			//授权空间管理
			cfClient.authorizeToSpaceManager(userEntity, spaceEntity,clientToken);
			//授权空间开发者
			cfClient.authorizeToSpaceDeveloper(userEntity, spaceEntity,clientToken);
			
			// 注册工作空间以后返回cfspaceguid,更新工作空间
			spaceEntityMapper.updateByPrimaryKey(spaceEntity);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e);
			throw new RCloudException(e);
		}
		return true;
	}

	@Override
	public boolean updateOrganizationEntity(
			OrganizationEntity organizationEntity) throws RCloudException {
		try {
			organizationEntityMapper.updateByPrimaryKey(organizationEntity);
		} catch (Exception e) {
			logger.error(e);
			throw new RCloudException(e);
		}
		return true;
	}

	/**
	 * 
	 */
	@Override
	public boolean updateUserEntity(UserEntity userEntity)
			throws RCloudException {
		try {
			userEntityMapper.updateByPrimaryKey(userEntity);
		} catch (Exception e) {
			logger.error(e);
			throw new RCloudException(e);
		}
		return true;
	}

	/**
	 * 
	 * @Description: 注册组织机构
	 * @param userEntity
	 * @param organizationEntity
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	@Override
	public boolean registEntity(UserEntity userEntity,
			OrganizationEntity organizationEntity) throws RCloudException {
		// step1:先注册组织,再更新组织到本地表中

		registEntity(organizationEntity);
		try {
			// step2:注册机构管理员
			organizationEntity = organizationEntityMapper
					.selectByPrimaryKey(organizationEntity.getOrganizationId());
			userEntity = userEntityMapper.selectByPrimaryKey(userEntity
					.getUserId());
			userEntity.setCfOrgGuid(organizationEntity.getCfOrgGuid());
			cfClient.createOrgAdmin(userEntity);
			// step3:修改机构管理员的密码成MD5保存到本地表中
			userEntity.setPassword(MD5.GetMD5Code(userEntity.getPassword()));
			// setp4:更新
			updateUserEntity(userEntity);
		} catch (Exception e) {
			logger.error(e);
			throw new RCloudException(e);
		}
		return true;
	}

	/**
	 * 注册机构开发者
	 */
	@Override
	public boolean registEntityDevloper(UserEntity userEntity,
			CFClientToken clientToken) throws RCloudException {

		try {
			OrganizationEntity organizationEntity = organizationEntityMapper
					.selectByPrimaryKey(userEntity.getOrganizationId());
			userEntity = userEntityMapper.selectByPrimaryKey(userEntity
					.getUserId());
			userEntity.setCfOrgGuid(organizationEntity.getCfOrgGuid());
			// 修改机构开发者的密码成MD5保存到本地表中
			userEntity.setPassword(MD5.GetMD5Code(userEntity.getPassword()));
			// setp4:更新
			updateUserEntity(userEntity);
		} catch (Exception e) {
			logger.error(e);
			throw new RCloudException(e);
		}
		return true;
	}

	/**
	 * 
	 * @Description: 注册独立开发者
	 * @param userEntity
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	@Override
	public boolean registEntity(UserEntity userEntity) throws RCloudException {
		try {
			userEntity = userEntityMapper.selectByPrimaryKey(userEntity
					.getUserId());
			cfClient.createDeveloper(userEntity);// 注册用户
			// step3:修改机构管理员的密码成MD5保存到本地表中
			userEntity.setPassword(MD5.GetMD5Code(userEntity.getPassword()));
			// setp4:更新
			updateUserEntity(userEntity);
		} catch (Exception e) {
			logger.error(e);
			throw new RCloudException(e);
		}
		return true;

	}

	/**
	 * 
	 * @Description: 注册组织机构
	 * @param organizationEntity
	 * @return
	 * @throws RCloudException
	 * @return boolean
	 * @throws
	 */
	@Override
	public boolean registEntity(OrganizationEntity organizationEntity)
			throws RCloudException {
		try {
			organizationEntity = organizationEntityMapper
					.selectByPrimaryKey(organizationEntity.getOrganizationId());
			cfClient.createOrg(organizationEntity);
			updateOrganizationEntity(organizationEntity); // 更新本地库
		} catch (Exception e) {
			logger.error(e);
			throw new RCloudException(e);
		}
		return true;
	}

	/**
	 * 根据组织Id 和 角色查询 组织管理者
	 */
	@Override
	public UserEntity selectByOrgIdAndRole(String orgId, String roleId)
			throws RCloudException {
		UserEntityExample example = new UserEntityExample();
		example.createCriteria().andOrganizationIdEqualTo(orgId)
				.andRoleCodeEqualTo(roleId);
		List<UserEntity> list = userEntityMapper.selectByExample(example);// 查询用户是否存在
		UserEntity userEntity = null;
		if (list.size() != 0) {
			userEntity = list.get(0);
		} else {
			throw new RCloudException(ExceptionContants.USER_NOT_EXIST);
		}
		return userEntity;
	}

	/**
	 * 发送邮件
	 */
	@Override
	public String sendEmail(String eamil) throws RCloudException {
		String code = null;
		try {
			code = CommonUtil.getRamdomSix();
			String content = "欢迎使用RCloud，您此次操作的验证码是" + code;
			EmailUtil.sendEmail(eamil, CommonConstants.EMAIL_SUBJECT, content);
		} catch (Exception e) {
			logger.error(e);
			throw new RCloudException(ExceptionContants.USER_NOT_EXIST);
		}
		return code;
	}

	/**
	 * 更新用户密码
	 */
	@Override
	public boolean modifyUserPwd(UserEntity userEntity) throws RCloudException {
		// setp1 调用cf平台更新
		// setp2把明文密码改成md5以后的密码
		// setp3更新数据库
		try {
			cfClient.createDeveloper(userEntity);// 注册用户
			userEntity.setPassword(MD5.GetMD5Code(userEntity.getPassword()));
			updateUserEntity(userEntity);
		} catch (Exception e) {
			logger.error(e);
			throw new RCloudException(e);
		}
		return true;
	}

	/**
	 * 此处在xml配置一个事物
	 */
	@Override
	public boolean bingEntityDevleper(UserEntityExt userEntity,
			List<UserEntity> listDev, String spaceId) throws RCloudException {

		CFClientToken clientToken = userEntity.getcFClientToken();
		SpaceEntity spaceEntity = spaceEntityMapper.selectByPrimaryKey(spaceId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userEntity.getUserId());
		map.put("spaceId", spaceId);
		List<SpaceToUserEntityKey> bingUser = this
				.selectBySpaceIdNotUserId(map);// 数据库中已绑定的用户

		if (listDev.size() == 0) {// 全部解绑,并删除数据库中的数据
			for (SpaceToUserEntityKey spaceToUserEntityKey : bingUser) {// 遍历所有的数据，解绑并删除数据库
				UserEntity u = userEntityMapper
						.selectByPrimaryKey(spaceToUserEntityKey.getUserId());
				try {
					spaceToUserEntityMapper
							.deleteByPrimaryKey(spaceToUserEntityKey);
					cfClient.unBindUserToSpace(u, spaceEntity, clientToken);
				} catch (Exception e) {
					throw new RCloudException(e);
				}
			}
		} else { // 部分解绑，部分绑定
					// 查找数据库不要删除的数据以及需要删除的数据
			List<SpaceToUserEntityKey> notDeletUser = new ArrayList<SpaceToUserEntityKey>();// 不需要删除的数据
			List<SpaceToUserEntityKey> deletUser = new ArrayList<SpaceToUserEntityKey>();// 需要删除的数据
			List<UserEntity> needDoubleUser = new ArrayList<UserEntity>();// 既需要删除也需要添加的数据

			for (SpaceToUserEntityKey spaceToUserEntityKey : bingUser) {// 遍历库中的数据
				for (UserEntity needBingUser : listDev) {// 遍历用户提交的需要绑定的用户
					if (spaceToUserEntityKey.getUserId().equals(
							needBingUser.getUserId())) {// 如果数据库中的数据和用户需要绑定的Id相同，则不用删除
						notDeletUser.add(spaceToUserEntityKey);
					} else {
						deletUser.add(spaceToUserEntityKey);
					}
				}
			}

			// 查询需要特殊处理的用户数据
			for (UserEntity needBingUser : listDev) {
				if (notDeletUser.size() > 0) {
					for (SpaceToUserEntityKey spaceToUser : notDeletUser) {
						if (spaceToUser.getUserId().equals(
								needBingUser.getUserId())) {
						} else {
							needDoubleUser.add(needBingUser);
						}
					}
				} else {
					needDoubleUser.add(needBingUser);
				}
			}
			// 删除当前空间中需要删除的数据
			for (SpaceToUserEntityKey element : deletUser) {
				UserEntity u = userEntityMapper.selectByPrimaryKey(element
						.getUserId());
				try {
					spaceToUserEntityMapper.deleteByPrimaryKey(element);
					cfClient.unBindUserToSpace(u, spaceEntity, clientToken);
				} catch (Exception e) {
					throw new RCloudException(e);
				}
			}
			// 处理既需要删除又需要插入的数据
			for (UserEntity element : needDoubleUser) {
				SpaceToUserEntityExample example = new SpaceToUserEntityExample();
				example.createCriteria().andUserIdEqualTo(element.getUserId());
				List<SpaceToUserEntityKey> spaceuserList = spaceToUserEntityMapper
						.selectByExample(example);
				UserEntity u = userEntityMapper.selectByPrimaryKey(element
						.getUserId());

				if (spaceuserList != null && spaceuserList.size() > 0) {
					for (SpaceToUserEntityKey ele : spaceuserList) {
						SpaceEntity space = spaceEntityMapper
								.selectByPrimaryKey(ele.getSpaceId());
						if (space != null) {
							try {
								spaceToUserEntityMapper.deleteByPrimaryKey(ele);
								cfClient.unBindUserToSpace(u, space,
										clientToken);
							} catch (Exception e) {
								throw new RCloudException(e);
							}
						}
					}
				}
				try {
					SpaceToUserEntityKey paceToUserEntityKey = new SpaceToUserEntityKey();
					paceToUserEntityKey.setSpaceId(spaceId);
					paceToUserEntityKey.setUserId(u.getUserId());
					spaceToUserEntityMapper.insert(paceToUserEntityKey);
					cfClient.bindUserToSpace(u, spaceEntity, clientToken);
				} catch (Exception e) {
					throw new RCloudException(e);
				}
			}
		}
		return true;
	}

	/*
	 * 根据组织ID和角色类型返回所有的机构开发者
	 */
	@Override
	public List<Map<String, String>> selectByMap(Map<String, Object> map)
			throws RCloudException {

		// UserEntityExample example = new UserEntityExample();
		// example.createCriteria().
		// andOrganizationIdEqualTo((String) map.get("orgId"))
		// .andRoleCodeEqualTo((String) map.get("rolecode"))
		// .andMarkDeleteEqualTo(CommonConstants.USER_ARE_HERE);
		// List<UserEntity> list = userEntityMapper.selectByExample(example);

		List<Map<String, String>> list = organizationManagerMapperExt
				.getUserOfOrganization(map);
		return list;
	}

	@Override
	public List<SpaceToUserEntityKey> selectBySpaceIdNotUserId(
			Map<String, Object> map) throws RCloudException {

		SpaceToUserEntityExample example = new SpaceToUserEntityExample();
		example.createCriteria().andSpaceIdEqualTo((String) map.get("spaceId"))
				.andUserIdNotEqualTo((String) map.get("userId"));
		List<SpaceToUserEntityKey> list = spaceToUserEntityMapper
				.selectByExample(example);
		return list;
	}

	public int selectNumsBySpaceIdNotUserId(Map<String, Object> map)
			throws RCloudException {

		SpaceToUserEntityExample example = new SpaceToUserEntityExample();
		example.createCriteria().andSpaceIdEqualTo((String) map.get("spaceId"))
				.andUserIdNotEqualTo((String) map.get("userId"));
		int nums = spaceToUserEntityMapper.countByExample(example);
		return nums;
	}

	/*
	 * 根据用户的guid查询用户的信息
	 */
	@Override
	public UserEntity getUserByGuid(String guid) throws RCloudException {
		UserEntityExample example = new UserEntityExample();
		example.createCriteria().andUaaGuidEqualTo(guid);
		List<UserEntity> list = userEntityMapper.selectByExample(example);// 查询用户是否存在
		if (list.size() != 0) {
			return list.get(0);
		} else {
			throw new RCloudException(
					ExceptionContants.ERROR_MSG
							.get(ExceptionContants.USER_NOT_EXIST),
					ExceptionContants.USER_NOT_EXIST);
		}
	}

	/*
	 * 根据用户的userid查询用户的信息
	 */
	@Override
	public UserEntity getUserByUserid(String userid) throws RCloudException {
		UserEntity user = userEntityMapper.selectByPrimaryKey(userid);// 查询用户是否存在
		if (user != null) {
			return user;
		} else {
			throw new RCloudException(
					ExceptionContants.ERROR_MSG
							.get(ExceptionContants.USER_NOT_EXIST),
					ExceptionContants.USER_NOT_EXIST);
		}
	}

	@Override
	public String isLogin(UserEntityExt user) throws RCloudException {
		
		String isLogin = "";
		
		if (user != null) {
			isLogin = CommonConstants.USER_IS_LOGIN;
		} else {
			isLogin = CommonConstants.USER_NOT_LOGIN;
		}
					
		return isLogin;
	}

	@Override
	public String isPasswordRight(String password,String userId) throws RCloudException {
		String isRight = "";
		
		UserEntityExample example = new UserEntityExample();
		example.createCriteria().andUserIdEqualTo(userId).andPasswordEqualTo(password);
		
		List<UserEntity> user = userEntityMapper.selectByExample(example);
		if (user.size() == 1) {
			isRight = CommonConstants.PASSWORD_IS_RIGHT;
		} else {
			isRight = CommonConstants.PASSWORD_NOT_RIGHT;
		}
		return isRight;

	}

}
