package bz.sunlight.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.entity.BusinessRule;
import bz.sunlight.service.RuleService;

@Controller
@RequestMapping("/admin/")
public class RuleController {

	@Autowired
	private RuleService ruleService;
	
	@RequestMapping(value="businessRule",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BusinessRule> get(){
		BusinessRule rule = ruleService.query();
		return new ResponseEntity<BusinessRule>(rule,HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value="businessRule",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> add(@RequestBody BusinessRule businessRule){
		BusinessRule rule = ruleService.query();
		if(rule!=null){
			ruleService.update(businessRule);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		ruleService.add(businessRule);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
