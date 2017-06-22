package bz.sunlight.pay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.constant.HttpConstant;
import bz.sunlight.entity.ConsumeRecord;
import bz.sunlight.entity.ReceiptRecord;
import bz.sunlight.entity.UserInfo;
import bz.sunlight.entity.UserInfoBO;
import bz.sunlight.service.AccountService;
import bz.sunlight.service.PayService;
import bz.sunlight.util.PageUtil;
import bz.sunlight.util.PropertiesUtil;

@Controller
@RequestMapping("/pay/")
public class PayController {

	
	@Autowired
	private PayService payService;
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="consumeRecord",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String,Object>> get(Short type,Date startConsumeTime,Date endConsumeTime,Integer currentPage,
			Integer pageSize,@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId){
		Map<String,Object> recordMap=new HashMap<String, Object>();
		int itemCount=payService.selectBySelectiveCount(type, startConsumeTime, endConsumeTime,enterpriseId);
		int pageItemStart = PageUtil.getPageItemStart(currentPage, pageSize);
		int totalPage = PageUtil.getTotalPage(itemCount, pageSize);
		List<Object> list=new ArrayList<Object>();
		List<ConsumeRecord> consumeRecords = payService.getConsumeRecord(type, startConsumeTime,endConsumeTime,pageItemStart,pageSize,enterpriseId);
		if(consumeRecords!=null&&consumeRecords.size()>0){
			Map map=null;
			for (ConsumeRecord consumeRecord : consumeRecords) {
				  map=new HashMap<String, Object>();
				  UserInfo userInfo = accountService.getUserInfo(consumeRecord.getUserInfoId());
				  map.put("userInfo", userInfo);
				  map.put("consumeRecord", consumeRecord);
				  list.add(map);
			}
		}
		recordMap.put("itemCount", itemCount);
		recordMap.put("totalPage", totalPage);
		recordMap.put("consumeRecords", list);
		return new ResponseEntity<Map<String,Object>>(recordMap,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="receiptRecord",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getReceiptRecord(Short type,Date startPayTime,Date endPayTime,
			Integer currentPage,Integer pageSize,@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId){
		Map<String, Object> map=null;
		Integer itemCount = payService.getReciptRecordCount(type, startPayTime, endPayTime,enterpriseId);
		int pageItemStart = PageUtil.getPageItemStart(currentPage, pageSize);
		int totalPage = PageUtil.getTotalPage(itemCount, pageSize);
		List<ReceiptRecord> receiptRecords = payService.getReceiptRecord(type, startPayTime,
				endPayTime,pageItemStart,pageSize,enterpriseId);
		if(receiptRecords!=null&&receiptRecords.size()>0){
			map=new HashMap<String, Object>();
			map.put("itemCount", itemCount);
			map.put("totalPage", totalPage);
			map.put("receiptRecords", receiptRecords);
		}
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		
	}
	
}
