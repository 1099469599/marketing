package bz.sunlight.service;

import java.util.List;

import bz.sunlight.entity.KeyValueCategory;
import bz.sunlight.entity.KeyValueItem;

public interface KeyValueService {

	List<KeyValueItem> selectAllKeyValueItem();

	KeyValueItem selectKeyValueItem(String id);
	
	List<KeyValueItem> selectKeyValueItemByCategory(String categoryId);
	
	List<KeyValueItem> selectKeyValueItemByCategory(String categoryId,Short status);
	
	List<KeyValueCategory> selectAllCategoryWithItem(Short categoryStatus,Short itemStatus,String categoryName);
	
	void add(KeyValueItem keyValueItem);
	
	void delete(KeyValueItem keyValueItem);
	
	List<KeyValueCategory> selectKeyValueCategory(String categoryName,Short categoryStatus);
	
	List<KeyValueCategory> selectKeyValueCategory(Short categoryStatus);
	
	Short selectMax(String categotyId);
	
	KeyValueCategory selectCategory(String id);
	
	void add(KeyValueCategory keyValueCategory);
	
	void update(KeyValueItem keyValueItem);
}
