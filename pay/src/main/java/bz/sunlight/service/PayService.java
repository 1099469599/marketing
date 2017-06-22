package bz.sunlight.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import bz.sunlight.entity.CallQuota;
import bz.sunlight.entity.ConsumeRecord;
import bz.sunlight.entity.GiftPackage;
import bz.sunlight.entity.ReceiptRecord;




public interface PayService {
	
	// 获取帐户信息
	CallQuota getQuotaByEnterprise(String enterpriseId);
	
//	多条件查询消费记录
	List<ConsumeRecord> getConsumeRecord(Short type,Date startConsumeTime,
			Date endConsumeTime,Integer pageItemStart,Integer pageSize,String enterpriseId);

	
//	查询充值记录
	List<ReceiptRecord> getReceiptRecord(Short type,Date startPayTime,
			Date endPayTime,Integer pageItemStart,Integer pageSize,String enterpriseId);
	
	List<ReceiptRecord> getReceiptRecord(Map map);

//	获取客户充值记录
	Integer getReciptRecordCount(Short type,Date startPayTime,
			Date endPayTime,String enterpriseId);
	
//	查询管理员充值记录
	Integer getReciptRecordCountAdmin(Short type);
	
//	新增充值记录  同时更新企业账户余额
	void addReceiptRecord(ReceiptRecord receiptRecord);
	
//	消费记录条数
	int selectBySelectiveCount(Short type,Date startConsumeTime,Date endConsumeTime,String enterpriseId);
	
	// 获取指定日期范围内产生消费记录的企业
	List<String> getEnterpriseIdsByTime(Date start, Date end);
	
	// 获取企业各项消费记录
	Integer getQuantityByType(String enterpriseId, Short type);
	
	// 新境消费记录
	int addConsumeRecord(ConsumeRecord record);
	
	GiftPackage getGiftPackageByAmount(Double amount,Short status);
}
