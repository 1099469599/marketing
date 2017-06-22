package bz.sunlight.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import bz.sunlight.constant.HttpConstant;
import bz.sunlight.entity.Account;
import bz.sunlight.entity.CallQuota;
import bz.sunlight.entity.EnterpriseAccount;
import bz.sunlight.entity.UserInfoBO;
import bz.sunlight.entity.UserInfoVO;
import bz.sunlight.service.AccountService;
import bz.sunlight.service.CallService;
import bz.sunlight.service.PayService;
import bz.sunlight.util.BeanUtil;
import bz.sunlight.util.CookieUtil;
import bz.sunlight.util.Md5Util;

public class BaseController {

	@Autowired
	protected AccountService accountService;
	@Autowired
	protected PayService payService;
	@Autowired
	protected CallService callService;
    
	public boolean cookieCheck(HttpServletRequest request, HttpServletResponse response) {
		CookieUtil cookieUtil = new CookieUtil(request, response);
		String userName = cookieUtil.getCookieValue(HttpConstant.AIM_MARKETING_USERNAME_COOKIE);
		if (userName != null) {
			String loginCookie = cookieUtil.getCookieValue(HttpConstant.AIM_MARKETING_LOGIN_COOKIE);
			if (loginCookie != null) {
				// 1. TODO 从缓存中直接取 loginCookie 比对，如若没找到则到数据库中去查
				// 2. 从数据库中查到用户登录信息拼成 cookie 再比对
				Account account = accountService.getAccountByName(userName);
				if (account != null) {
					if (loginCookie.equals(Md5Util.parseStrToMd5U32(userName + HttpConstant.LOGIN_CONFUSION_STR + account.getPassword()))) {
						return true;
					}
				}
				
			}
		}
		
		return false;
	}

	public UserInfoVO getUserInfo(String userId) {
//		UserInfoBO userInfoBO = accountService.getAccount(userId);
		UserInfoBO userInfoBO = accountService.getAccountInfoByUserId(userId);
		
		// 由于涉及到跨模块取数据,这里将部分数据录入放到 controller
		if (userInfoBO != null) {
			// 数据迁移
			UserInfoVO userInfoVO = new UserInfoVO();
			BeanUtil.copyProperties(userInfoVO, userInfoBO);
			
			String enterpriseId = userInfoVO.getEnterpriseId();
			CallQuota callQuota = payService.getQuotaByEnterprise(enterpriseId);
			if (callQuota != null) {
				userInfoVO.setCallQuantity(callQuota.getCallQuantity());

				// 查有效外呼记录条数
//				Integer calledQuantity = callService.getValidCallRecord(enterpriseId);
				EnterpriseAccount enterpriseAccount = accountService.getEnterpriseAccount(enterpriseId);
				userInfoVO.setCalledQuantity(enterpriseAccount.getCalledQuantity());

				return userInfoVO;
			}
		}
		
		return null;
	}

}
