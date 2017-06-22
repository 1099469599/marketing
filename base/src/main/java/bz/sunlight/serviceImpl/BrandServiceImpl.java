package bz.sunlight.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import bz.sunlight.dao.BrandMapper;
import bz.sunlight.entity.Brand;
import bz.sunlight.entity.BrandExample;
import bz.sunlight.service.BrandService;

@Service("brandService")
public class BrandServiceImpl implements BrandService{

	@Resource
	private BrandMapper brandMapper;
	
	@Override
	public List<Brand> getBrand(Short status) {
		// TODO Auto-generated method stub
		BrandExample example=new BrandExample();
		example.createCriteria().andStatusEqualTo(status);
		return brandMapper.selectByExample(example);
		
	}
}
