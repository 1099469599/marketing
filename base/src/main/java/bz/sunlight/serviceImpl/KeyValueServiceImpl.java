package bz.sunlight.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import bz.sunlight.dao.KeyValueCategoryMapper;
import bz.sunlight.dao.KeyValueItemMapper;
import bz.sunlight.entity.KeyValueCategory;
import bz.sunlight.entity.KeyValueCategoryExample;
import bz.sunlight.entity.KeyValueItem;
import bz.sunlight.entity.KeyValueItemExample;
import bz.sunlight.service.KeyValueService;
@Service("keyValueService")
public class KeyValueServiceImpl implements KeyValueService{

	@Resource
	private KeyValueItemMapper keyValueItemMapper;
	@Resource
	private KeyValueCategoryMapper keyValueCategoryMapper;
	
	@Override
	public List<KeyValueItem> selectAllKeyValueItem() {
		// TODO Auto-generated method stub
		KeyValueItemExample example=new KeyValueItemExample();
		example.setOrderByClause("name asc");
		return keyValueItemMapper.selectByExample(example);
	}

	@Override
	public void add(KeyValueItem keyValueItem) {
		keyValueItemMapper.insert(keyValueItem);
		
	}

	@Override
	public void delete(KeyValueItem keyValueItem) {
		keyValueItemMapper.updateByPrimaryKeySelective(keyValueItem);
		
	}

	@Override
	public List<KeyValueCategory> selectKeyValueCategory(String categoryName,Short categoryStatus) {
		KeyValueCategoryExample example=new KeyValueCategoryExample();
		example.createCriteria().andStatusEqualTo(categoryStatus).andNameLike("%"+categoryName+"%");
		return keyValueCategoryMapper.selectByExample(example);
		
	}
	
	@Override
	public List<KeyValueCategory> selectKeyValueCategory(Short categoryStatus) {
		KeyValueCategoryExample example=new KeyValueCategoryExample();
		example.createCriteria().andStatusEqualTo(categoryStatus);
		return keyValueCategoryMapper.selectByExample(example);
		
	}

	@Override
	public Short selectMax(String categotyId) {
		return keyValueItemMapper.selectMax(categotyId);
		
	}

	@Override
	public KeyValueCategory selectCategory(String id) {
		KeyValueCategoryExample example=new KeyValueCategoryExample();
		example.createCriteria().andIdEqualTo(id);
		List<KeyValueCategory> category = keyValueCategoryMapper.selectByExample(example);
		if(category!=null&&category.size()>0){
			return category.get(0);
					
		}
		return null;
	}

	@Override
	public KeyValueItem selectKeyValueItem(String id) {
		
		return keyValueItemMapper.selectByPrimaryKey(id);
		
	}

	@Override
	public List<KeyValueItem> selectKeyValueItemByCategory(String categoryId) {
		KeyValueItemExample example=new KeyValueItemExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		return keyValueItemMapper.selectByExample(example);
	}

	@Override
	public List<KeyValueItem> selectKeyValueItemByCategory(String categoryId,
			Short status) {
		// TODO Auto-generated method stub
		KeyValueItemExample example=new KeyValueItemExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId).andStatusEqualTo(status);
		return keyValueItemMapper.selectByExample(example);
		
	}

	@Override
	public void add(KeyValueCategory keyValueCategory) {
		keyValueCategoryMapper.insert(keyValueCategory);
		
	}

	@Override
	public List<KeyValueCategory> selectAllCategoryWithItem(Short categoryStatus,Short itemStatus,String categoryName) {
		// TODO Auto-generated method stub
		return keyValueCategoryMapper.selectAllCategoryWithItem(categoryStatus, itemStatus,categoryName);
	}

	@Override
	public void update(KeyValueItem keyValueItem) {
		// TODO Auto-generated method stub
		keyValueItemMapper.updateByPrimaryKeySelective(keyValueItem);
	}
	
	

}
