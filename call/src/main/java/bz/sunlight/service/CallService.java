package bz.sunlight.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import bz.sunlight.dto.CallExcutionDTO;
import bz.sunlight.dto.CallRecordDTO;
import bz.sunlight.dto.KeepSecretDTO;
import bz.sunlight.entity.BusinessQueue;
import bz.sunlight.entity.CallLog;
import bz.sunlight.entity.CallRecord;
import bz.sunlight.entity.Customer;
import bz.sunlight.entity.KeepSecret;
import bz.sunlight.entity.SecretPool;


public interface CallService {

	// 获取有效通话次数
	Integer getValidCallRecord(String enterpriseId);

	// 查找通话记录
	List<CallRecord> getCallRecords(String userId, Short callResult, String languageTrickId, String startTime, String endTime);

	// 分页查找通话记录
	List<CallRecord> getCallRecordsByPagination(CallRecordDTO callRecordDTO);
	
	// 查找通话记录总数
	int getRecordsCount(CallRecordDTO callRecordDTO);
	
//	外呼执行
	void callExcute(String userId,String enterpriseID,CallExcutionDTO callExcutionDTO);
	
	Customer getCustomer(String secretId,String enterpriseId);
	
	int getCustomerCount(String name, String mobile, Date appointmentDate,String enterpriseId);
	
	List<Customer> getCustomer(Integer currentPage,Short pageSize,String name,String mobile,Date appointmentDate,String enterpriseId);
	
//	查找保留区信息
	KeepSecret getKeepSecret(String secretId,String enterpirseId);
	
//	查找保留区是否存在记录
	boolean checkKeepSecret(String secretId);
	
//	查询密号内容
	List<SecretPool> getSecretPool(Short keepStatus,Short status,String brand,String userId,String area,Integer pageItemStart,Integer pageSize,Short callStatus);
	
//	查询密号池条数
	int secretPoolCount(Short keepStatus, Short status,String brand, String userId,String area,Short callStatus);
	
//	更新密号池状态
	void updateSecretPool(SecretPool secretPool);
	
//	查询保留区内容
	List<KeepSecret> getKeepSecret(KeepSecretDTO keepSecretDTO);
//	chaxun 保留区总条数
	int  getKeepSecretCount(KeepSecretDTO keepSecretDTO);
	// 获取未处理的的业务队列
	List<BusinessQueue> getUnhandledBusinessQueue();


	// 更新业务处理状态
	int putBusinessQueue(BusinessQueue businessQueue);

	// 通过主键查保留区记录
	KeepSecret getKeepSecretById(String id);
	
//	新增保留区客户
	KeepSecret keepSecretCustomer(SecretPool secretPool);
	
	// 通过主键查通话记录
	CallRecord getCallRecordById(String id);
	
    void releaseKeepSecret(SecretPool pool,KeepSecret keepSecret);
	/**
	 * 
	 * @param callNum	主叫方号码
	 * @param targetNum	被叫方号码
	 * @param userdata	用户自定义参数(外呼记录 Id)
	 * @return 请求成功返回 true,否则返回 false
	 */
	boolean calling(String callNum, String targetNum, String secretId, String userId);

	// 外呼回调
	Map<String,String> callStateUrl(JSONObject jsonObject);
	Map<String,String> hangupCdrUrl(JSONObject jsonObject);
	
	// 新增外呼日志
	void addCallLog();
	
	// 通过主键查外呼日志
	CallLog getCallLogById(String id);
	
	// 更新外呼记录的三个时间
	void updateCallRecord();
	
	// 根据起止时间获取保留区密号
	List<KeepSecret> getKeepSecretByTime(Date start, Date end);	
	
	// 将新增任务队列做成公共方法
	void saveBusinessQueue(String businessId,Short type);
}
