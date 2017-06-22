package bz.sunlight.entity;

import java.util.Date;
import java.util.List;

public class CallRecordVO {
	
	private int totalPage;						// 页面总数
	private int totalItemCount;					// 数据总条数
	private List<CallRecordDemo> callRecordDemos;	// 外呼列表
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalItemCount() {
		return totalItemCount;
	}
	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}
	public List<CallRecordDemo> getCallRecords() {
		return callRecordDemos;
	}
	public void setCallRecords(List<CallRecordDemo> callRecordDemos) {
		this.callRecordDemos = callRecordDemos;
	}


	public class CallRecordDemo {
		private String id;
		private String secretId;					// 密号ID
		private String languageTrickId;				// 话术ID
		private String languageTrickTitle;			// 话术标题
		private Short callResult;					// 外呼效果
		private String record;						// 通话记录
		private Date callTime;						// 拨打时间
		private Date connectTime;					// 接通时间
		private Date hangUpTime;					// 挂断时间
		private Date createTime;					// 创建时间
		private String customPropertiesId1;			// 自定义属性1 ID
		private String customPropertiesValue1;		// 自定义属性1
		private String customPropertiesId2;			// 自定义属性2 ID
		private String customPropertiesValue2;		// 自定义属性2
		private String customProperties3;			// 自定义属性3 ID
		private String remark;						// 备注
		private Short status;						// 状态 0-有效 1-无效

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getSecretId() {
			return secretId;
		}
		public void setSecretId(String secretId) {
			this.secretId = secretId;
		}
		public String getLanguageTrickId() {
			return languageTrickId;
		}
		public void setLanguageTrickId(String languageTrickId) {
			this.languageTrickId = languageTrickId;
		}
		public String getLanguageTrickTitle() {
			return languageTrickTitle;
		}
		public void setLanguageTrickTitle(String languageTrickTitle) {
			this.languageTrickTitle = languageTrickTitle;
		}
		public Short getCallResult() {
			return callResult;
		}
		public void setCallResult(Short callResult) {
			this.callResult = callResult;
		}
		public String getRecord() {
			return record;
		}
		public void setRecord(String record) {
			this.record = record;
		}
		public Date getCallTime() {
			return callTime;
		}
		public void setCallTime(Date callTime) {
			this.callTime = callTime;
		}
		public Date getConnectTime() {
			return connectTime;
		}
		public void setConnectTime(Date connectTime) {
			this.connectTime = connectTime;
		}
		public Date getHangUpTime() {
			return hangUpTime;
		}
		public void setHangUpTime(Date hangUpTime) {
			this.hangUpTime = hangUpTime;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public String getCustomPropertiesId1() {
			return customPropertiesId1;
		}
		public void setCustomPropertiesId1(String customPropertiesId1) {
			this.customPropertiesId1 = customPropertiesId1;
		}
		public String getCustomPropertiesValue1() {
			return customPropertiesValue1;
		}
		public void setCustomPropertiesValue1(String customPropertiesValue1) {
			this.customPropertiesValue1 = customPropertiesValue1;
		}
		public String getCustomPropertiesId2() {
			return customPropertiesId2;
		}
		public void setCustomPropertiesId2(String customPropertiesId2) {
			this.customPropertiesId2 = customPropertiesId2;
		}
		public String getCustomPropertiesValue2() {
			return customPropertiesValue2;
		}
		public void setCustomPropertiesValue2(String customPropertiesValue2) {
			this.customPropertiesValue2 = customPropertiesValue2;
		}
		public String getCustomProperties3() {
			return customProperties3;
		}
		public void setCustomProperties3(String customProperties3) {
			this.customProperties3 = customProperties3;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public Short getStatus() {
			return status;
		}
		public void setStatus(Short status) {
			this.status = status;
		}
	}

}
