package bz.sunlight.pay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.constant.HttpConstant;
import bz.sunlight.entity.BillMonthsVO;
import bz.sunlight.entity.BillVO;
import bz.sunlight.service.BillService;

@Controller
@RequestMapping("/bill/")
public class BillController {
	
	@Autowired
	private BillService billService;
	
	@RequestMapping(value="monthBills",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<BillMonthsVO>> getMonthBills(@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId){
		List<BillMonthsVO> billMonthsVOList = billService.getMonthBills(enterpriseId);
		return new ResponseEntity<List<BillMonthsVO>>(billMonthsVOList, HttpStatus.OK);
	}
	
	@RequestMapping(value="{billId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BillVO> getBill(@PathVariable("billId") String billId){
		BillVO billVO = billService.getBill(billId);
		return new ResponseEntity<BillVO>(billVO, HttpStatus.OK);
	}
	
}
