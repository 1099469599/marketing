package bz.sunlight.dto;

import java.util.Date;


public class ReceiptRecordDTO {

	
	private Double account;
	
	private Double giftAccount;
	
	private Short type;
	
	private Date payTime;
	
	private String enterpriseId;
	
	private String userInfoId;
	
	private String adminInfoId;

	
	public Double getAccount() {
		return account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	public Double getGiftAccount() {
		return giftAccount;
	}

	public void setGiftAccount(Double giftAccount) {
		this.giftAccount = giftAccount;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	

	

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getAdminInfoId() {
		return adminInfoId;
	}

	public void setAdminInfoId(String adminInfoId) {
		this.adminInfoId = adminInfoId;
	}
	
	
}
