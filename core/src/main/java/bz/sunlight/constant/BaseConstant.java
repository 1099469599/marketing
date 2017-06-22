package bz.sunlight.constant;



public class BaseConstant {

	//数据库基本状态
	public static final Short BASE_TRUE=0;
	public static final Short BASE_FALSE=1;
	
	
	
	//返回前端状态
	public static final int SUCCESS=0;
	public static final int FAIL=1;
	
//	日期格式
	public static final String DATE_FORMAT_YEAR_TO_SECOND="yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_YEAR_TO_MIN="yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_YEAR_TO_DAY="yyyy-MM-dd";
	
//	字典项分类code
	public static final String CATEGORY_CODE_IS_REPEATE="isRepeate";
	public static final String CATEGORY_CODE_IS_FOLLOW="followStatus";
	public static final String CATEGORY_CODE_HANGUP_POSITION="hangupPosition";
	public static final String CATEGORY_CODE_CALL_RESULT="callResult";
	public static final String CATEGORY_CODE_SEX="sex";
	public static final String CATEGORY_CODE_IS_SUCCESS="isSuccrss";
	
//	编码
	public static final String UTF_8="UTF-8";
	public static final String ISO_8859_1="ISO-8859-1";
	
//自定义属性类型
	public static final Short PROPERTY_TYPE_ONE=1;  //自定义属性一
	public static final Short PROPERTY_TYPE_TWO=2;  //自定义属性二
	

	
	//自定义属性所属的区域
	public static final Short CUSTOM_PROPERTIES_STATUS_RESERVE=0;  //保留区
	public static final Short CUSTOM_PROPERTIES_STATUS_CALL_RECORD=1;  //通话记录
	
	
	public static final String ADMIN_USER_NAME="beppe";  //用户名
	public static final String ADMIN_PASSWORD="123456";  //密码
	
	public static final Short CHARGE_TYPE_ALIPAY=0;  //支付宝
	public static final Short CHARGE_TYPE_WECHAT=1;  //微信
	public static final Short CHARGE_TYPE_MANUAL=2;  //手动充值
	
	public static final Short IS_BUILT_IN_NO=0;  //不能新增
	public static final Short IS_BUILT_IN_YES=1;  //可以新增
	
	
	public static final Short KEEP_STATUS_PUBLIC=0; //公共
	public static final Short KEEP_STATUS_PRIVATE=1;  //持有
	
	public static final Short HANDLE_STATUS_NO=0; //未处理
	public static final Short HANDLE_STATUS_YES=1;  //已处理
	
	// 消费类型
	public static final Short CONSUME_TYPE_BASE = 0;  // 月租
	public static final Short CONSUME_TYPE_CALL = 1;  // 通话
	public static final Short CONSUME_TYPE_KEEP = 2;  // 保留
	public static final Short CONSUME_TYPE_SEAT = 3;  // 坐席

	// 当前话务状态
	public static final String CURRENT_CALL_STATE_START = "start";							// 话务初始化，建立中
	public static final String CURRENT_CALL_STATE_CALLER_RINGING = "caller-ringing";  		// 主叫振铃
	public static final String CURRENT_CALL_STATE_CALLER_ANSWERED = "caller-answered";  	// 主叫应答
	public static final String CURRENT_CALL_STATE_CALLER_CALLSTATE = "caller-callstate";  	// 主叫接听状态
	public static final String CURRENT_CALL_STATE_CALLEE_RINGING = "callee-ringing";  		// 被叫振铃
	public static final String CURRENT_CALL_STATE_CALLEE_ANSWERED = "callee-answered";  	// 被叫应答
	public static final String CURRENT_CALL_STATE_CALLEE_CALLSTATE = "callee-callstate";  	// 被叫接听状态
	public static final String CURRENT_CALL_STATE_HANGUP = "hangup";  						// 通话结束
	
	// 通话状态
	public static final Short CALL_STATUS_BAD = 789;  		// 异常通话状态
	public static final Short CALL_STATUS_ANSWERED = 787;	// 回铃音标准嘟嘟声
	
	//数据导入状态
	public static final int RESULT_CODE_SUCCESS = 0;  // 成功
	public static final int RESULT_CODE_DOWNLOAD_FAIL = 1001;  // 下载失败
	public static final int RESULT_CODE_FAILE = 1002;  // 失败
}
