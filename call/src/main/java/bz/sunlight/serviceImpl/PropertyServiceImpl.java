package bz.sunlight.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.dao.CustomPropertiesMapper;
import bz.sunlight.entity.CustomProperties;
import bz.sunlight.entity.CustomPropertiesExample;
import bz.sunlight.service.PropertyService;

@Service("propertyService")
public class PropertyServiceImpl implements PropertyService{

	@Resource
	private CustomPropertiesMapper customPropertiesMapper;
	
	
	@Override
	public void add(CustomProperties customProperties) {
		// TODO Auto-generated method stub
		customPropertiesMapper.insert(customProperties);
	}

	@Override
	public void updateById(CustomProperties customProperties) {
		// TODO Auto-generated method stub
		CustomPropertiesExample example=new CustomPropertiesExample();
		example.createCriteria().andIdEqualTo(customProperties.getId()).andStatusEqualTo(BaseConstant.BASE_TRUE);
		customPropertiesMapper.updateByExampleSelective(customProperties, example);
	}

	@Override
	public int getCount(CustomProperties customProperties) {
		// TODO Auto-generated method stub
		CustomPropertiesExample example=new CustomPropertiesExample();
		example.createCriteria().andTypeEqualTo(customProperties.getType()).andStatusEqualTo(customProperties.getStatus());
		return customPropertiesMapper.countByExample(example);
	}

	@Override
	public List<CustomProperties> queryAll(Short status,String enterpriseId,Short type,Short property) {
		// TODO Auto-generated method stub
		CustomPropertiesExample example=new CustomPropertiesExample();
		example.setOrderByClause("property asc");
		example.createCriteria().andStatusEqualTo(status).andEnterpriseIdEqualTo(enterpriseId).andTypeEqualTo(type).andPropertyEqualTo(property);
		return customPropertiesMapper.selectByExample(example);
	}
	
	@Override
	public List<CustomProperties> queryCustomPropertiesAll(Short status,String enterpriseId) {
		// TODO Auto-generated method stub
		CustomPropertiesExample example=new CustomPropertiesExample();
		example.setOrderByClause("type asc,property asc,create_time desc");
		example.createCriteria().andStatusEqualTo(status).andEnterpriseIdEqualTo(enterpriseId);
		return customPropertiesMapper.selectByExample(example);
	}

	@Override
	public CustomProperties queryById(String customPropertiesId) {
		return customPropertiesMapper.selectByPrimaryKey(customPropertiesId);
	}

}
