package bz.sunlight.service;

import bz.sunlight.entity.BusinessRule;

public interface RuleService {

	BusinessRule query();
	
	void add(BusinessRule rule);
	
	void update(BusinessRule rule);
}
