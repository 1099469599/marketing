package bz.sunlight.dto;

import java.util.Date;

public class CallRecordDTO {

	private String secretPoolId;
	private String callRecordId;
	private String secretId;
	
	
	private String record;
	private String customPropertiesId1;
	private String customPropertiesId2;
	private String customProperties3;
	private String keepCustomPropertiesId1;
	private String keepCustomPropertiesId2;
	private String keepCustomProperties3;
	private String remark;
	private String customerId;
	private String name;
	private Short mobile;
	private Short tel;
	private Date appointmentDate;
	private String intentionVehicle;
	private String customerRemark;
	private Short keepStatus;
	
	private Integer pageSize;
	private Integer currentPage;

	private String userId;
	private Short callResult;
	private String languageTrickId;
	private Date startTime;
	private Date endTime;
	
	
	
	public CallRecordDTO() {
		super();
	}

	public CallRecordDTO(String userId, Short callResult, String languageTrickId, Date startTime, Date endTime, Integer pageSize, Integer currentPage) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.userId = userId;
		this.callResult = callResult;
		this.languageTrickId = languageTrickId;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Short getCallResult() {
		return callResult;
	}
	public void setCallResult(Short callResult) {
		this.callResult = callResult;
	}
	public String getLanguageTrickId() {
		return languageTrickId;
	}
	public void setLanguageTrickId(String languageTrickId) {
		this.languageTrickId = languageTrickId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	

	

	public String getCallRecordId() {
		return callRecordId;
	}

	public void setCallRecordId(String callRecordId) {
		this.callRecordId = callRecordId;
	}

	public String getSecretId() {
		return secretId;
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getCustomPropertiesId1() {
		return customPropertiesId1;
	}

	public void setCustomPropertiesId1(String customPropertiesId1) {
		this.customPropertiesId1 = customPropertiesId1;
	}

	public String getCustomPropertiesId2() {
		return customPropertiesId2;
	}

	public void setCustomPropertiesId2(String customPropertiesId2) {
		this.customPropertiesId2 = customPropertiesId2;
	}

	public String getCustomProperties3() {
		return customProperties3;
	}

	public void setCustomProperties3(String customProperties3) {
		this.customProperties3 = customProperties3;
	}

	public String getKeepCustomPropertiesId1() {
		return keepCustomPropertiesId1;
	}

	public void setKeepCustomPropertiesId1(String keepCustomPropertiesId1) {
		this.keepCustomPropertiesId1 = keepCustomPropertiesId1;
	}

	public String getKeepCustomPropertiesId2() {
		return keepCustomPropertiesId2;
	}

	public void setKeepCustomPropertiesId2(String keepCustomPropertiesId2) {
		this.keepCustomPropertiesId2 = keepCustomPropertiesId2;
	}

	public String getKeepCustomProperties3() {
		return keepCustomProperties3;
	}

	public void setKeepCustomProperties3(String keepCustomProperties3) {
		this.keepCustomProperties3 = keepCustomProperties3;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getMobile() {
		return mobile;
	}

	public void setMobile(Short mobile) {
		this.mobile = mobile;
	}

	public Short getTel() {
		return tel;
	}

	public void setTel(Short tel) {
		this.tel = tel;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getIntentionVehicle() {
		return intentionVehicle;
	}

	public void setIntentionVehicle(String intentionVehicle) {
		this.intentionVehicle = intentionVehicle;
	}

	public String getCustomerRemark() {
		return customerRemark;
	}

	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}

	public Short getKeepStatus() {
		return keepStatus;
	}

	public void setKeepStatus(Short keepStatus) {
		this.keepStatus = keepStatus;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSecretPoolId() {
		return secretPoolId;
	}

	public void setSecretPoolId(String secretPoolId) {
		this.secretPoolId = secretPoolId;
	}
	
	
	
	
}
