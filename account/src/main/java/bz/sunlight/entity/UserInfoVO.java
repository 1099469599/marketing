package bz.sunlight.entity;

import java.math.BigDecimal;

public class UserInfoVO {

		private String userInfoId;				// 用户ID
		private String userInfoName;			// 姓名
		private Short userInfoGender;			// 性别
		private String userInfoNumber;			// 工号
		private String account;					// 账户名
		private String enterpriseId;			// 企业ID
		private String enterpriseName;			// 企业名称
		private Short industry;					// 行业
		private BigDecimal accountBalance;		// 余额
		private Integer callQuantity;			// 有效通话数量
		private Integer calledQuantity;			// 已使用有效通话数量
		
		public Short getUserInfoGender() {
			return userInfoGender;
		}
		public void setUserInfoGender(Short userInfoGender) {
			this.userInfoGender = userInfoGender;
		}
		public Short getIndustry() {
			return industry;
		}
		public void setIndustry(Short industry) {
			this.industry = industry;
		}
		public String getUserInfoId() {
			return userInfoId;
		}
		public void setUserInfoId(String userInfoId) {
			this.userInfoId = userInfoId;
		}
		public String getUserInfoName() {
			return userInfoName;
		}
		public void setUserInfoName(String userInfoName) {
			this.userInfoName = userInfoName;
		}
		public String getUserInfoNumber() {
			return userInfoNumber;
		}
		public void setUserInfoNumber(String userInfoNumber) {
			this.userInfoNumber = userInfoNumber;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getEnterpriseId() {
			return enterpriseId;
		}
		public void setEnterpriseId(String enterpriseId) {
			this.enterpriseId = enterpriseId;
		}
		public String getEnterpriseName() {
			return enterpriseName;
		}
		public void setEnterpriseName(String enterpriseName) {
			this.enterpriseName = enterpriseName;
		}
		public BigDecimal getAccountBalance() {
			return accountBalance;
		}
		public void setAccountBalance(BigDecimal accountBalance) {
			this.accountBalance = accountBalance;
		}
		public Integer getCallQuantity() {
			return callQuantity;
		}
		public void setCallQuantity(Integer callQuantity) {
			this.callQuantity = callQuantity;
		}
		public Integer getCalledQuantity() {
			return calledQuantity;
		}
		public void setCalledQuantity(Integer calledQuantity) {
			this.calledQuantity = calledQuantity;
		}
	
}
