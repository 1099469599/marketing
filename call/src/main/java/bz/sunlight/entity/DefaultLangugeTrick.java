package bz.sunlight.entity;

public class DefaultLangugeTrick {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column default_languge_trick.id
	 * @mbggenerated
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column default_languge_trick.language_trick_id
	 * @mbggenerated
	 */
	private String languageTrickId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column default_languge_trick.user_info_id
	 * @mbggenerated
	 */
	private String userInfoId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column default_languge_trick.id
	 * @return  the value of default_languge_trick.id
	 * @mbggenerated
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column default_languge_trick.id
	 * @param id  the value for default_languge_trick.id
	 * @mbggenerated
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column default_languge_trick.language_trick_id
	 * @return  the value of default_languge_trick.language_trick_id
	 * @mbggenerated
	 */
	public String getLanguageTrickId() {
		return languageTrickId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column default_languge_trick.language_trick_id
	 * @param languageTrickId  the value for default_languge_trick.language_trick_id
	 * @mbggenerated
	 */
	public void setLanguageTrickId(String languageTrickId) {
		this.languageTrickId = languageTrickId == null ? null : languageTrickId
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column default_languge_trick.user_info_id
	 * @return  the value of default_languge_trick.user_info_id
	 * @mbggenerated
	 */
	public String getUserInfoId() {
		return userInfoId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column default_languge_trick.user_info_id
	 * @param userInfoId  the value for default_languge_trick.user_info_id
	 * @mbggenerated
	 */
	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId == null ? null : userInfoId.trim();
	}
}