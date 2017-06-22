package bz.sunlight.serviceImpl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.dao.CallQuotaMapper;
import bz.sunlight.dao.ConsumeRecordMapper;
import bz.sunlight.dao.EnterpriseAccountMapper;
import bz.sunlight.dao.GiftPackageMapper;
import bz.sunlight.dao.ReceiptRecordMapper;
import bz.sunlight.entity.CallQuota;
import bz.sunlight.entity.CallQuotaExample;
import bz.sunlight.entity.ConsumeRecord;
import bz.sunlight.entity.ConsumeRecordExample;
import bz.sunlight.entity.EnterpriseAccount;
import bz.sunlight.entity.EnterpriseAccountExample;
import bz.sunlight.entity.GiftPackage;
import bz.sunlight.entity.GiftPackageExample;
import bz.sunlight.entity.ReceiptRecord;
import bz.sunlight.entity.ReceiptRecordExample;
import bz.sunlight.service.AccountService;
import bz.sunlight.service.PayService;

@Service("payService")
public class PayServiceImpl implements PayService {

	@Resource
	private CallQuotaMapper callQuotaMapper;
	
	@Resource
	private ConsumeRecordMapper consumeRecordMapper;
	
	@Resource
	private ReceiptRecordMapper receiptRecordMapper;
	
	@Autowired
	private AccountService accountService;
	
	@Resource
	private GiftPackageMapper giftPackageMapper;
	
	@Override
	public CallQuota getQuotaByEnterprise(String enterpriseId) {
		CallQuotaExample example = new CallQuotaExample();
		example.createCriteria().andEnterpriseIdEqualTo(enterpriseId);
		List<CallQuota> callQuotas = callQuotaMapper.selectByExample(example);
		if (callQuotas != null && callQuotas.size() != 0) {
			return callQuotas.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ConsumeRecord> getConsumeRecord(Short type,Date startConsumeTime,
			Date endConsumeTime,Integer pageItemStart,Integer pageSize,String enterpriseId) {
		// TODO Auto-generated method stub
//		ConsumeRecordExample example=new ConsumeRecordExample();
//		example.createCriteria().andTypeEqualTo(type).andConsumeTimeBetween(startConsumeTime, endConsumeTime);
//		return consumeRecordMapper.selectByExample(example);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("type", type);
		map.put("startConsumeTime", startConsumeTime);
		map.put("endConsumeTime", endConsumeTime);
		map.put("pageItemStart", pageItemStart);
		map.put("pageSize", pageSize);
		map.put("enterpriseId", enterpriseId);
		return consumeRecordMapper.selectBySelective(map);
	}

	@Override
	public List<ReceiptRecord> getReceiptRecord(Short type,Date startPayTime,Date endPayTime,
			Integer pageItemStart,Integer pageSize,String enterpriseId ) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("type", type);
		map.put("startPayTime", startPayTime);
		map.put("endPayTime", endPayTime);
		map.put("pageItemStart", pageItemStart);
		map.put("pageSize", pageSize);
		map.put("enterpriseId", enterpriseId);
		return receiptRecordMapper.selectReceiptWithUserAndEnterprise(map);
	}

	@Transactional
	@Override
	public void addReceiptRecord(ReceiptRecord receiptRecord) {
		List<Double> amounts = giftPackageMapper.getGiftPackage(BaseConstant.BASE_TRUE);
		EnterpriseAccount enterpriseAccount = accountService.getEnterpriseAccount(receiptRecord.getEnterpriseId());
		if(enterpriseAccount==null){
//		    	TODO :抛出不存在该账户异常
			throw new RuntimeException("账户不存在");
		}
		Double accountBalance=enterpriseAccount.getAccountBalance();
		Double account=receiptRecord.getAccount();
		if(accountBalance==null){
			accountBalance=0.00;
		}
		if(account==null){
			account=0.00;
		}
		GiftPackage giftPackage =null;
		double giftAccount=0;
		if(amounts.contains(account)){
			//根据充值金额查出套餐种类
			giftPackage=getGiftPackageByAmount(account, BaseConstant.BASE_TRUE);
			accountBalance=accountBalance+account+giftPackage.getGiftAmount();
			giftAccount=giftPackage.getGiftAmount();
		}else{
//			构造新的集合
			amounts.add(account);
			Collections.sort(amounts);
			int indexOf = amounts.indexOf(account);
			if(indexOf==0){
//				直接充值金额
				accountBalance=accountBalance+account;
			}else{
//			充值时加上赠送金额
				giftPackage = getGiftPackageByAmount(amounts.get(indexOf-1), BaseConstant.BASE_TRUE);
				accountBalance=accountBalance+account+giftPackage.getGiftAmount();
				giftAccount=giftPackage.getGiftAmount();
			}
		}
		enterpriseAccount.setAccountBalance(accountBalance);
		accountService.putEnterpriseAccount(enterpriseAccount);
		receiptRecord.setCurrentAccount(enterpriseAccount.getAccountBalance());
		receiptRecord.setGiftAccount(giftAccount);
		receiptRecordMapper.insert(receiptRecord);
	}

	@Override
	public List<ReceiptRecord> getReceiptRecord(Map map) {
		// TODO Auto-generated method stub
		return receiptRecordMapper.selectReceiptWithAdmin(map);
	}

	@Override
	public Integer getReciptRecordCount(Short type,Date startPayTime,
			Date endPayTime,String enterpriseId) {
		// TODO Auto-generated method stub
		return receiptRecordMapper.getReciptRecordCount(type,startPayTime,endPayTime,enterpriseId);
	}

	@Override
	public Integer getReciptRecordCountAdmin(Short type) {
		// TODO Auto-generated method stub
		
		return receiptRecordMapper.selectAdminReceiptCount(type);
	}

	@Override
	public int selectBySelectiveCount(Short type,Date startConsumeTime,Date endConsumeTime,String enterpriseId) {
		// TODO Auto-generated method stub
		return consumeRecordMapper.selectBySelectiveCount(type,startConsumeTime,endConsumeTime,enterpriseId);
	}

	@Override
	public List<String> getEnterpriseIdsByTime(Date start, Date end) {
		return consumeRecordMapper.selectEnterpriseByTime(start, end);
	}

	@Override
	public Integer getQuantityByType(String enterpriseId, Short type) {
		ConsumeRecordExample consumeRecordExample = new ConsumeRecordExample();
		consumeRecordExample.createCriteria().andEnterpriseIdEqualTo(enterpriseId).andTypeEqualTo(type);
		return consumeRecordMapper.countByExample(consumeRecordExample);
	}

	@Override
	public int addConsumeRecord(ConsumeRecord record) {
		return consumeRecordMapper.insert(record);
	}

	@Override
	public GiftPackage getGiftPackageByAmount(Double amount, Short status) {
		GiftPackageExample example=new GiftPackageExample();
		example.createCriteria().andAmountEqualTo(amount).andStatusEqualTo(status);
		List<GiftPackage> giftPackages = giftPackageMapper.selectByExample(example);
		if(giftPackages!=null&&giftPackages.size()>0){
			return giftPackages.get(0);
		}
		return null;
	}
	
	

}
