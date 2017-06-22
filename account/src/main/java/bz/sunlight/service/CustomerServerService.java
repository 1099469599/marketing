package bz.sunlight.service;

import bz.sunlight.entity.CustomerServer;

public interface CustomerServerService {

	CustomerServer getById(String id);
	
	void add(CustomerServer customerServer1,CustomerServer customerServer2);
}
