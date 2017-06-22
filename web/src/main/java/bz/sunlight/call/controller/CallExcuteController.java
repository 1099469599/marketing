package bz.sunlight.call.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.common.BaseController;
import bz.sunlight.constant.HttpConstant;
import bz.sunlight.entity.EnterpriseAccount;
import bz.sunlight.entity.UserInfo;

@Controller
@RequestMapping("/call/")
public class CallExcuteController extends BaseController{
	
	@RequestMapping(value="excute",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,String> calling(String secretId, @CookieValue(HttpConstant.AIM_MARKETING_USERID_COOKIE) String userId, @CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId){
		Map<String, String> map = new HashMap<String, String>();

		// 获取主叫方电话号码
		UserInfo userInfo = accountService.getUserInfo(userId);
		if (userInfo != null) {
			String callerNum = userInfo.getCallerMobile();

			// 被叫方电话
			String calleeNum = secretId;
			
			// 先检查余额看能不能打
			EnterpriseAccount enterpriseAccount = accountService.getEnterpriseAccount(userInfo.getEnterpriseId());
			if (enterpriseAccount.getAccountBalance() < 0) {
				map.put("msg", "disabled");
			} else {
				if (callService.calling(callerNum, calleeNum, enterpriseId, userId)) {
					map.put("msg", "success");
				} else {
					map.put("msg", "failed");
				}
			}
		} else {
			map.put("msg", "failed");
		}
		
		return map;
	}

	@RequestMapping(value="callStateUrl",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,String> callStateUrl(@RequestBody JSONObject jsonObject){
		return callService.callStateUrl(jsonObject);
	}
	
	@RequestMapping(value="hangupCdrUrl",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,String> hangupCdrUrl(@RequestBody JSONObject jsonObject){
		return callService.hangupCdrUrl(jsonObject);
	}
	
}
