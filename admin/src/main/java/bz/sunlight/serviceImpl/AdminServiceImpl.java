package bz.sunlight.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import bz.sunlight.dao.AdminInfoMapper;
import bz.sunlight.entity.AdminInfo;
import bz.sunlight.entity.AdminInfoExample;
import bz.sunlight.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Resource
	private AdminInfoMapper adminInfoMapper;
	@Override
	public AdminInfo get(String userName, String password, Short status) {
		AdminInfoExample example=new AdminInfoExample();
		example.createCriteria().andUserNameEqualTo(userName).andPasswordEqualTo(password).andStatusEqualTo(status);
		List<AdminInfo> adminInfos = adminInfoMapper.selectByExample(example);
		if(adminInfos!=null&&adminInfos.size()>0){
			return adminInfos.get(0);
		}
		return null;
	}
	@Override
	public AdminInfo get(String id, Short status) {
		// TODO Auto-generated method stub
		AdminInfoExample example=new AdminInfoExample();
		example.createCriteria().andIdEqualTo(id).andStatusEqualTo(status);
		List<AdminInfo> adminInfos = adminInfoMapper.selectByExample(example);
		if(adminInfos!=null&adminInfos.size()>0){
			return adminInfos.get(0);
		}
		return null;
	}
	
	

}
