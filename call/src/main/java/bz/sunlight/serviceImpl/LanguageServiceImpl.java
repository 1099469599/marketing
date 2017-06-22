package bz.sunlight.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import bz.sunlight.dao.DefaultLangugeTrickMapper;
import bz.sunlight.dao.LanguageTrickMapper;
import bz.sunlight.entity.DefaultLangugeTrick;
import bz.sunlight.entity.DefaultLangugeTrickExample;
import bz.sunlight.entity.LanguageTrick;
import bz.sunlight.entity.LanguageTrickExample;
import bz.sunlight.service.LanguageService;
@Service("languageService")
public class LanguageServiceImpl implements LanguageService{

	@Resource
	private LanguageTrickMapper languageTrickMapper;
	@Resource
	private DefaultLangugeTrickMapper defaultLanguageTrickMapper;
	
	@Override
	public void add(LanguageTrick languageTrick) {
		// TODO Auto-generated method stub
		languageTrickMapper.insert(languageTrick);
	}

	@Override
	public List<LanguageTrick> select(String enterpriseId,Short status) {
		LanguageTrick languageTrick=new LanguageTrick();
		languageTrick.setEnterpriseId(enterpriseId);
		languageTrick.setStatus(status);
		return languageTrickMapper.selectLanguageTrick(languageTrick);
	}

	@Override
	public void delete(LanguageTrick languageTrick) {
		// TODO Auto-generated method stub
		languageTrickMapper.updateByPrimaryKey(languageTrick);
	}

	@Override
	public LanguageTrick selectById(String id, Short status) {
		LanguageTrickExample example=new LanguageTrickExample();
		example.createCriteria().andIdEqualTo(id).andStatusEqualTo(status);
		List<LanguageTrick> tricks = languageTrickMapper.selectByExample(example);
		if(tricks!=null&&tricks.size()>0){
			return tricks.get(0);
		}
		return null;
	}

	@Override
	public DefaultLangugeTrick selectDefault(String userInfoId) {
		DefaultLangugeTrickExample example=new DefaultLangugeTrickExample();
		example.createCriteria().andUserInfoIdEqualTo(userInfoId);
		List<DefaultLangugeTrick> defaultLangugeTricks = defaultLanguageTrickMapper.selectByExample(example);
		if(defaultLangugeTricks!=null&&defaultLangugeTricks.size()>0){
			return defaultLangugeTricks.get(0);
		}
		return null;
	}

	@Override
	public void updateDefault(DefaultLangugeTrick defaultLangugeTrick) {
		// TODO Auto-generated method stub
		DefaultLangugeTrickExample example=new DefaultLangugeTrickExample();
		example.createCriteria().andUserInfoIdEqualTo(defaultLangugeTrick.getUserInfoId());
		defaultLanguageTrickMapper.updateByExampleSelective(defaultLangugeTrick, example);
	}

	@Override
	public void addDefault(DefaultLangugeTrick defaultLangugeTrick) {
		// TODO Auto-generated method stub
		defaultLanguageTrickMapper.insert(defaultLangugeTrick);
	}

	@Override
	public void update(LanguageTrick languageTrick) {
		// TODO Auto-generated method stub
		languageTrickMapper.updateByPrimaryKeySelective(languageTrick);
	}

}
