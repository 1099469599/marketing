package bz.sunlight.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bz.sunlight.dao.CustomerServerMapper;
import bz.sunlight.entity.CustomerServer;
import bz.sunlight.service.CustomerServerService;

@Service("CustomerServerService")
public class CustomerServerServiceImpl implements CustomerServerService{

	@Resource
	private CustomerServerMapper customerServerMapper;
	
	@Override
	public CustomerServer getById(String id) {
		return customerServerMapper.selectByPrimaryKey(id);
		
	}

	@Override
	@Transactional
	public void add(CustomerServer customerServer1,
			CustomerServer customerServer2) {
		// TODO Auto-generated method stub
		customerServerMapper.insertSelective(customerServer1);
		int i=100/0;
		customerServerMapper.updateByPrimaryKey(customerServer2);
	}

}
