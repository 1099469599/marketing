package bz.sunlight.service;

import java.util.List;

import bz.sunlight.entity.CustomProperties;

public interface PropertyService {

	public void add(CustomProperties customProperties);
	
	public void updateById(CustomProperties customProperties);
	
	public int getCount(CustomProperties customProperties);
	
	public List<CustomProperties> queryAll(Short status,String enterpriseId,Short type,Short property);
	
	List<CustomProperties> queryCustomPropertiesAll(Short status,String enterpriseId);
	
	public CustomProperties queryById(String customPropertiesId);
}
