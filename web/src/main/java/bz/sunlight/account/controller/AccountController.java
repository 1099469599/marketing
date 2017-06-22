package bz.sunlight.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.common.BaseController;
import bz.sunlight.constant.HttpConstant;
import bz.sunlight.entity.Account;
import bz.sunlight.entity.UserInfoVO;
import bz.sunlight.util.CookieUtil;
import bz.sunlight.util.Md5Util;

@Controller
public class AccountController extends BaseController{
	
	
	@RequestMapping(value="check",method=RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<Void> check(HttpServletRequest request, HttpServletResponse response){
		if (cookieCheck(request, response)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserInfoVO> login(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response){
		String userName = (String)jsonObject.get("userName");
		String password = (String)jsonObject.get("password");

		Account account = accountService.doLogin(userName,password);
		if (account != null) {
			UserInfoVO userInfoVO = getUserInfo(account.getUserInfoId());
			if (userInfoVO != null) {
				CookieUtil cookieUtil=new CookieUtil(request, response, HttpConstant.REFRESHTOKENEXPIRESUTC_SECOND);
				
				// 种登录检验 cookie
				String hashPw = account.getPassword();
				String cookieStr = Md5Util.parseStrToMd5U32(userName + HttpConstant.LOGIN_CONFUSION_STR + hashPw);
				cookieUtil.addCookie(HttpConstant.AIM_MARKETING_LOGIN_COOKIE, cookieStr);
				cookieUtil.addCookie(HttpConstant.AIM_MARKETING_USERNAME_COOKIE, userName);
				
				// 种企业 Id 与 帐户 Id
				cookieUtil.addCookie(HttpConstant.AIM_MARKETING_USERID_COOKIE, userInfoVO.getUserInfoId());
				cookieUtil.addCookie(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE, userInfoVO.getEnterpriseId());
				
				return new ResponseEntity<UserInfoVO>(userInfoVO,HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<UserInfoVO>(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="logout",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response){
		CookieUtil cookieUtil=new CookieUtil(request, response);
		cookieUtil.deleteAllCookies();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value="account",method=RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<UserInfoVO> account(@CookieValue(HttpConstant.AIM_MARKETING_USERID_COOKIE) String userId){
		UserInfoVO userInfoVO = getUserInfo(userId);
		return new ResponseEntity<UserInfoVO>(userInfoVO,HttpStatus.OK);
	}

	@RequestMapping(value="changePassword",method=RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<Void> changePassword(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response){
		String userName = (String)jsonObject.get("userName");
		String password = (String)jsonObject.get("password");
		String newPassword = (String)jsonObject.get("newPassword");
		
		if (accountService.changePassword(userName, password, newPassword)) {
			// 修改密码后删除帐户相关 cookie
			CookieUtil cookieUtil = new CookieUtil(request, response);
			cookieUtil.deleteAllCookies();
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

}
