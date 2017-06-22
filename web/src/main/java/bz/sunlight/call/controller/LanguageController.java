package bz.sunlight.call.controller;

import java.util.List;

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
import bz.sunlight.dto.LanguageTrickDTO;
import bz.sunlight.entity.DefaultLangugeTrick;
import bz.sunlight.entity.LanguageTrick;
import bz.sunlight.service.LanguageService;
import bz.sunlight.util.BeanUtil;
import bz.sunlight.util.UUIDUtil;

@Controller
@RequestMapping("/call/")
public class LanguageController {

	@Autowired
	private LanguageService languageService;
	
	@RequestMapping(value="language",method=RequestMethod.POST)
	@ResponseBody
	public void add(@RequestBody LanguageTrickDTO languageTrickDTO,@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId,
			@CookieValue(HttpConstant.AIM_MARKETING_USERID_COOKIE) String userId){
		LanguageTrick trick=new LanguageTrick();
		BeanUtil.copyProperties(trick, languageTrickDTO);
		trick.setId(UUIDUtil.getOrigUUID());
		trick.setEnterpriseId(enterpriseId);
		trick.setStatus(BaseConstant.BASE_TRUE);
		languageService.add(trick);
	}
	
	@RequestMapping(value="language",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<LanguageTrick>> query(@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId){
		List<LanguageTrick> trick = languageService.select(enterpriseId,BaseConstant.BASE_TRUE);
		return new ResponseEntity<List<LanguageTrick>>(trick,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="language/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> delete(@PathVariable("id") String id){
		LanguageTrick trick = languageService.selectById(id, BaseConstant.BASE_TRUE);
		if(trick!=null){
			trick.setStatus(BaseConstant.BASE_FALSE);
			languageService.delete(trick);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="language",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Void> update(@RequestBody LanguageTrick languageTrick){
		languageService.update(languageTrick);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
//	点击设置默认时触发的方法
	@RequestMapping(value="language/default",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Void> addDefault(@RequestBody LanguageTrick languageTrick,@CookieValue(HttpConstant.AIM_MARKETING_USERID_COOKIE) String userId){
		 DefaultLangugeTrick defaultLangugeTrick = languageService.selectDefault(userId);
		if(defaultLangugeTrick!=null){
			defaultLangugeTrick.setLanguageTrickId(languageTrick.getId());
			languageService.updateDefault(defaultLangugeTrick);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		defaultLangugeTrick=new DefaultLangugeTrick();
		defaultLangugeTrick.setId(UUIDUtil.getOrigUUID());
		defaultLangugeTrick.setLanguageTrickId(languageTrick.getId());
		defaultLangugeTrick.setUserInfoId(userId);
		languageService.addDefault(defaultLangugeTrick);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value="language/default",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<LanguageTrick> queryDefault(@CookieValue(HttpConstant.AIM_MARKETING_USERID_COOKIE) String userId){
		 DefaultLangugeTrick trick = languageService.selectDefault(userId);
		 LanguageTrick languageTrick=null;
		 if(trick!=null){
			languageTrick = languageService.selectById(trick.getLanguageTrickId(), BaseConstant.BASE_TRUE);
		}
		 return new ResponseEntity<LanguageTrick>(languageTrick,HttpStatus.OK);
		
	}
}
