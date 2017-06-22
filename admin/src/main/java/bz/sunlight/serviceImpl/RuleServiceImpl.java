package bz.sunlight.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import bz.sunlight.dao.BusinessRuleMapper;
import bz.sunlight.entity.BusinessRule;
import bz.sunlight.entity.BusinessRuleExample;
import bz.sunlight.service.RuleService;
@Service("ruleService")
public class RuleServiceImpl implements RuleService{

	@Resource
	private BusinessRuleMapper businessRuleMapper;
	
	@Override
	public BusinessRule query() {
		BusinessRuleExample example=new BusinessRuleExample();
		List<BusinessRule> businessRules = businessRuleMapper.selectByExample(example);
		if(businessRules!=null&&businessRules.size()>0){
			return businessRules.get(0);
		}
		return null;
	}

	@Override
	public void add(BusinessRule rule) {
		// TODO Auto-generated method stub
		businessRuleMapper.insert(rule);
	}

	@Override
	public void update(BusinessRule rule) {
		// TODO Auto-generated method stub
		businessRuleMapper.updateByPrimaryKeySelective(rule);
	}

}
