package bz.sunlight.entity;

public class Account {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column account.id
	 * @mbggenerated
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column account.account
	 * @mbggenerated
	 */
	private String account;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column account.password
	 * @mbggenerated
	 */
	private String password;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column account.user_info_id
	 * @mbggenerated
	 */
	private String userInfoId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column account.id
	 * @return  the value of account.id
	 * @mbggenerated
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column account.id
	 * @param id  the value for account.id
	 * @mbggenerated
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column account.account
	 * @return  the value of account.account
	 * @mbggenerated
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column account.account
	 * @param account  the value for account.account
	 * @mbggenerated
	 */
	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column account.password
	 * @return  the value of account.password
	 * @mbggenerated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column account.password
	 * @param password  the value for account.password
	 * @mbggenerated
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column account.user_info_id
	 * @return  the value of account.user_info_id
	 * @mbggenerated
	 */
	public String getUserInfoId() {
		return userInfoId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column account.user_info_id
	 * @param userInfoId  the value for account.user_info_id
	 * @mbggenerated
	 */
	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId == null ? null : userInfoId.trim();
	}
}