package bz.sunlight.service;

import java.util.List;

import bz.sunlight.entity.Account;
import bz.sunlight.entity.Enterprise;
import bz.sunlight.entity.EnterpriseAccount;
import bz.sunlight.entity.UserInfo;
import bz.sunlight.entity.UserInfoBO;



public interface AccountService {

	// 登录
	Account doLogin(String userName, String password);

	// 获取帐户信息
	UserInfoBO getAccount(String userId);
	
	// 多表联合查询获取帐户信息
	UserInfoBO getAccountInfoByUserId(String userId);
	
	// 修改密码
	boolean changePassword(String userName, String password, String newPassword);
	
	// 根据 Id 查企业信息
	Enterprise getEnterpriseById(String enterpriseId);
	
	// 根据用户名查帐户信息
	Account getAccountByName(String userName);
	
	// 根据企业 Id 查企业帐户
	EnterpriseAccount getEnterpriseAccount(String enterpriseId);
	
	// 更新企业帐户余额
	int putEnterpriseAccount(EnterpriseAccount enterpriseAccount);
	
//	获取所有的企业信息
	List<Enterprise> getEnterprise();
	
	UserInfo getUserInfo(String userId);

}
