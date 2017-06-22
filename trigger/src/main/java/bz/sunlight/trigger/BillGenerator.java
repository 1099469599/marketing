package bz.sunlight.trigger;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.entity.Bill;
import bz.sunlight.entity.BusinessRule;
import bz.sunlight.entity.CallQuota;
import bz.sunlight.entity.Enterprise;
import bz.sunlight.entity.EnterpriseAccount;
import bz.sunlight.service.AccountService;
import bz.sunlight.service.BillService;
import bz.sunlight.service.PayService;
import bz.sunlight.service.RuleService;
import bz.sunlight.util.DateUtil;
import bz.sunlight.util.UUIDUtil;

@Component
public class BillGenerator {

	@Autowired
	private PayService payService;
	@Autowired
	private RuleService ruleService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BillService billService;
    
	public void generateBill() {
		// 生成结算周期
		Date start = DateUtil.getTimesMonthStart();
		Date end = DateUtil.getTimesMonthEnd();
		
		// 获取本月产生消费记录的企业
		List<String> enterpriseIds = payService.getEnterpriseIdsByTime(start, end);
		for (String enterpriseId : enterpriseIds) {
			generateSingleBill(start, end, enterpriseId);
		}
	}
	
	@Transactional
	public void generateSingleBill(Date start, Date end, String enterpriseId) {
		// 初始化帐单
		Bill bill = new Bill();
		bill.setId(UUIDUtil.getOrigUUID());
		bill.setEnterpriseId(enterpriseId);
		bill.setBillDate(new Date());
		bill.setStartDate(start);
		bill.setEndDate(end);
		String currentYear = String.valueOf(DateUtil.currentYear());
		String currentMonth = String.valueOf(DateUtil.currentMonth());
		bill.setYear(currentYear);
		bill.setMonth(currentMonth);
		
		// 生成帐单 code
		Enterprise enterprise = accountService.getEnterpriseById(enterpriseId);
		String enterpriseCode = enterprise.getCode();
		String formatMonth = String.format("%02d", Integer.valueOf(currentMonth));	// 一位数月份左边补 0
		String billCode = enterpriseCode + currentYear + formatMonth;
		bill.setCode(billCode);

		// 企业对应基本消费、有效通话数量、坐席数量
		CallQuota callQuota = payService.getQuotaByEnterprise(enterpriseId);
		Double baseCost = callQuota.getPrice();
		bill.setBaseCost(baseCost);
		bill.setBaseCallQuantity(callQuota.getCallQuantity());
		bill.setBaseSeatQuantity(callQuota.getSeatQuantity());
		
		// 各项单价
		BusinessRule businessRule = ruleService.query();
		Double callPrice = businessRule.getCallPrice(); 
		Double seatPrice = businessRule.getSeatPrice(); 
		Double keepPrice = businessRule.getKeepPrice(); 
		bill.setCallPrice(callPrice);
		bill.setSeatPrice(seatPrice);
		bill.setKeepPrice(keepPrice);
		
		// 各项消费记录及小计
		Integer seatQuantity = payService.getQuantityByType(enterpriseId, BaseConstant.CONSUME_TYPE_SEAT);
		Integer keepDay = payService.getQuantityByType(enterpriseId, BaseConstant.CONSUME_TYPE_KEEP);
		bill.setSeatQuantity(seatQuantity);
		bill.setKeepDay(keepDay);
		Double seatSubtotal = seatPrice * seatQuantity;
		Double keepSubtotal = keepPrice * keepDay;
		bill.setSeatSubtotal(seatSubtotal);
		bill.setKeepSubtotal(keepSubtotal);
		// 超出额度后把超出部分纳入帐单
		Integer callQuantity = payService.getQuantityByType(enterpriseId, BaseConstant.CONSUME_TYPE_CALL);
		Double callSubtotal = 0.00;
		if (callQuantity > callQuota.getCallQuantity()) {
			Integer subCallQuantity = callQuantity - callQuota.getCallQuantity(); 
			bill.setCallQuantity(subCallQuantity);
			callSubtotal = callPrice * subCallQuantity;
			bill.setCallSubtotal(callSubtotal);
		} else {
			bill.setCallQuantity(0);
			bill.setCallSubtotal(callSubtotal);
		}
		
		// 合计
		Double totalAmount = baseCost + callSubtotal + seatSubtotal + keepSubtotal;
		bill.setTotalAmount(totalAmount);
		
		// 新增帐单
		billService.addBill(bill);
		
		// 企业帐户当月拨打次数清0
		EnterpriseAccount enterpriseAccount = accountService.getEnterpriseAccount(enterpriseId);
		enterpriseAccount.setCalledQuantity(0);
		accountService.putEnterpriseAccount(enterpriseAccount);
	}
	
}
