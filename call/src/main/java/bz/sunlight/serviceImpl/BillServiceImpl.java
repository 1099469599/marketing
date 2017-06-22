package bz.sunlight.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bz.sunlight.dao.BillMapper;
import bz.sunlight.entity.Bill;
import bz.sunlight.entity.BillExample;
import bz.sunlight.entity.BillMonth;
import bz.sunlight.entity.BillMonthsVO;
import bz.sunlight.entity.BillVO;
import bz.sunlight.service.BillService;
import bz.sunlight.util.BeanUtil;

@Service("billService")
public class BillServiceImpl implements BillService {

	@Autowired
	private BillMapper billMapper;
	
	@Override
	public List<BillMonthsVO> getMonthBills(String enterpriseId) {
		BillExample billExample = new BillExample();
		billExample.setOrderByClause("bill_date DESC");
		billExample.createCriteria().andEnterpriseIdEqualTo(enterpriseId);
		List<Bill> bills = billMapper.selectByExample(billExample);

		// 先用一个map去接收结果
		Map<String,List<BillMonth>> billsMap = new HashMap<String, List<BillMonth>>();
		List<BillMonth> billMonthList = null;
		for (Bill bill : bills) {
			String billYear = bill.getYear();
			if (billsMap.containsKey(billYear)) {
				billMonthList = billsMap.get(billYear);
				BillMonth billMonth = new BillMonth();
				billMonth.setBillId(bill.getId());
				billMonth.setMonth(bill.getMonth());
				billMonthList.add(billMonth);
			} else {
				billMonthList = new ArrayList<BillMonth>();
				BillMonth billMonth = new BillMonth();
				billMonth.setBillId(bill.getId());
				billMonth.setMonth(bill.getMonth());
				billMonthList.add(billMonth);
				billsMap.put(billYear, billMonthList);
			}
		}

		// 数据迁移
		List<BillMonthsVO> billMonthsVOList = new ArrayList<BillMonthsVO>();
		for (Map.Entry<String, List<BillMonth>> entry : billsMap.entrySet()) {
			BillMonthsVO billMonthsVO = new BillMonthsVO();
			billMonthsVO.setYear(entry.getKey());
			billMonthsVO.setMonths(entry.getValue());
			billMonthsVOList.add(billMonthsVO);
		}

		return billMonthsVOList;
	}

	@Override
	public BillVO getBill(String billId) {
		Bill bill = billMapper.selectByPrimaryKey(billId);
		if (bill != null) {
			BillVO billVO = new BillVO();
			BeanUtil.copyProperties(billVO, bill);
			return billVO;
		} else {
			return null;
		}
	}

	@Override
	public int addBill(Bill bill) {
		return billMapper.insert(bill);
	}
	
}
