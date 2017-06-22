package bz.sunlight.dao;

import bz.sunlight.entity.ReceiptRecord;
import bz.sunlight.entity.ReceiptRecordExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ReceiptRecordMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receipt_record
	 * @mbggenerated
	 */
	int countByExample(ReceiptRecordExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receipt_record
	 * @mbggenerated
	 */
	int deleteByExample(ReceiptRecordExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receipt_record
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receipt_record
	 * @mbggenerated
	 */
	int insert(ReceiptRecord record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receipt_record
	 * @mbggenerated
	 */
	int insertSelective(ReceiptRecord record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receipt_record
	 * @mbggenerated
	 */
	List<ReceiptRecord> selectByExample(ReceiptRecordExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receipt_record
	 * @mbggenerated
	 */
	ReceiptRecord selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receipt_record
	 * @mbggenerated
	 */
	int updateByExampleSelective(@Param("record") ReceiptRecord record,
			@Param("example") ReceiptRecordExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receipt_record
	 * @mbggenerated
	 */
	int updateByExample(@Param("record") ReceiptRecord record,
			@Param("example") ReceiptRecordExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receipt_record
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(ReceiptRecord record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table receipt_record
	 * @mbggenerated
	 */
	int updateByPrimaryKey(ReceiptRecord record);

	List<ReceiptRecord> selectReceiptWithUserAndEnterprise(Map map);
	
	List<ReceiptRecord> selectReceiptWithAdmin(Map map);
	
	int getReciptRecordCount(@Param("type") Short type,@Param("startPayTime") Date startPayTime,
			@Param("endPayTime") Date endPayTime,@Param("enterpriseId") String enterpriseId);
	
	int selectAdminReceiptCount(Short type);
}