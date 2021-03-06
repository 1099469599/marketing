package bz.sunlight.dao;

import bz.sunlight.entity.LanguageTrick;
import bz.sunlight.entity.LanguageTrickExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LanguageTrickMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table language_trick
	 * @mbggenerated
	 */
	int countByExample(LanguageTrickExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table language_trick
	 * @mbggenerated
	 */
	int deleteByExample(LanguageTrickExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table language_trick
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table language_trick
	 * @mbggenerated
	 */
	int insert(LanguageTrick record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table language_trick
	 * @mbggenerated
	 */
	int insertSelective(LanguageTrick record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table language_trick
	 * @mbggenerated
	 */
	List<LanguageTrick> selectByExample(LanguageTrickExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table language_trick
	 * @mbggenerated
	 */
	LanguageTrick selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table language_trick
	 * @mbggenerated
	 */
	int updateByExampleSelective(@Param("record") LanguageTrick record,
			@Param("example") LanguageTrickExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table language_trick
	 * @mbggenerated
	 */
	int updateByExample(@Param("record") LanguageTrick record,
			@Param("example") LanguageTrickExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table language_trick
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(LanguageTrick record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table language_trick
	 * @mbggenerated
	 */
	int updateByPrimaryKey(LanguageTrick record);

	List<LanguageTrick> selectLanguageTrick(LanguageTrick record);
}