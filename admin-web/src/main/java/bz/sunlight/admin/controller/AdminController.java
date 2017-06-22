package bz.sunlight.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.constant.HttpConstant;
import bz.sunlight.entity.AdminInfo;
import bz.sunlight.service.AdminService;
import bz.sunlight.util.CookieUtil;
import bz.sunlight.util.Md5Util;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> login(@RequestBody AdminInfo adminInfo,
			HttpServletRequest request,HttpServletResponse response){
		String md5Decode = Md5Util.parseStrToMd5L32(adminInfo.getPassword());
		AdminInfo admin = adminService.get(adminInfo.getUserName(), md5Decode, BaseConstant.BASE_TRUE);
		if(admin!=null){
			CookieUtil cookieUtil = new CookieUtil(request, response);
			cookieUtil.addCookie(HttpConstant.AIM_MARKETING_ADMIN_LOGIN_COOKIE, Md5Util.parseStrToMd5L32(adminInfo.getPassword()+adminInfo.getUserName()));
			cookieUtil.addCookie(HttpConstant.AIM_MARKETING_ADMIN_USER_ID_COOKIE, admin.getId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="check",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Void> check(@CookieValue(HttpConstant.AIM_MARKETING_ADMIN_LOGIN_COOKIE) String adminCookie
			){
		if(adminCookie!=null&&adminCookie.equals(Md5Util.parseStrToMd5L32(BaseConstant.ADMIN_PASSWORD+BaseConstant.ADMIN_USER_NAME))){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="logout",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response){
		CookieUtil cookieUtil=new CookieUtil(request, response);
		cookieUtil.deleteCookie(HttpConstant.AIM_MARKETING_ADMIN_LOGIN_COOKIE);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="userInfo",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<AdminInfo> getUserInfo(@CookieValue(HttpConstant.AIM_MARKETING_ADMIN_USER_ID_COOKIE) String userId){
		AdminInfo adminInfo = adminService.get(userId, BaseConstant.BASE_TRUE);
		return new ResponseEntity<AdminInfo>(adminInfo,HttpStatus.OK);
	
		
	}
	
}
