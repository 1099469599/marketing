package bz.sunlight.service;

import java.util.List;

import bz.sunlight.entity.Bill;
import bz.sunlight.entity.BillMonthsVO;
import bz.sunlight.entity.BillVO;



public interface BillService {

	// 获取某企业各年月帐单
	List<BillMonthsVO> getMonthBills(String enterpriseId);

	// 根据 id 获取帐单详情
	BillVO getBill(String billId);
	
	// 新增帐单
	int addBill(Bill bill);
}
