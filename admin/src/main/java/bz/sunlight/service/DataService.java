package bz.sunlight.service;

import java.util.List;

import bz.sunlight.entity.ADIDRequest;
import bz.sunlight.entity.AdPool;
import bz.sunlight.entity.ImportData;
import bz.sunlight.entity.SecretPool;

public interface DataService {

	public void importAdPool(AdPool adPool);
	
	public AdPool getAd(String ad);
	
	public void updateAdPool(AdPool adPool);
	
	public SecretPool getSecretPool(String ad,String secretId);
	
	public void updateSecretPool(SecretPool secretPool);
	
	public void importSecretPool(SecretPool secretPool);
	
    int insertImportData(ADIDRequest request,String desPath);
	
	List<ImportData> getImportDatas(Short handleStatus);
	
	int updateImportData(ImportData importData);
}
