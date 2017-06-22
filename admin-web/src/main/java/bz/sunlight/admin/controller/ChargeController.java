package bz.sunlight.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import bz.sunlight.dto.ReceiptRecordDTO;
import bz.sunlight.entity.Enterprise;
import bz.sunlight.entity.ReceiptRecord;
import bz.sunlight.service.AccountService;
import bz.sunlight.service.PayService;
import bz.sunlight.util.BeanUtil;
import bz.sunlight.util.PageUtil;
import bz.sunlight.util.UUIDUtil;

@Controller
@RequestMapping("/admin/")
public class ChargeController {
	
	@Autowired 
	private AccountService accountService;
	@Autowired 
	private PayService payService;
	
	
	@RequestMapping(value="enterprise",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Enterprise>> getAllEnterprise(){
		List<Enterprise> enterprise = accountService.getEnterprise();
		return new ResponseEntity<List<Enterprise>>(enterprise,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="receiptRecord",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> add(@RequestBody ReceiptRecordDTO receiptRecord,
			@CookieValue(HttpConstant.AIM_MARKETING_ADMIN_USER_ID_COOKIE) String userId){
		ReceiptRecord record=new ReceiptRecord();
		BeanUtil.copyProperties(record, receiptRecord);
		record.setType(BaseConstant.CHARGE_TYPE_MANUAL);
		record.setPayTime(new Date());
		record.setId(UUIDUtil.getOrigUUID());
		record.setAdminInfoId(userId);
		payService.addReceiptRecord(record);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@SuppressWarnings(value="unchecked")
	@RequestMapping(value="receiptRecord/{currentPage}/{pageSize}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> get(@PathVariable Integer currentPage,@PathVariable Integer pageSize){
		Map<String,Object> map=new HashMap<String,Object>();
		Integer itemCount = payService.getReciptRecordCountAdmin(BaseConstant.CHARGE_TYPE_MANUAL);
		int pageItemStart = PageUtil.getPageItemStart(currentPage, pageSize);
		int totalPage = PageUtil.getTotalPage(itemCount, pageSize);
		map.put("type", BaseConstant.CHARGE_TYPE_MANUAL);
		map.put("pageItemStart", pageItemStart);
		map.put("pageSize", pageSize);
		List<ReceiptRecord> receiptRecords = payService.getReceiptRecord(map);
		Map<String, Object> recordMap=null;
		if(receiptRecords!=null&receiptRecords.size()>0){
			recordMap=new HashMap<String, Object>();
			recordMap.put("itemCount", itemCount);
			recordMap.put("totalPage", totalPage);
			recordMap.put("receiptRecords", receiptRecords);
		}
		return new ResponseEntity<Map<String, Object>>(recordMap,HttpStatus.OK);
		
	}
	
	
}
