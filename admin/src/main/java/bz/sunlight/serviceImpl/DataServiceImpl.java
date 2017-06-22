package bz.sunlight.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.dao.AdPoolMapper;
import bz.sunlight.dao.ImportDataMapper;
import bz.sunlight.dao.SecretPoolMapper;
import bz.sunlight.entity.ADIDRequest;
import bz.sunlight.entity.AdPool;
import bz.sunlight.entity.AdPoolExample;
import bz.sunlight.entity.ImportData;
import bz.sunlight.entity.ImportDataExample;
import bz.sunlight.entity.SecretPool;
import bz.sunlight.entity.SecretPoolExample;
import bz.sunlight.service.DataService;
import bz.sunlight.util.BeanUtil;
import bz.sunlight.util.HttpUtil;
import bz.sunlight.util.UUIDUtil;
@Service("dataService")
public class DataServiceImpl implements DataService{

	@Resource
	private AdPoolMapper adPoolMapper;
	@Resource
	private SecretPoolMapper secretPoolMapper;
	
	@Resource
	private ImportDataMapper importDataMapper;
	
	@Override
	public void importAdPool(AdPool adPool) {
		// TODO Auto-generated method stub
		adPoolMapper.insert(adPool);
	}

	@Override
	public AdPool getAd(String ad) {
		AdPoolExample example=new AdPoolExample();
		example.createCriteria().andAdEqualTo(ad);
		List<AdPool> adPools = adPoolMapper.selectByExample(example);
		if(adPools!=null&&adPools.size()>0){
			return adPools.get(0);
		}
		return null;
	}
	
	@Override
	public void importSecretPool(SecretPool secretPool) {
		// TODO Auto-generated method stub
		secretPoolMapper.insert(secretPool);
	}

	@Override
	public void updateAdPool(AdPool adPool) {
		adPoolMapper.updateByPrimaryKeySelective(adPool);
		
	}

	@Override
	public SecretPool getSecretPool(String ad, String secretId) {
		SecretPoolExample example=new SecretPoolExample();
		example.createCriteria().andAdEqualTo(ad).andSecretIdEqualTo(secretId);
		List<SecretPool> secretPools = secretPoolMapper.selectByExample(example);
		if(secretPools!=null&&secretPools.size()>0){
			return secretPools.get(0);
		}
		return null;
	}

	@Override
	public void updateSecretPool(SecretPool secretPool) {
		// TODO Auto-generated method stub
		secretPoolMapper.updateByPrimaryKeySelective(secretPool);
	}
	
	@Override
	public int insertImportData(ADIDRequest request,String desPath) {
		Integer resultCode = HttpUtil.downloadFromServer(request.getContentMngTXTURL(), desPath);
		if(resultCode==BaseConstant.RESULT_CODE_SUCCESS){
			ImportData importData = new ImportData();
			BeanUtil.copyProperties(importData, request);
			importData.setId(UUIDUtil.getOrigUUID());
			importData.setHandleStatus(BaseConstant.HANDLE_STATUS_NO);
			importData.setFileUrl(desPath);
			importData.setCreateTime(new Date());
			importDataMapper.insert(importData);
			return resultCode;
		}
		return resultCode;
	}

	@Override
	public int updateImportData(ImportData importData) {
		return importDataMapper.updateByPrimaryKeySelective(importData);
		
	}

	@Override
	public List<ImportData> getImportDatas(Short handleStatus) {
		ImportDataExample example=new ImportDataExample();
		example.createCriteria().andHandleStatusEqualTo(handleStatus);
		return importDataMapper.selectByExample(example);
		}



}
