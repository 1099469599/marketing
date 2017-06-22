package bz.sunlight.service;

import bz.sunlight.entity.AdminInfo;

public interface AdminService {

	AdminInfo get(String userName,String password,Short status);
	
	AdminInfo get(String id,Short status);
}
