package com.chinasofti.rcloud.common.constants;

public class BusinessConstants {
	
	/**
	 *  软删除代码 0-未删除，1-已删除
	 */
	public final static int DELETE_STATUS_NO_DELETE = 0;
	
	public final static int DELETE_STATUS_DELETE = 1;

  public final static int PERSONAL_DEVELOPER = 0;
  public final static int ORGANIZATION_MANAGER = 1;
  public final static int ORGANIZATION_DEVELOPER = 2;
  
  /**
   * 服务凭证状态0-未激活   1-有效  2-失效  3-注销
   */
  public final static  int  SERVICECREDENTIAL_NONACTIVATED=0;
  public final static  int  SERVICECREDENTIAL_ACTIVATE=1;
  public final static  int  SERVICECREDENTIAL_FAILURE=2;
  public final static  int  SERVICECREDENTIAL_LOGOUT=3;
  
  public final static  String   SERVICECREDENTIAL_MARK="rcloud-serviceCredential";
  
  /**
   * 缓存工厂类取值范围，local-本地缓存实现，memcached-memcache服务器实现,aliyun-阿里云缓存服务实现
   */
  public final static String CACHE_IMPL_LOCAL = "local";
  
  public final static String CACHE_IMPL_MEMCACHE = "memcached";
  
  public final static String CACHE_IMPL_ALIYUN = "aliyun";
  
  //000000	校验成功
  public final static  String   SERVICECREDENTIALVERIFY_SUCCESS="000000";
  
  //000101	服务凭证不存在或已失效
  public final static  String   SERVICECREDENTIALVERIFY_NOEFFICACY="000101";
  
  //000102	签名不匹配
  public final static  String   SERVICECREDENTIALVERIFY_NOMATCH="000102";
  //  000103	无有效订单
  public final static  String   SERVICECREDENTIALVERIFY_ORDERINVALID="000103";
  //999999	未知异常
  public final static  String   SERVICECREDENTIALVERIFY_ERROR="999999";
  
  //系统初始化数据key
  public final static String SYSTEM_INIT_KEY_ROLE_PERMISSION = "com.chinasofti.rcloud.parameters.role-permission";
  
  public final static String SYSTEM_INIT_KEY_ROLE = "com.chinasofti.rcloud.parameters.role";
  
  public final static String SYSTEM_INIT_KEY_PERMISSION = "com.chinasofti.rcloud.parameters.permission";
}
