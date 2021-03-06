package bz.sunlight.entity;

import java.util.Date;

public class CallQuota {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column call_quota.id
	 * @mbggenerated
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column call_quota.enterprise_id
	 * @mbggenerated
	 */
	private String enterpriseId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column call_quota.call_quantity
	 * @mbggenerated
	 */
	private Integer callQuantity;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column call_quota.expire_time
	 * @mbggenerated
	 */
	private Date expireTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column call_quota.price
	 * @mbggenerated
	 */
	private Double price;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column call_quota.seat_quantity
	 * @mbggenerated
	 */
	private Integer seatQuantity;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column call_quota.id
	 * @return  the value of call_quota.id
	 * @mbggenerated
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column call_quota.id
	 * @param id  the value for call_quota.id
	 * @mbggenerated
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column call_quota.enterprise_id
	 * @return  the value of call_quota.enterprise_id
	 * @mbggenerated
	 */
	public String getEnterpriseId() {
		return enterpriseId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column call_quota.enterprise_id
	 * @param enterpriseId  the value for call_quota.enterprise_id
	 * @mbggenerated
	 */
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId == null ? null : enterpriseId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column call_quota.call_quantity
	 * @return  the value of call_quota.call_quantity
	 * @mbggenerated
	 */
	public Integer getCallQuantity() {
		return callQuantity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column call_quota.call_quantity
	 * @param callQuantity  the value for call_quota.call_quantity
	 * @mbggenerated
	 */
	public void setCallQuantity(Integer callQuantity) {
		this.callQuantity = callQuantity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column call_quota.expire_time
	 * @return  the value of call_quota.expire_time
	 * @mbggenerated
	 */
	public Date getExpireTime() {
		return expireTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column call_quota.expire_time
	 * @param expireTime  the value for call_quota.expire_time
	 * @mbggenerated
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column call_quota.price
	 * @return  the value of call_quota.price
	 * @mbggenerated
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column call_quota.price
	 * @param price  the value for call_quota.price
	 * @mbggenerated
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column call_quota.seat_quantity
	 * @return  the value of call_quota.seat_quantity
	 * @mbggenerated
	 */
	public Integer getSeatQuantity() {
		return seatQuantity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column call_quota.seat_quantity
	 * @param seatQuantity  the value for call_quota.seat_quantity
	 * @mbggenerated
	 */
	public void setSeatQuantity(Integer seatQuantity) {
		this.seatQuantity = seatQuantity;
	}
}