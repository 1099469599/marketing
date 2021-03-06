package bz.sunlight.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KeyValueItemExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	public KeyValueItemExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("id like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("id not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andCategoryIdIsNull() {
			addCriterion("category_id is null");
			return (Criteria) this;
		}

		public Criteria andCategoryIdIsNotNull() {
			addCriterion("category_id is not null");
			return (Criteria) this;
		}

		public Criteria andCategoryIdEqualTo(String value) {
			addCriterion("category_id =", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdNotEqualTo(String value) {
			addCriterion("category_id <>", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdGreaterThan(String value) {
			addCriterion("category_id >", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdGreaterThanOrEqualTo(String value) {
			addCriterion("category_id >=", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdLessThan(String value) {
			addCriterion("category_id <", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdLessThanOrEqualTo(String value) {
			addCriterion("category_id <=", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdLike(String value) {
			addCriterion("category_id like", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdNotLike(String value) {
			addCriterion("category_id not like", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdIn(List<String> values) {
			addCriterion("category_id in", values, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdNotIn(List<String> values) {
			addCriterion("category_id not in", values, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdBetween(String value1, String value2) {
			addCriterion("category_id between", value1, value2, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdNotBetween(String value1, String value2) {
			addCriterion("category_id not between", value1, value2,
					"categoryId");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andCaptionIsNull() {
			addCriterion("caption is null");
			return (Criteria) this;
		}

		public Criteria andCaptionIsNotNull() {
			addCriterion("caption is not null");
			return (Criteria) this;
		}

		public Criteria andCaptionEqualTo(String value) {
			addCriterion("caption =", value, "caption");
			return (Criteria) this;
		}

		public Criteria andCaptionNotEqualTo(String value) {
			addCriterion("caption <>", value, "caption");
			return (Criteria) this;
		}

		public Criteria andCaptionGreaterThan(String value) {
			addCriterion("caption >", value, "caption");
			return (Criteria) this;
		}

		public Criteria andCaptionGreaterThanOrEqualTo(String value) {
			addCriterion("caption >=", value, "caption");
			return (Criteria) this;
		}

		public Criteria andCaptionLessThan(String value) {
			addCriterion("caption <", value, "caption");
			return (Criteria) this;
		}

		public Criteria andCaptionLessThanOrEqualTo(String value) {
			addCriterion("caption <=", value, "caption");
			return (Criteria) this;
		}

		public Criteria andCaptionLike(String value) {
			addCriterion("caption like", value, "caption");
			return (Criteria) this;
		}

		public Criteria andCaptionNotLike(String value) {
			addCriterion("caption not like", value, "caption");
			return (Criteria) this;
		}

		public Criteria andCaptionIn(List<String> values) {
			addCriterion("caption in", values, "caption");
			return (Criteria) this;
		}

		public Criteria andCaptionNotIn(List<String> values) {
			addCriterion("caption not in", values, "caption");
			return (Criteria) this;
		}

		public Criteria andCaptionBetween(String value1, String value2) {
			addCriterion("caption between", value1, value2, "caption");
			return (Criteria) this;
		}

		public Criteria andCaptionNotBetween(String value1, String value2) {
			addCriterion("caption not between", value1, value2, "caption");
			return (Criteria) this;
		}

		public Criteria andDictKeyIsNull() {
			addCriterion("dict_key is null");
			return (Criteria) this;
		}

		public Criteria andDictKeyIsNotNull() {
			addCriterion("dict_key is not null");
			return (Criteria) this;
		}

		public Criteria andDictKeyEqualTo(Short value) {
			addCriterion("dict_key =", value, "dictKey");
			return (Criteria) this;
		}

		public Criteria andDictKeyNotEqualTo(Short value) {
			addCriterion("dict_key <>", value, "dictKey");
			return (Criteria) this;
		}

		public Criteria andDictKeyGreaterThan(Short value) {
			addCriterion("dict_key >", value, "dictKey");
			return (Criteria) this;
		}

		public Criteria andDictKeyGreaterThanOrEqualTo(Short value) {
			addCriterion("dict_key >=", value, "dictKey");
			return (Criteria) this;
		}

		public Criteria andDictKeyLessThan(Short value) {
			addCriterion("dict_key <", value, "dictKey");
			return (Criteria) this;
		}

		public Criteria andDictKeyLessThanOrEqualTo(Short value) {
			addCriterion("dict_key <=", value, "dictKey");
			return (Criteria) this;
		}

		public Criteria andDictKeyIn(List<Short> values) {
			addCriterion("dict_key in", values, "dictKey");
			return (Criteria) this;
		}

		public Criteria andDictKeyNotIn(List<Short> values) {
			addCriterion("dict_key not in", values, "dictKey");
			return (Criteria) this;
		}

		public Criteria andDictKeyBetween(Short value1, Short value2) {
			addCriterion("dict_key between", value1, value2, "dictKey");
			return (Criteria) this;
		}

		public Criteria andDictKeyNotBetween(Short value1, Short value2) {
			addCriterion("dict_key not between", value1, value2, "dictKey");
			return (Criteria) this;
		}

		public Criteria andDictTextIsNull() {
			addCriterion("dict_text is null");
			return (Criteria) this;
		}

		public Criteria andDictTextIsNotNull() {
			addCriterion("dict_text is not null");
			return (Criteria) this;
		}

		public Criteria andDictTextEqualTo(String value) {
			addCriterion("dict_text =", value, "dictText");
			return (Criteria) this;
		}

		public Criteria andDictTextNotEqualTo(String value) {
			addCriterion("dict_text <>", value, "dictText");
			return (Criteria) this;
		}

		public Criteria andDictTextGreaterThan(String value) {
			addCriterion("dict_text >", value, "dictText");
			return (Criteria) this;
		}

		public Criteria andDictTextGreaterThanOrEqualTo(String value) {
			addCriterion("dict_text >=", value, "dictText");
			return (Criteria) this;
		}

		public Criteria andDictTextLessThan(String value) {
			addCriterion("dict_text <", value, "dictText");
			return (Criteria) this;
		}

		public Criteria andDictTextLessThanOrEqualTo(String value) {
			addCriterion("dict_text <=", value, "dictText");
			return (Criteria) this;
		}

		public Criteria andDictTextLike(String value) {
			addCriterion("dict_text like", value, "dictText");
			return (Criteria) this;
		}

		public Criteria andDictTextNotLike(String value) {
			addCriterion("dict_text not like", value, "dictText");
			return (Criteria) this;
		}

		public Criteria andDictTextIn(List<String> values) {
			addCriterion("dict_text in", values, "dictText");
			return (Criteria) this;
		}

		public Criteria andDictTextNotIn(List<String> values) {
			addCriterion("dict_text not in", values, "dictText");
			return (Criteria) this;
		}

		public Criteria andDictTextBetween(String value1, String value2) {
			addCriterion("dict_text between", value1, value2, "dictText");
			return (Criteria) this;
		}

		public Criteria andDictTextNotBetween(String value1, String value2) {
			addCriterion("dict_text not between", value1, value2, "dictText");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Short value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Short value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Short value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Short value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Short value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Short value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Short> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Short> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Short value1, Short value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Short value1, Short value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNull() {
			addCriterion("create_time is null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNotNull() {
			addCriterion("create_time is not null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeEqualTo(Date value) {
			addCriterion("create_time =", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotEqualTo(Date value) {
			addCriterion("create_time <>", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThan(Date value) {
			addCriterion("create_time >", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("create_time >=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThan(Date value) {
			addCriterion("create_time <", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
			addCriterion("create_time <=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIn(List<Date> values) {
			addCriterion("create_time in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotIn(List<Date> values) {
			addCriterion("create_time not in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeBetween(Date value1, Date value2) {
			addCriterion("create_time between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
			addCriterion("create_time not between", value1, value2,
					"createTime");
			return (Criteria) this;
		}

		public Criteria andIsBuiltInIsNull() {
			addCriterion("is_built_in is null");
			return (Criteria) this;
		}

		public Criteria andIsBuiltInIsNotNull() {
			addCriterion("is_built_in is not null");
			return (Criteria) this;
		}

		public Criteria andIsBuiltInEqualTo(Short value) {
			addCriterion("is_built_in =", value, "isBuiltIn");
			return (Criteria) this;
		}

		public Criteria andIsBuiltInNotEqualTo(Short value) {
			addCriterion("is_built_in <>", value, "isBuiltIn");
			return (Criteria) this;
		}

		public Criteria andIsBuiltInGreaterThan(Short value) {
			addCriterion("is_built_in >", value, "isBuiltIn");
			return (Criteria) this;
		}

		public Criteria andIsBuiltInGreaterThanOrEqualTo(Short value) {
			addCriterion("is_built_in >=", value, "isBuiltIn");
			return (Criteria) this;
		}

		public Criteria andIsBuiltInLessThan(Short value) {
			addCriterion("is_built_in <", value, "isBuiltIn");
			return (Criteria) this;
		}

		public Criteria andIsBuiltInLessThanOrEqualTo(Short value) {
			addCriterion("is_built_in <=", value, "isBuiltIn");
			return (Criteria) this;
		}

		public Criteria andIsBuiltInIn(List<Short> values) {
			addCriterion("is_built_in in", values, "isBuiltIn");
			return (Criteria) this;
		}

		public Criteria andIsBuiltInNotIn(List<Short> values) {
			addCriterion("is_built_in not in", values, "isBuiltIn");
			return (Criteria) this;
		}

		public Criteria andIsBuiltInBetween(Short value1, Short value2) {
			addCriterion("is_built_in between", value1, value2, "isBuiltIn");
			return (Criteria) this;
		}

		public Criteria andIsBuiltInNotBetween(Short value1, Short value2) {
			addCriterion("is_built_in not between", value1, value2, "isBuiltIn");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table key_value_item
	 * @mbggenerated
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table key_value_item
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}