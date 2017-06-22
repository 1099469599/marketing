package bz.sunlight.service;

import java.util.List;

import bz.sunlight.entity.DefaultLangugeTrick;
import bz.sunlight.entity.LanguageTrick;

public interface LanguageService {

	void add(LanguageTrick languageTrick);
	
	List<LanguageTrick> select(String enterpriseId,Short status);
	
	LanguageTrick selectById(String id,Short status);
	
	DefaultLangugeTrick selectDefault(String userInfoId);
	
	void updateDefault(DefaultLangugeTrick defaultLangugeTrick);
	
	void addDefault(DefaultLangugeTrick defaultLangugeTrick);
	
	void delete(LanguageTrick languageTrick);
	
	void update(LanguageTrick languageTrick);
	
	
	
} 
