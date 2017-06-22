package bz.sunlight.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.entity.AdPool;
import bz.sunlight.entity.CallRecordVO;
import bz.sunlight.entity.SecretPool;
import bz.sunlight.excel.util.AbstractXlsxReader;
import bz.sunlight.excel.util.ExcelTools;
import bz.sunlight.excel.util.RowReader;
import bz.sunlight.excel.util.SimpleRowReader;
import bz.sunlight.excel.util.SimpleXlsxReader;
import bz.sunlight.excel.util.ExcelTools.ExcelType;
import bz.sunlight.service.DataService;
import bz.sunlight.util.GsonUtil;
import bz.sunlight.util.HttpUtil;
import bz.sunlight.util.UUIDUtil;

@Controller
@RequestMapping("/admin/")
public class DataController {
	
	private String exceladUrl = "C:/Users/Administrator/Desktop/excelad.xlsx";
	private String excelSecretUrl = "C:/Users/Administrator/Desktop/excelSecret.xlsx";
	private String csvUrl = "C:/Users/Administrator/Desktop/data.csv";
//	private final static String requestUrl="http://localhost:8080/web/download/data.csv";
//	private final static String desPath="D:/temp/importData";
	@Autowired
	private DataService dataService;


	@RequestMapping(value = "ad/excel", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Void> importData() throws FileNotFoundException {
		List<List<String>> excelData = ExcelTools.readExcel(
				new File(exceladUrl), ExcelType.EXCEL2007);

		if (excelData != null) {
			for (List<String> list : excelData) {
				for (String str : list) {
					JSONObject json = GsonUtil.fromJson(str, JSONObject.class);
					String ad = json.getString("AD");
					String brand = json.getString("brand");
					String area = json.getString("area");
					String lowprice = json.getString("lowprice");
					String highprice = json.getString("highprice");
					AdPool adPool = dataService.getAd(ad);
					if (adPool != null) {
						initAdPool(brand, area, lowprice, highprice, adPool);
						dataService.updateAdPool(adPool);
					} else {
						adPool = new AdPool();
						adPool.setId(UUIDUtil.getOrigUUID());
						adPool.setAd(ad);
						initAdPool(brand, area, lowprice, highprice, adPool);
						adPool.setCreateTime(new Date());
						adPool.setStatus(BaseConstant.BASE_TRUE);
						dataService.importAdPool(adPool);
					}
				}

			}
		} 
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private void initAdPool(String brand, String area, String lowprice,
			String highprice, AdPool adPool) {
		adPool.setIndustryTag1(brand);
		adPool.setIndustryTag2(area);
		adPool.setIndustryTag3(lowprice);
		adPool.setIndustryTag4(highprice);
	}

	@RequestMapping(value = "secret/excel", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Void> importSecretPool() throws FileNotFoundException {
		List<List<String>> excelData = ExcelTools.readExcel(new File(
				excelSecretUrl), ExcelType.EXCEL2007);
		if (excelData != null) {
			for (List<String> list : excelData) {
				for (String str : list) {
					JSONObject json = GsonUtil.fromJson(str, JSONObject.class);
					String ad = json.getString("AD");
					String secretId = json.getString("ID");
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

			}
		} 
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
//	从服务器下载文件到项目中
	@RequestMapping(value = "secret/download", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Integer> download(String requestUrl,HttpServletRequest request){
		String desPath=request.getRealPath(request.getContextPath()+"/WEB-INF/download");
		System.out.println("=============="+desPath);
		Integer code = HttpUtil.downloadFromServer(requestUrl, desPath);
		return new ResponseEntity<Integer>(code,HttpStatus.OK);
	}
	
	@RequestMapping(value = "secret/csv", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Void> parseCsv() throws FileNotFoundException {
		try {
			List<String> datas = FileUtils.readLines(new File(csvUrl));
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
			System.out.println(datas);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("文件不存在");
			e.printStackTrace();
		}
 
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	private void InitSecretPool(AdPool adPool, SecretPool secretPool) {
		secretPool.setIndustryTag1(adPool.getIndustryTag1());
		secretPool.setIndustryTag2(adPool.getIndustryTag2());
		secretPool.setIndustryTag3(adPool.getIndustryTag3());
		secretPool.setIndustryTag4(adPool.getIndustryTag4());
		secretPool.setIndustryTag5(adPool.getIndustryTag5());
	}
}
