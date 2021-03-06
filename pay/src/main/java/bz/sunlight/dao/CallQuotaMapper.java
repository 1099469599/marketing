package bz.sunlight.dao;

import bz.sunlight.entity.CallQuota;
import bz.sunlight.entity.CallQuotaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CallQuotaMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table call_quota
	 * @mbggenerated
	 */
	int countByExample(CallQuotaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table call_quota
	 * @mbggenerated
	 */
	int deleteByExample(CallQuotaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table call_quota
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table call_quota
	 * @mbggenerated
	 */
	int insert(CallQuota record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table call_quota
	 * @mbggenerated
	 */
	int insertSelective(CallQuota record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table call_quota
	 * @mbggenerated
	 */
	List<CallQuota> selectByExample(CallQuotaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table call_quota
	 * @mbggenerated
	 */
	CallQuota selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table call_quota
	 * @mbggenerated
	 */
	int updateByExampleSelective(@Param("record") CallQuota record,
			@Param("example") CallQuotaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table call_quota
	 * @mbggenerated
	 */
	int updateByExample(@Param("record") CallQuota record,
			@Param("example") CallQuotaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table call_quota
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(CallQuota record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table call_quota
	 * @mbggenerated
	 */
	int updateByPrimaryKey(CallQuota record);
}