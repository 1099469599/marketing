package bz.sunlight.trigger.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.trigger.BillGenerator;
import bz.sunlight.trigger.BusinessGenerator;
import bz.sunlight.trigger.ConsumeRecordGenerator;

@Controller
@RequestMapping("/generator/")
public class GeneratorController {
	
	@Autowired
	private ConsumeRecordGenerator consumeRecordGenerator;
	@Autowired
	private BusinessGenerator businessGenerator;
	@Autowired
	private BillGenerator billGenerator;
	
	@RequestMapping(value="consumption",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,String> createConsumption(HttpServletResponse response){
		Map<String, String> map = new HashMap<String, String>();
		consumeRecordGenerator.businessQueueHandler();
		map.put("msg", "OK");
		return map;
	}

	@RequestMapping(value="business",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,String> reAddBusinessQueue(HttpServletResponse response){
		Map<String, String> map = new HashMap<String, String>();
		businessGenerator.reAddBusinessQueue();
		map.put("msg", "OK");
		return map;
	}
	
	@RequestMapping(value="bill",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,String> createBill(HttpServletResponse response){
		Map<String, String> map = new HashMap<String, String>();
		billGenerator.generateBill();
		map.put("msg", "OK");
		return map;
	}
}
