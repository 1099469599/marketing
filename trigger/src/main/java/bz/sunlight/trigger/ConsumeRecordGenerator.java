package bz.sunlight.trigger;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.entity.BusinessQueue;
import bz.sunlight.entity.BusinessRule;
import bz.sunlight.entity.CallLog;
import bz.sunlight.entity.CallQuota;
import bz.sunlight.entity.ConsumeRecord;
import bz.sunlight.entity.EnterpriseAccount;
import bz.sunlight.entity.KeepSecret;
import bz.sunlight.entity.UserInfo;
import bz.sunlight.service.AccountService;
import bz.sunlight.service.CallService;
import bz.sunlight.service.PayService;
import bz.sunlight.service.RuleService;
import bz.sunlight.serviceImpl.CallServiceImpl;
import bz.sunlight.util.DateUtil;
import bz.sunlight.util.HttpUtil;
import bz.sunlight.util.PropertiesUtil;
import bz.sunlight.util.UUIDUtil;

@Component
public class ConsumeRecordGenerator {

	@Autowired
	private PayService payService;
	@Autowired
	private RuleService ruleService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private CallService callService;

	private static Logger logger = LoggerFactory.getLogger(CallServiceImpl.class);
	
	private ConsumeRecord consumeRecord;		// 消费记录
	private Double keepPrice;					// 保留单价
	private Double callPrice;					// 通话单价
	private Double seatPrice;					// 座席单价
	private Double remindBalance;				// 余额
	private Short validCallSecond;				// 有效通话时间
	private Double currentBalance;				// 当前余额

	@Transactional
	public void doKeep(BusinessQueue business) {
		// 获取保留区信息
		KeepSecret keepSecret = callService.getKeepSecretById(business.getBusinessId());
		if (keepSecret == null) {
			return;
		}
		
		// 初始化消费记录
		consumeRecord.setId(UUIDUtil.getOrigUUID());
		consumeRecord.setAccount(keepPrice);
		consumeRecord.setConsumeTime(keepSecret.getKeepTime());
		consumeRecord.setEnterpriseId(keepSecret.getEnterpirseId());
		consumeRecord.setUserInfoId(keepSecret.getUserInfoId());
		consumeRecord.setType(BaseConstant.CONSUME_TYPE_KEEP);

		// 修改帐户余额
		EnterpriseAccount account = accountService.getEnterpriseAccount(keepSecret.getEnterpirseId());
		currentBalance = account.getAccountBalance() - keepPrice;
		account.setAccountBalance(currentBalance);

		// 新增消费记录
		consumeRecord.setCurrent(currentBalance);
		int addResult = payService.addConsumeRecord(consumeRecord);
		if (addResult != 0) {
			// 更新任务队列处理状态
			business.setHandleStatus(BaseConstant.HANDLE_STATUS_YES);
			business.setModifyTime(new Date());
			callService.putBusinessQueue(business);

			// 帐户数据更新
			accountService.putEnterpriseAccount(account);
		}

	}
	
	@Transactional
	public void doCall(BusinessQueue business) {
		// 获取消费记录信息
		CallLog callLog = callService.getCallLogById(business.getBusinessId());
		if (callLog == null) {
			return;
		}

		// 如果暂未生成接通时间与挂断时间则不对该数据做处理
		Date connectTime = callLog.getConnectTime();
		Date hangUpTime = callLog.getHangUpTime();
		if (connectTime == null || hangUpTime == null) {
			return;
		}
		
		// 仅对有效外呼进行处理
		long diffSec = DateUtil.getDiffSeconds(connectTime, hangUpTime); 
		if (diffSec >= validCallSecond) {
			// 初始化消费记录
			consumeRecord.setId(UUIDUtil.getOrigUUID());
			consumeRecord.setConsumeTime(hangUpTime);
			consumeRecord.setEnterpriseId(callLog.getEnterpriseId());
			consumeRecord.setUserInfoId(callLog.getUserInfoId());
			consumeRecord.setType(BaseConstant.CONSUME_TYPE_CALL);
			
			// 修改当月已拨打电话次数
			EnterpriseAccount account = accountService.getEnterpriseAccount(callLog.getEnterpriseId());
			Integer currentCalledQuantity = account.getCalledQuantity() + 1;
			account.setCalledQuantity(currentCalledQuantity);

			// 在超出额度之后开始扣除帐户余额,未超出额度前的消费金额都给 0
			currentBalance = account.getAccountBalance();
			CallQuota callQuota = payService.getQuotaByEnterprise(callLog.getEnterpriseId());
			Integer baseCallQuantity = callQuota.getCallQuantity();
			if (currentCalledQuantity > baseCallQuantity) {
				// 修改帐户余额
				currentBalance = account.getAccountBalance() - callPrice;
				consumeRecord.setAccount(callPrice);
				account.setAccountBalance(currentBalance);
			} else {
				consumeRecord.setAccount(0.00);
			}

			// 新增消费记录
			consumeRecord.setCurrent(currentBalance);
			int addResult = payService.addConsumeRecord(consumeRecord);
			if (addResult != 0) {
				// 更新处理状态
				business.setHandleStatus(BaseConstant.HANDLE_STATUS_YES);
				business.setModifyTime(new Date());
				callService.putBusinessQueue(business);

				// 更新帐户
				accountService.putEnterpriseAccount(account);
			}
		}
		
	}

	@Transactional
	public void doSeat(BusinessQueue business) {
		// 获取座席
		UserInfo userInfo = accountService.getUserInfo(business.getBusinessId());
		if (userInfo == null) {
			return;
		}

		// 初始化消费记录
		consumeRecord.setId(UUIDUtil.getOrigUUID());
		consumeRecord.setAccount(seatPrice);
		consumeRecord.setConsumeTime(userInfo.getCreateTime());
		consumeRecord.setEnterpriseId(userInfo.getEnterpriseId());
		consumeRecord.setUserInfoId(userInfo.getId());
		consumeRecord.setType(BaseConstant.CONSUME_TYPE_SEAT);
		
		// 修改帐户余额
		EnterpriseAccount account = accountService.getEnterpriseAccount(userInfo.getEnterpriseId());				
		currentBalance = account.getAccountBalance() - seatPrice;
		account.setAccountBalance(currentBalance);

		// 新增消费记录
		consumeRecord.setCurrent(currentBalance);
		int addResult = payService.addConsumeRecord(consumeRecord);
		if (addResult != 0) {
			// 更新处理状态
			business.setHandleStatus(BaseConstant.HANDLE_STATUS_YES);
			business.setModifyTime(new Date());
			callService.putBusinessQueue(business);

			// 更新帐户
			accountService.putEnterpriseAccount(account);
		}

	}

	public void businessQueueHandler() {
		// 每次消费记录结算前先做一次新增外呼日志与更新外呼记录
		callService.addCallLog();
		callService.updateCallRecord();

		List<BusinessQueue> businessQueue = callService.getUnhandledBusinessQueue();
		// 待处理任务的时候获取业务规则
		if (businessQueue.size() != 0) {
			BusinessRule businessRule = ruleService.query();
			callPrice = businessRule.getCallPrice(); 
			keepPrice = businessRule.getKeepPrice();
			seatPrice = businessRule.getSeatPrice();
			remindBalance = businessRule.getRemindBalance();
			validCallSecond = businessRule.getCallSecond();
		} else {
			return;
		}
		
		for (BusinessQueue business : businessQueue) {
			// 初始化两个全局变量
			consumeRecord = new ConsumeRecord();
			currentBalance = null;

			// 处理保留
			if (business.getType() == BaseConstant.CONSUME_TYPE_KEEP) {
				doKeep(business);
			} else if (business.getType() == BaseConstant.CONSUME_TYPE_CALL) {
				doCall(business);
			} else if (business.getType() == BaseConstant.CONSUME_TYPE_SEAT) {
				doSeat(business);
			}

			// 如果根据 businessId 查不到相应的业务单据会造成帐户余额初始化不成功
			if (currentBalance == null) {
				continue;
			}
			
			// 余额提醒
			if (currentBalance < remindBalance) {
				String balanceRoot = PropertiesUtil.getInstanse().getStringByProperties("aim_marketing_balance", "/server.properties");
				String balanceURI = String.format(balanceRoot, consumeRecord.getUserInfoId(), remindBalance, currentBalance);
				// 推送消息
				if (balanceURI != null) {
					boolean isSendSuccess = HttpUtil.sendGetRequest(balanceURI);
					if (!isSendSuccess) {
						logger.debug("余额提醒失败！");
					}
				}
			}
		}
		
	}
	
}
