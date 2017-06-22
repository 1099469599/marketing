package bz.sunlight.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.dao.BusinessQueueMapper;
import bz.sunlight.dao.CallLogMapper;
import bz.sunlight.dao.CallRecordMapper;
import bz.sunlight.dao.CallResponseMsgMapper;
import bz.sunlight.dao.CallStateMsgMapper;
import bz.sunlight.dao.CallerRecordMsgMapper;
import bz.sunlight.dao.CustomerMapper;
import bz.sunlight.dao.KeepSecretMapper;
import bz.sunlight.dao.SecretPoolMapper;
import bz.sunlight.dto.CallExcutionDTO;
import bz.sunlight.dto.CallRecordDTO;
import bz.sunlight.dto.KeepSecretDTO;
import bz.sunlight.entity.BusinessQueue;
import bz.sunlight.entity.BusinessQueueExample;
import bz.sunlight.entity.CallLog;
import bz.sunlight.entity.CallLogExample;
import bz.sunlight.entity.CallRecord;
import bz.sunlight.entity.CallRecordExample;
import bz.sunlight.entity.CallResponseMsg;
import bz.sunlight.entity.CallResponseMsgExample;
import bz.sunlight.entity.CallStateMsg;
import bz.sunlight.entity.CallStateMsgExample;
import bz.sunlight.entity.CallerRecordMsg;
import bz.sunlight.entity.CallerRecordMsgExample;
import bz.sunlight.entity.Customer;
import bz.sunlight.entity.CustomerExample;
import bz.sunlight.entity.KeepSecret;
import bz.sunlight.entity.KeepSecretExample;
import bz.sunlight.entity.SecretPool;
import bz.sunlight.entity.SecretPoolExample;
import bz.sunlight.service.CallService;
import bz.sunlight.util.BeanUtil;
import bz.sunlight.util.DateUtil;
import bz.sunlight.util.HttpUtil;
import bz.sunlight.util.PageUtil;
import bz.sunlight.util.PropertiesUtil;
import bz.sunlight.util.UUIDUtil;

@Service("callService")
public class CallServiceImpl implements CallService {

	@Autowired
	private CallRecordMapper callRecordMapper; 
	
	@Resource
	private CustomerMapper customerMapper;
	
	@Resource
	private SecretPoolMapper secretPoolMapper;
	
	@Resource
	private KeepSecretMapper keepSecretMapper;
	
	@Resource
	private BusinessQueueMapper businessQueueMapper;
	
		
	@Autowired
	private CallResponseMsgMapper callResponseMsgMapper;
	@Autowired
	private CallStateMsgMapper callStateMsgMapper;
	@Autowired
	private CallerRecordMsgMapper callerRecordMsgMapper;
	@Autowired
	private CallLogMapper callLogMapper;
	
	private static Logger logger = LoggerFactory.getLogger(CallServiceImpl.class);
	
	@Override
	public Integer getValidCallRecord(String enterpriseId) {
		CallRecordExample example = new CallRecordExample();
		example.createCriteria().andEnterpriseIdEqualTo(enterpriseId).andStatusEqualTo(BaseConstant.BASE_TRUE);
		return callRecordMapper.countByExample(example);
	}

	@Override
	public List<CallRecord> getCallRecords(String userId, Short callResult, String languageTrickId,
			String startTime, String endTime) {
		CallRecordExample callRecordExample = new CallRecordExample();
		CallRecordExample.Criteria criteria = callRecordExample.createCriteria().andUserInfoIdEqualTo(userId);
		if (callResult != null) {
			criteria.andCallResultEqualTo(callResult);
		}
		if (StringUtils.isNotBlank(languageTrickId)) {
			criteria.andLanguageTrickIdEqualTo(languageTrickId);
		}
		if (StringUtils.isNotBlank(startTime)) {
			Date start = DateUtil.stringToDate(startTime, BaseConstant.DATE_FORMAT_YEAR_TO_SECOND);
			criteria.andCallTimeGreaterThanOrEqualTo(start);
		}
		if (StringUtils.isNotBlank(endTime)) {
			Date end = DateUtil.stringToDate(endTime, BaseConstant.DATE_FORMAT_YEAR_TO_SECOND);
			criteria.andCallTimeLessThanOrEqualTo(end);
		}
		List<CallRecord> callRecords = callRecordMapper.selectByExample(callRecordExample);
		
		return callRecords;
	}

	@Override
	public List<CallRecord> getCallRecordsByPagination(CallRecordDTO callRecordDTO) {
		int pageItemStart = PageUtil.getPageItemStart(callRecordDTO.getCurrentPage(), callRecordDTO.getPageSize());
		return callRecordMapper.selectByPagination(callRecordDTO, pageItemStart);
	}

	@Override
	public int getRecordsCount(CallRecordDTO callRecordDTO) {
		return callRecordMapper.getRecordsCount(callRecordDTO);
	}


	@Override
	public Customer getCustomer(String secretId, String enterpriseId) {
		CustomerExample example=new CustomerExample();
		example.createCriteria().andSecretIdEqualTo(secretId).andEnterpriseIdEqualTo(enterpriseId);
		List<Customer> customers = customerMapper.selectByExample(example);
		if(customers!=null&&customers.size()>0){
			return customers.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void callExcute(String userId, String enterpriseId,
			CallExcutionDTO callExcutionDTO) {
		
		CallRecord record=new CallRecord();
		if(StringUtils.isBlank(callExcutionDTO.getSessionId())){
			callExcutionDTO.setSessionId(null);
		}
		BeanUtil.copyProperties(record, callExcutionDTO);
		record.setId(UUIDUtil.getOrigUUID());
		record.setUserInfoId(userId);
		record.setEnterpriseId(enterpriseId);
		record.setStatus(BaseConstant.BASE_TRUE);
		record.setCreateTime(new Date());
		callRecordMapper.insert(record);
		Customer customer=getCustomer(callExcutionDTO.getSecretId(), enterpriseId);
		if(customer!=null){
			BeanUtil.copyProperties(customer, callExcutionDTO);
			customer.setId(callExcutionDTO.getCustomerId());
			customer.setRemark(callExcutionDTO.getCustomerRemark());
			customerMapper.updateByPrimaryKeySelective(customer);
		}else{
			if(StringUtils.isNotBlank(callExcutionDTO.getName())){
				customer=new Customer();
				BeanUtil.copyProperties(customer, callExcutionDTO);
				customer.setId(UUIDUtil.getOrigUUID());
				customer.setRemark(callExcutionDTO.getCustomerRemark());
				customer.setUserInfoId(userId);
				customer.setEnterpriseId(enterpriseId);
				customerMapper.insert(customer);
			}
		}
//		在公共区打电话
		KeepSecret keepSecret=null;
//		TODO: 关联到通话记录id
		if(callExcutionDTO.getKeepStatus()!=null){
			SecretPool secretPool=new SecretPool();
			secretPool.setId(callExcutionDTO.getSecretPoolId());
			secretPool.setLastDialTime(new Date());
			secretPoolMapper.updateByPrimaryKeySelective(secretPool);
			if(callExcutionDTO.getKeepStatus()==BaseConstant.KEEP_STATUS_PRIVATE){
				if(checkKeepSecret(callExcutionDTO.getSecretId())){
					secretPool.setSecretId(callExcutionDTO.getSecretId());
					secretPool.setKeepStatus(BaseConstant.KEEP_STATUS_PRIVATE);
					updateSecretPool(secretPool);
//			新增一条保留区信息
					keepSecret=new KeepSecret();
					keepSecret.setId(UUIDUtil.getOrigUUID());
					keepSecret.setSecretId(callExcutionDTO.getSecretId());
					keepSecret.setEnterpirseId(enterpriseId);
					keepSecret.setUserInfoId(userId);
					keepSecret.setKeepTime(new Date());
					keepSecret.setCustomPropertiesId1(callExcutionDTO.getKeepCustomPropertiesId1());
					keepSecret.setCustomPropertiesId2(callExcutionDTO.getKeepCustomPropertiesId2());
					keepSecret.setCustomProperties3(callExcutionDTO.getKeepCustomProperties3());
					keepSecretMapper.insert(keepSecret);
//				新增一个业务队列
					saveBusinessQueue(keepSecret.getId(), BaseConstant.CONSUME_TYPE_KEEP);
				}
			}
		}else{
//			在保留区拨打
			keepSecret = getKeepSecret(callExcutionDTO.getSecretId(), enterpriseId);
			if(keepSecret!=null){
				keepSecret.setCustomPropertiesId1(callExcutionDTO.getKeepCustomPropertiesId1());
				keepSecret.setCustomPropertiesId2(callExcutionDTO.getKeepCustomPropertiesId2());
				keepSecret.setCustomProperties3(callExcutionDTO.getKeepCustomProperties3());
				keepSecretMapper.updateByPrimaryKeySelective(keepSecret);
			}
		}
		
		
		
	}

	@Override
	public KeepSecret getKeepSecret(String secretId, String enterpriseId) {
		// TODO Auto-generated method stub
		KeepSecretExample example=new KeepSecretExample();
		example.createCriteria().andSecretIdEqualTo(secretId).andEnterpirseIdEqualTo(enterpriseId).andFreedTimeIsNull();
		List<KeepSecret> keepSecrets = keepSecretMapper.selectByExample(example);
		if(keepSecrets!=null&&keepSecrets.size()>0){
			return keepSecrets.get(0);
		}
		return null;
	}

	@Override
	public List<SecretPool> getSecretPool(Short keepStatus,Short status,String brand,String userId,
			String area,Integer pageItemStart,Integer pageSize,Short callStatus) {
		// TODO Auto-generated method stub
		return secretPoolMapper.getSecretPool(keepStatus, status, brand,userId, area, pageItemStart, pageSize,callStatus);
	}

	@Override
	public List<KeepSecret> getKeepSecret(KeepSecretDTO keepSecretDTO) {
		List<KeepSecret> keepSecretList=null;
		List<KeepSecret> keepSecrets = keepSecretMapper.getKeepSecret(keepSecretDTO);
		if(keepSecrets!=null&&keepSecrets.size()>0){
			keepSecretList=new ArrayList<KeepSecret>();
			for (KeepSecret keepSecret : keepSecrets) {
				Date keepTime = keepSecret.getKeepTime();
				keepTime=DateUtil.dateToDate(keepTime);
				keepSecret.setKeepDay((short)(DateUtil.getDiffDays(keepTime, new Date())+1));
				keepSecretList.add(keepSecret);
			}
		}
		return keepSecretList;
		
	}
	
	

	@Override
	public int getKeepSecretCount(KeepSecretDTO keepSecretDTO) {
		
		return keepSecretMapper.getKeepSecretCount(keepSecretDTO);
	}

	@Override
	public void saveBusinessQueue(String businessId,Short type){
		BusinessQueue queue=new BusinessQueue();
		queue.setId(UUIDUtil.getOrigUUID());
		queue.setBusinessId(businessId);
		queue.setType(type);
		queue.setHandleStatus(BaseConstant.HANDLE_STATUS_NO);
		queue.setCreateTime(new Date());
		businessQueueMapper.insert(queue);
	}

	@Override
	public List<Customer> getCustomer(Integer pageItemStart, Short pageSize,
			String name, String mobile, Date appointmentDate,String enterpriseId) {
		
		return customerMapper.getCustomer(name, mobile, appointmentDate, pageItemStart, pageSize,enterpriseId);
	}

	@Override
	public int getCustomerCount(String name, String mobile, Date appointmentDate,String enterpriseId) {
		
		return customerMapper.getCustomerCount(name, mobile, appointmentDate, enterpriseId);
	}

	
	@Override
	public List<BusinessQueue> getUnhandledBusinessQueue() {
		BusinessQueueExample businessQueueExample = new BusinessQueueExample();
		businessQueueExample.createCriteria().andHandleStatusEqualTo(BaseConstant.HANDLE_STATUS_NO);
		List<BusinessQueue> businessQueue = businessQueueMapper.selectByExample(businessQueueExample);
		return businessQueue;
	}

	@Override
	public KeepSecret getKeepSecretById(String id) {
		return keepSecretMapper.selectByPrimaryKey(id);
	}

	@Override
	public int putBusinessQueue(BusinessQueue businessQueue) {
		return businessQueueMapper.updateByPrimaryKeySelective(businessQueue);
	}

	@Override
	public CallRecord getCallRecordById(String id) {
		return callRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean calling(String callNum, String targetNum, String enterpriseId, String userId) {
		// 确定两个回调接口
		String hangupCdrUrl = PropertiesUtil.getInstanse().getStringByProperties("aim_marketing_hangupCdrUrl", "/server.properties");
		String callStateUrl = PropertiesUtil.getInstanse().getStringByProperties("aim_marketing_callStateUrl", "/server.properties");
		
		// 生成目标 url,实际上这里再将 sevretId 入到 userData 已经没什么意义了
		String uriAPI = String.format("http://118.89.201.20/bsyinterfaces?"
        		+ "EVENT=SupBackCall&orgidentity=weilan&pwdtype=md5&password=0b6866587d8dcefad1b27ac8a0ffa755&user=weilan0&icallerid=96999724&xcallerid=96999724"
        		+ "&exten=%s&targetdn=%s&userdata=%s&hangupCdrUrl=%s&callStateUrl=%s",
        		callNum, targetNum, enterpriseId, hangupCdrUrl, callStateUrl);  
		
		// 执行呼叫
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uriAPI);  
        CloseableHttpResponse response = null;
        String responseText = "";
        try {
        	response = client.execute(httpGet);
        	if (response.getStatusLine().getStatusCode() == 200) {  
        		responseText = EntityUtils.toString(response.getEntity());
        		JSONObject jsonObject = JSONObject.fromObject(responseText);
        		int code = jsonObject.getInt("code");
        		if (code == 1) {
        			// 新增外呼响应消息记录
        			CallResponseMsg msg = new CallResponseMsg();
        			msg.setId(UUIDUtil.getOrigUUID());
        			msg.setCreateTime(new Date());
        			msg.setSecretId(targetNum);
        			msg.setUserInfoId(userId);
        			msg.setSessionId(jsonObject.getString("sessionid"));
        			callResponseMsgMapper.insertSelective(msg);

        			return true;
        		} else {
        			return false;
        		}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
	}

	@Override
	public Map<String,String> callStateUrl(JSONObject jsonObject) {
		// 解析状态消息
		String sessionId = null;
		String status = null;
		try {
			sessionId = jsonObject.getString("sessionid");
			status = jsonObject.getString("status");
		} catch (JSONException e) {
			e.printStackTrace();
			return getOKResponse();
		}

		// 保存实时状态消息
		CallStateMsg msg = new CallStateMsg();
		msg.setId(UUIDUtil.getOrigUUID());
		msg.setCreateTime(new Date());
		msg.setSessionId(sessionId);
		msg.setMessage(jsonObject.toString());
		callStateMsgMapper.insert(msg);

		String secretId = null;
		String userId = null;

		// 初始化推送地址
		String callStateURI = null;
		String callStateRoot = PropertiesUtil.getInstanse().getStringByProperties("aim_marketing_callstate", "/server.properties");
		if (status.equals(BaseConstant.CURRENT_CALL_STATE_CALLEE_ANSWERED)) {
			// 被叫应答或挂断时获取 sessionId 绑定信息并将其委托给外部推送服务发给前端
			CallResponseMsg responseMsg = getResponseMsgBySessionId(sessionId);
			if (responseMsg != null) {
				secretId = responseMsg.getSecretId();
				userId = responseMsg.getUserInfoId();
			} else {
				return getOKResponse();
			}

			// 生成应答地址
			callStateURI = String.format(callStateRoot, userId, secretId, sessionId, BaseConstant.CURRENT_CALL_STATE_CALLEE_ANSWERED);
		} else if (status.equals(BaseConstant.CURRENT_CALL_STATE_HANGUP)) {
			// 被叫应答或挂断时获取 sessionId 绑定信息并将其委托给外部推送服务发给前端
			CallResponseMsg responseMsg = getResponseMsgBySessionId(sessionId);
			if (responseMsg != null) {
				secretId = responseMsg.getSecretId();
				userId = responseMsg.getUserInfoId();
			} else {
				return getOKResponse();
			}

			// 生成结束通话地址
			callStateURI = String.format(callStateRoot, userId, secretId, sessionId, BaseConstant.CURRENT_CALL_STATE_HANGUP);
		}
		
		// 推送消息
		if (callStateURI != null) {
			boolean isSendSuccess = HttpUtil.sendGetRequest(callStateURI);
			if (!isSendSuccess) {
				logger.debug("推送实时话务状态消息失败！");
			}
		}
		
		return getOKResponse();
	}
	
	@Override
	public Map<String,String> hangupCdrUrl(JSONObject jsonObject) {
		String sessionId = null;
		try {
			JSONObject callerCdr = jsonObject.getJSONObject("callerCdr");
			sessionId = callerCdr.getString("sessionid");
		} catch (JSONException e) {
			e.printStackTrace();
			return getOKResponse();
		}
		
		// 保存呼叫记录消息
		CallerRecordMsg msg = new CallerRecordMsg();
		msg.setId(UUIDUtil.getOrigUUID());
		msg.setCreateTime(new Date());
		msg.setSessionId(sessionId);
		msg.setMessage(jsonObject.toString());
		msg.setHandleStatus(BaseConstant.HANDLE_STATUS_NO);
		callerRecordMsgMapper.insert(msg);
		
		return getOKResponse();
	}
	
	private Map<String,String> getOKResponse() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "OK");
		return map;
	}
	
	private CallResponseMsg getResponseMsgBySessionId(String sessionId) {
		CallResponseMsgExample example = new CallResponseMsgExample();
		example.createCriteria().andSessionIdEqualTo(sessionId);
		List<CallResponseMsg> responseMsgs = callResponseMsgMapper.selectByExample(example);
		if (responseMsgs.size() != 0) {
			return responseMsgs.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public CallLog getCallLogById(String id) {
		return callLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public void addCallLog() {
		// 获取待处理呼叫记录消息
		CallerRecordMsgExample callerRecordMsgExample = new CallerRecordMsgExample();
		callerRecordMsgExample.createCriteria().andHandleStatusEqualTo(BaseConstant.HANDLE_STATUS_NO);
		List<CallerRecordMsg> callerRecordMsgs = callerRecordMsgMapper.selectByExampleWithBLOBs(callerRecordMsgExample);
		
		// 生成相应外呼日志
		for (CallerRecordMsg callerRecordMsg : callerRecordMsgs) {
			addSingleCallLog(callerRecordMsg);
		}

	}

	@Transactional
	public void addSingleCallLog(CallerRecordMsg callerRecordMsg) {
		JSONObject msgObject = JSONObject.fromObject(callerRecordMsg.getMessage());
		String sessionId = callerRecordMsg.getSessionId();
		
		// 如果消息体中连主叫信息都拿不到则直接开始下一条消息的处理
		JSONObject callerCdr = null;
		try {
			callerCdr = msgObject.getJSONObject("callerCdr");
		} catch (JSONException e) {
			return;
		}
		
		// 如果没能找到相应的外呼响应消息，则不对当前记录作处理直接进行下一条 (有可能拨打环节出了问题)
		String secretId = null;
		String userId = null;
		CallResponseMsg responseMsg = getResponseMsgBySessionId(sessionId);
		if (responseMsg != null) {
			secretId = responseMsg.getSecretId();
			userId = responseMsg.getUserInfoId();
		} else {
			return;
		}
		
		// 初始化单条记录
		CallLog callLog = new CallLog();
		callLog.setId(UUIDUtil.getOrigUUID());
		callLog.setSecretId(secretId);
		callLog.setUserInfoId(userId);
		callLog.setSessionId(sessionId);
		callLog.setEnterpriseId((String)msgObject.get("userdata"));
		
		// 录入三个时间 (有可能结果中没有返回被叫方信息,这时候将接通时间与挂断时间赋为 null)
		Date callTime = null;
		Date answerTime = null;
		Date hangupTime = null;
		try {
			JSONObject calleeObject = msgObject.getJSONObject("calleeCdr");
			callTime = DateUtil.stringToDate(calleeObject.getString("calldate"), BaseConstant.DATE_FORMAT_YEAR_TO_SECOND);
			answerTime = DateUtil.stringToDate(calleeObject.getString("answertime"), BaseConstant.DATE_FORMAT_YEAR_TO_SECOND);
			hangupTime = DateUtil.stringToDate(calleeObject.getString("endtime"), BaseConstant.DATE_FORMAT_YEAR_TO_SECOND);
		} catch (Exception e) {
			callTime = DateUtil.stringToDate(callerCdr.getString("calldate"), BaseConstant.DATE_FORMAT_YEAR_TO_SECOND);
		}
		callLog.setCallTime(callTime);
		callLog.setConnectTime(answerTime);
		callLog.setHangUpTime(hangupTime);
		
		// 录入通话状态
		CallStateMsgExample callStateMsgExample = new CallStateMsgExample();
		callStateMsgExample.createCriteria().andSessionIdEqualTo(sessionId);
		List<CallStateMsg> callStateMsgs = callStateMsgMapper.selectByExample(callStateMsgExample);
		Short callResult = null;
		for (CallStateMsg callStateMsg : callStateMsgs) {
			JSONObject stateObject = JSONObject.fromObject(callStateMsg.getMessage());
			String callStatus = (String)stateObject.get("status");
			if (callStatus.equals(BaseConstant.CURRENT_CALL_STATE_CALLEE_CALLSTATE)) {
				callResult = generateCallStatus(stateObject);
				callLog.setCallStatus(callResult);
				break;
			}
		}
		// 优先取 callee-callstate 的 rec_result ,找不到则用 caller-callstate 的值,这种情况在主叫方未接听的情况下会发生
		if (callResult == null) {
			for (CallStateMsg callStateMsg : callStateMsgs) {
				JSONObject stateObject = JSONObject.fromObject(callStateMsg.getMessage());
				String callStatus = (String)stateObject.get("status");
				if (callStatus.equals(BaseConstant.CURRENT_CALL_STATE_CALLER_CALLSTATE)) {
					callResult = generateCallStatus(stateObject);
					callLog.setCallStatus(callResult);
					break;
				}
			}
		}
		
		// 更新密号池中的通话状态和最近拨打时间
		SecretPoolExample secretPoolExample = new SecretPoolExample();
		secretPoolExample.createCriteria().andSecretIdEqualTo(secretId);
		List<SecretPool> secrets = secretPoolMapper.selectByExample(secretPoolExample);
		if (secrets.size() != 0) {
			SecretPool secretPool = secrets.get(0);
			secretPool.setCallStatus(callResult);
			secretPool.setLastDialTime(callTime);
			secretPoolMapper.updateByPrimaryKeySelective(secretPool);
		}
		
		// 插入日志
		callLogMapper.insertSelective(callLog);
		
		// 新增任务队列 (如果通话状态正常则放入任务队列)
		if (callResult.equals(BaseConstant.CALL_STATUS_ANSWERED)) {
			saveBusinessQueue(callLog.getId(), BaseConstant.CONSUME_TYPE_CALL);
		}
		
		// 更新呼叫记录消息状态
		callerRecordMsg.setHandleStatus(BaseConstant.HANDLE_STATUS_YES);
		callerRecordMsg.setModifyTime(new Date());
		callerRecordMsgMapper.updateByPrimaryKey(callerRecordMsg);
	}
	
	private Short generateCallStatus(JSONObject stateObject) {
		String rec_result = (String)stateObject.get("rec_result");
		// 统一处理异常状态
		try {
			return Short.parseShort(rec_result);
		} catch (Exception e) {
			return BaseConstant.CALL_STATUS_BAD;
		}
	}
	
	@Override
	public void updateCallRecord() {
		// 查找未更新通话时间的有效外呼记录
		CallRecordExample callRecordExample = new CallRecordExample();
		callRecordExample.createCriteria().andSessionIdIsNotNull().andCallTimeIsNull();
		List<CallRecord> callRecords = callRecordMapper.selectByExample(callRecordExample);
		
		for (CallRecord callRecord : callRecords) {
			// 从外呼日志中获取目标会话
			String sessionId = callRecord.getSessionId();
			CallLogExample callLogExamle = new CallLogExample();
			callLogExamle.createCriteria().andSessionIdEqualTo(sessionId);
			List<CallLog> callLogs = callLogMapper.selectByExample(callLogExamle);
			
			/* 注意,这里外呼日志是有可能还未生成的，这种情况出现在客服刚好在生成外呼日志和更新外呼记录的时间差内打了电话并且保存了通话,
			这种情况可直接跳过该外呼记录 ，等待下一个周期进行相应处理 */
			if (callLogs.size() != 0) {
				CallLog callLog = callLogs.get(0);
				callRecord.setCallTime(callLog.getCallTime());
				callRecord.setConnectTime(callLog.getConnectTime());
				callRecord.setHangUpTime(callLog.getHangUpTime());
			} else {
				continue;
			}
			
			// 更新外呼记录
			callRecordMapper.updateByPrimaryKey(callRecord);
		}
		
	}

	@Transactional
	@Override
	public void releaseKeepSecret(SecretPool pool,KeepSecret keepSecret) {
		// TODO Auto-generated method stub
		SecretPoolExample example=new SecretPoolExample();
		example.createCriteria().andSecretIdEqualTo(pool.getSecretId());
		secretPoolMapper.updateByExampleSelective(pool, example);
		updateKeepSecret(keepSecret);
	}

	@Override
	public KeepSecret keepSecretCustomer(SecretPool secretPool) {
		
		return null;
	}

	@Override
	public void updateSecretPool(SecretPool secretPool) {
		secretPoolMapper.updateSecretPool(secretPool.getKeepStatus(),secretPool.getSecretId());
	}
	
	private void updateKeepSecret(KeepSecret keepSecret){
		KeepSecretExample example=new KeepSecretExample();
		example.createCriteria().andFreedTimeIsNull().andSecretIdEqualTo(keepSecret.getSecretId()).andEnterpirseIdEqualTo(keepSecret.getEnterpirseId());
		keepSecretMapper.updateByExampleSelective(keepSecret, example);
	}

	@Override
	public List<KeepSecret> getKeepSecretByTime(Date start, Date end) {
		KeepSecretExample example = new KeepSecretExample();
		if (start != null) {
			example.createCriteria().andKeepTimeGreaterThanOrEqualTo(start);
		}
		if (end != null) {
			example.createCriteria().andKeepTimeLessThanOrEqualTo(end);
		}
		return keepSecretMapper.selectByExample(example);
	}

	
	@Override
	public boolean checkKeepSecret(String secretId) {
		KeepSecretExample example=new KeepSecretExample(); 
		example.createCriteria().andSecretIdEqualTo(secretId).andFreedTimeIsNull();
		List<KeepSecret> keepSecrets = keepSecretMapper.selectByExample(example);
		if(keepSecrets!=null&&keepSecrets.size()>0){
			return false;
		}
		return true;
	}

	@Override
	public int secretPoolCount(Short keepStatus, Short status,String brand, String userId,String area,Short callStatus) {
		return secretPoolMapper.getSecretPoolCount(keepStatus, keepStatus, brand, userId, area, callStatus);
		
	}

	
}
