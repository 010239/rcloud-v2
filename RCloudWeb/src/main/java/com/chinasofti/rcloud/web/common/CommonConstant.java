package com.chinasofti.rcloud.web.common;

/**
 * Created by mupeng on 14-4-17.
 */
public abstract class CommonConstant {
    // HttpSession存储用户信息的key
    public static final String USER_INFO = "user_info";
    //HttpSession存储登录后页面跳转信息的key
    public static final String CURRENT_PAGE = "currentUrl";
    
    //Token cookie
    public static final String COOKIE_TOKEN = "token_info";

    //网站首页URL
    public static final String INDEX_URL = "/cloud/index";
    //用户登录页面URL
    public static  final String LOGIN_PAGE = "user/loginPage";

    //请求状态码
    //成功
    public static final String STATUS_SUCCESS = "200";
    //逻辑异常
    public static final String STATUS_BUSINESS_EXCEPTION = "501";
    //系统异常
    public static  final String STATUS_SYSTEM_EXCEPTION = "500";
    //没有权限
    public static final String STATUS_ACCESS_DENY_EXCEPTION = "600";
    //重新登录
    public static final String STATUS_SESSION_OUT_OF_TIME = "601";
    
    //校验码
    public static final String CHECK_CODE = "j_rand";
    //默认校验码,首次登陆不用输入校验码
    public static final String DEFAULT_CHECK_CODE = "1111";
    //生成校验码的时间
    public static final String CHECK_CODE_CREATE_TIME = "j_rand_create_time";
    
    //保存成功
    public static final String SAVE_SUCCESS = "保存成功";
    //操作成功
    public static final String OPERATE_SUCCESS = "操作成功";
    //操作失败
    public static final String OPERATE_FAIL = "操作失败";
    //访问权限定义
    public static final String ROLE_PERMISSION_COMMON = "300010";//公共访问权限
    
    public static final String ROLE_PERMISSION_ORG_ADMIN = "300001";//机构管理权限-涉及机构管理功能访问到的方法
    
    public static final String ROLE_PERMISSION_ORDER_ADMIN = "300002";//订单管理权限-涉及订单管理功能访问到的方法
    
    public static final String ROLE_PERMISSION_BILL_ADMIN = "300003";//账单管理权限-涉及账单管理功能访问到的方法
    
    public static final String ROLE_PERMISSION_SERVICE_ADMIN = "300004";//服务管理权限-涉及服务管理功能访问到的方法
    
    public static final String ROLE_PERMISSION_SERVICE_QUERY = "300005";//服务查询权限-涉及服务查询功能访问到的方法 包括服务凭证查询，已开通服务检索，不能开通服务
    
    public static final String ROLE_PERMISSION_APP_ADMIN = "300006";//应用管理权限-涉及应用管理功能访问到的方法
    
    public static final String ROLE_PERMISSION_APP_QUERY = "300007";//应用查询权限-涉及应用查询功能访问到的方法，已购买应用查询，已发布应用查询
    
    public static final String ROLE_PERMISSION_USER_ADMIN = "300008";//用户管理权限-涉及用户管理功能访问到的方法
    
    public static final String ROLE_PERMISSION_ACCOUNT_ADMIN = "300009";//账户管理权限-涉及账户管理功能访问到的方法
    
	public static final String SUCCESS = "000000";
	public static final String SUCCESS_TXT = "请求成功";
	public static final String FAILURE = "999999";
	public static final String FAILURE_TXT = "请求失败";

	public static final String RANDOM_CODE = "random_code";
	public static final String CODE_NUM = "909";
	public static final String CODE_ERROR = "验证码错误！";
	
	public static final String DEVLOPER_ENTITY = "400003";  //独立开发者
	public static final String ORG_ENTITY = "400001";       //机构管理员
	public static final String ORG_DEVLOPER = "400002";     //机构开发者
	
	public static final String ORG_TEANTID = "tenant";      //机构开发者对应机构管理员的ID
	
	public static final Integer USER_FROZEN = 1;//0-正常,1-冻结
	public static final Integer USER_NORMAL = 0;//0-正常,1-冻结
	
	//用户是否删除
    public static final Integer USER_ARE_HERE = 0; //0没删除 1代表删除
    public static final Integer USER_ARE_DELETED = 1; //0没删除 1代表删除
    
    public static final String SPACE_OPERATION = "机构空间注册";
    public static final String SPACE_DESC = "注册机构空间";
    
    public static final String ORG_DEVELOPER_OPERATION = "机构开发者注册";
    public static final String ORG_DEVELOPER_DESC = "注册机构开发者";
    
    public static final String AUTH_CODE = "121";
    public static final String AUTH_CODE_MSG = "校验码失效";
    


}
