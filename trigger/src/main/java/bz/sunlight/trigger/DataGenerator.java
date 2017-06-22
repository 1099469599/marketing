package bz.sunlight.trigger;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.entity.AdPool;
import bz.sunlight.entity.ImportData;
import bz.sunlight.entity.SecretPool;
import bz.sunlight.service.DataService;
import bz.sunlight.util.UUIDUtil;

@Component
public class DataGenerator {

	@Autowired
	private DataService dataService;
	
    /**
     * 读取数据库中未处理的文件
     */
     
	public void importData(){
		List<ImportData> importDatas = dataService.getImportDatas(BaseConstant.HANDLE_STATUS_NO);
		if(importDatas!=null&&importDatas.size()>0){
			for (ImportData importData : importDatas) {
				List<String> datas=null;
				try {
					datas = FileUtils.readLines(new File(importData.getFileUrl()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (String str : datas) {
					String[] strs = str.split("\\|");
					String secretId = strs[0];
					String ad = strs[1];
					String industryTag6=strs[2];
					AdPool adPool = dataService.getAd(ad);
					if (adPool == null) {
						throw new RuntimeException("没有对应的AD号");
					}
					SecretPool secretPool = dataService.getSecretPool(ad,
							secretId);
					if (secretPool != null) {
						InitSecretPool(adPool, secretPool);
						dataService.updateSecretPool(secretPool);
					} else {
						secretPool = new SecretPool();
						InitSecretPool(adPool, secretPool);
						secretPool.setIndustryTag6(industryTag6);
						secretPool.setId(UUIDUtil.getOrigUUID());
						secretPool.setAd(ad);
						secretPool.setImportTime(new Date());
						secretPool
								.setKeepStatus(BaseConstant.KEEP_STATUS_PUBLIC);
						secretPool.setSecretId(secretId);
						secretPool.setStatus(BaseConstant.BASE_TRUE);
						dataService.importSecretPool(secretPool);
					}
				}
//				更新数据库处理状态为已处理
				importData.setHandleStatus(BaseConstant.HANDLE_STATUS_YES);
				dataService.updateImportData(importData);
				
			}
		}
	}
	
	private void InitSecretPool(AdPool adPool, SecretPool secretPool) {
		secretPool.setIndustryTag1(adPool.getIndustryTag1());
		secretPool.setIndustryTag2(adPool.getIndustryTag2());
		secretPool.setIndustryTag3(adPool.getIndustryTag3());
		secretPool.setIndustryTag4(adPool.getIndustryTag4());
		secretPool.setIndustryTag5(adPool.getIndustryTag5());
	}
}
