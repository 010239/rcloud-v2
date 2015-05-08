package com.chinasofti.rcloud.common;

public abstract class CommonConstants {
	// 账单类型：
	// 服务账单
	public static final int BILL_TYPE_SERVICE = 1;
	// 应用账单
	public static final int BILL_TYPE_APPLICATION = 2;
	// 月账单
	public static final int BILL_TYPE_MONTH = 1;
	// 季度账单
	public static final int BILL_TYPE_QUARTER = 2;
	// 年账单
	public static final int BILL_TYPE_YEAR = 3;

	// 是否欠费：
	// 欠费
	public static final int OWED_STATUS_Y = 0;
	// 不欠费
	public static final int OWED_STATUS_N = 1;
	
	// 是否计入会计变更
	// 未计入
	public static final int UN_ACCOUNTING = 0;
	// 计入
	public static final int ACCOUNTING = 1;
	
	// 服务订单状态
	// 暂停
	public static final int SERVICE_STATUS_STOP = 0;
	// 有效
	public static final int SERVICE_STATUS_EFFECTIVE = 1;
	// 结束
	public static final int SERVICE_STATUS_END = 2;
	// 撤销
	public static final int SERVICE_STATUS_CANCEL = 3;
	
	// 应用订单状态
	// 暂停
	public static final int APPLICATION_STATUS_STOP = 2;
	// 有效
	public static final int APPLICATION_STATUS_EFFECTIVE = 1;
	// 结束
	public static final int APPLICATION_STATUS_END = 3;
	// 撤销
	public static final int APPLICATION_STATUS_CANCEL = 4;
	
	// 订单会计标记
	// 会计周期不需要处理
	public static final int ORDER_UNNEED_HANDLE = 0;
	// 会计周期需要正常处理
	public static final int ORDER_HANDLE = 1;
	// 处理补缴费用
	public static final int ORDER_SUPPLEMENTARY_PAYMENT = 2;
	
	// 购买类型
	// 服务
	public static final int TYPE_SERVICE = 1;
	// 应用
	public static final int TYPE_APPLICATION = 2;
	
	// 资金流向
	// 充值
	public static final int MONY_FLOW_RECHARGE = 1;
	// 收入
	public static final int MONY_FLOW_INCOME = 2;
	// 支出
	public static final int MONY_FLOW_PAY = 3;
	
	// 交易类型
	// 1：充值
	public static final int TRANSACTION_TYPE_RECHARGE = 1;
	// 2：应用订单支付
	public static final int TRANSACTION_TYPE_APP_ORDER_PAY = 2;
	// 3：服务订单支付
	public static final int TRANSACTION_TYPE_SERVICE_ORDER_PAY = 3;
	// 4：应用账单扣款
	public static final int TRANSACTION_TYPE_APP_BILL_PAY = 4;
	// 5：服务账单扣款
	public static final int TRANSACTION_TYPE_SERVICE_BILL_PAY = 5;
	// 6：应用账单收入
	public static final int TRANSACTION_TYPE_APP_BILL_INCOME = 6;
	
	// 付费类型
	// 1:一次性付费
	public static final int PAY_PATTERN_ONCE = 0;
	
	// 2：按月支付
	public static final int PAY_PATTERN_MONTH = 1;
	
	public static final String DEPLOYMENT_TYPE_PRE = "0";//0-预部署，1-后部署，3-外部应用
	
	public static final String DEPLOYMENT_TYPE_AFTER = "1";//0-预部署，1-后部署，3-外部应用
	
	public static final String RAE_SERVICE_ID = "8a8be9a4475dcaef01475dcaef7e0000";//RAE serviceID
	//用户是否删除
    public static final Integer USER_ARE_HERE = 0; //0没删除 1代表删除
    public static final Integer USER_ARE_DELETED = 1; //0没删除 1代表删除
    public static final int NOT_DELETE = 0;
	
	// 购买无期限
	public static final String PAY_MONTH_NOTIME = "0";
	
	// 机构开发者编码
//	public static final String ORGDEVELOPER_CODE = "400002";
	
	public static final String DEVLOPER_ENTITY = "400003";  //独立开发者
	public static final String ORG_ENTITY = "400001";       //机构管理员
	public static final String ORG_DEVLOPER = "400002";     //机构开发者
	
	public static final int STATUS = 0;       		//状态：0-待审核，1-审核通过，2-审核不通过
	public static final String APPLY_TYPE = "04";   //01-应用发布申请，02-发布信息修改申请，03-取消发布申请，04-注册申请
	public static final String APPLY_EXPLAN = "新用户注册申请"; //申请说明
	
	public static final String EMAIL_SUBJECT = "邮箱验证";
	
	public static final Integer USER_FROZEN = 1;//0-正常,1-冻结
	public static final Integer USER_NORMAL = 0;//0-正常,1-冻结
	
	// 消息状态
	// 已读
	public static final boolean MESSAGE_STATUS_READ = true;
	
	public static final boolean MESSAGE_STATUS_NOT_READ = false;
	
	// 公告状态
	// 已读
	public static final boolean NOTICE_STATUS_READ = true;
	
	public static final boolean NOTICE_STATUS_NOT_READ = false;
	 //逻辑异常
    public static final String STATUS_BUSINESS_EXCEPTION = "501";
    
    //定义应用类型常量
    public static final String APP_TYPE_GOVERNMENT= "100001"; //政务类

    public static final String APP_TYPE_MEDICINE = "100002";//医药类
    
    public static final String APP_TYPE_TRAFFIC = "100003";//交通类
    
    public static final String APP_TYPE_EDUCATION = "100004";//教育类
    
    //用户是否登录
    public static final String USER_IS_LOGIN = "0"; //0已经登录
    public static final String USER_NOT_LOGIN = "1"; //1 用户未登录
    
    //判断原密码是否正确
    public static final String PASSWORD_IS_RIGHT = "0"; //0原密码正确
    public static final String PASSWORD_NOT_RIGHT = "1"; //1 原密码输入错误
}

