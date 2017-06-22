package bz.sunlight.call.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.common.BaseController;
import bz.sunlight.constant.BaseConstant;
import bz.sunlight.constant.HttpConstant;
import bz.sunlight.dto.CallExcutionDTO;
import bz.sunlight.dto.CallRecordDTO;
import bz.sunlight.dto.KeepSecretDTO;
import bz.sunlight.entity.Brand;
import bz.sunlight.entity.CallRecord;
import bz.sunlight.entity.CallRecordVO;
import bz.sunlight.entity.CustomProperties;
import bz.sunlight.entity.Customer;
import bz.sunlight.entity.KeepSecret;
import bz.sunlight.entity.LanguageTrick;
import bz.sunlight.entity.SecretPool;
import bz.sunlight.service.BrandService;
import bz.sunlight.service.LanguageService;
import bz.sunlight.service.PropertyService;
import bz.sunlight.util.BeanUtil;
import bz.sunlight.util.DateUtil;
import bz.sunlight.util.PageUtil;
import bz.sunlight.util.StringUtil;
import bz.sunlight.vo.CustomerVo;

@Controller
@RequestMapping("/call/")
public class CallRecordController extends BaseController{

	@Autowired
	private LanguageService languageService;
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private BrandService brandService;

	
	@RequestMapping(value="record",method=RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<CallRecordVO> record(@CookieValue(HttpConstant.AIM_MARKETING_USERID_COOKIE) String userId, 
			Short callResult, String languageTrickId, Date startTime, Date endTime, 
			Integer pageSize, Integer currentPage){
		if (pageSize != null && currentPage != null) {
			CallRecordDTO callRecordDTO = new CallRecordDTO(userId, callResult, languageTrickId, startTime, endTime, pageSize, currentPage);
			int totalItemCount = callService.getRecordsCount(callRecordDTO);
			CallRecordVO callRecordVO = new CallRecordVO();
			if (totalItemCount != 0) {
				callRecordVO.setTotalItemCount(totalItemCount);
				callRecordVO.setTotalPage(PageUtil.getTotalPage(totalItemCount, pageSize));
				List<CallRecord> callRecords = callService.getCallRecordsByPagination(callRecordDTO);
				if (callRecords.size() != 0) {
					List<CallRecordVO.CallRecordDemo> callRecordDemos = new ArrayList<CallRecordVO.CallRecordDemo>();
					for (CallRecord callRecord : callRecords) {
						CallRecordVO.CallRecordDemo callRecordDemo = callRecordVO.new CallRecordDemo();
						BeanUtil.copyProperties(callRecordDemo, callRecord);

						// 录入话述标题
						String trickId = callRecord.getLanguageTrickId();
						if (trickId != null) {
							LanguageTrick trick = languageService.selectById(trickId, BaseConstant.BASE_TRUE);
							if (trick != null) {
								callRecordDemo.setLanguageTrickTitle(trick.getTitle());
							}
						}
						
						// 录入自定义参数值
						CustomProperties customProperties1 = propertyService.queryById(callRecord.getCustomPropertiesId1());
						CustomProperties customProperties2 = propertyService.queryById(callRecord.getCustomPropertiesId2());
						if (customProperties1 != null && customProperties2 != null) {
							callRecordDemo.setCustomPropertiesValue1(customProperties1.getValue());
							callRecordDemo.setCustomPropertiesValue2(customProperties2.getValue());
						}

						callRecordDemos.add(callRecordDemo);
					}
					callRecordVO.setCallRecords(callRecordDemos);
				}
			}
			
			return new ResponseEntity<CallRecordVO>(callRecordVO,HttpStatus.OK);
		} else {
			return new ResponseEntity<CallRecordVO>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
	@RequestMapping(value="record",method=RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<Void> addRecord(@CookieValue(HttpConstant.AIM_MARKETING_USERID_COOKIE) String userId,
		@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId,@RequestBody CallExcutionDTO callExcutionDTO){
		if(StringUtils.isNotBlank(callExcutionDTO.getAppointmentTime())){
			callExcutionDTO.setAppointmentDate(DateUtil.stringToDate(callExcutionDTO.getAppointmentTime(), BaseConstant.DATE_FORMAT_YEAR_TO_DAY));
		}
		callService.callExcute(userId,enterpriseId,callExcutionDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="customer/{secretId}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CustomerVo> queryCustomer(@PathVariable String secretId,@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId){
			Customer customer = callService.getCustomer(secretId, enterpriseId);
			CustomerVo customerVo=null;
			if(customer!=null){
				customerVo=new CustomerVo();
				BeanUtil.copyProperties(customerVo, customer);
				customerVo.setCustomerRemark(customer.getRemark());
			}
			return new ResponseEntity<CustomerVo>(customerVo,HttpStatus.OK);
			
	}
	
	@RequestMapping(value="customer",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> queryCustomerPage(Short currentPage,Short pageSize,
    		@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId,
		String name,String mobile,String appointmentDate){
		name=StringUtil.isoStringToU8String(name);
		Map<String,Object> map=null;
		Date appointmentTime=null;
		if(appointmentDate!=null){
			appointmentTime=DateUtil.stringToDate(appointmentDate, BaseConstant.DATE_FORMAT_YEAR_TO_DAY);
		}
		int customerCount = callService.getCustomerCount(name, mobile, appointmentTime, enterpriseId);
		int pageItemStart = PageUtil.getPageItemStart(currentPage, pageSize);
		List<Customer> customers = callService.getCustomer(pageItemStart, pageSize, name, mobile, appointmentTime,enterpriseId);
		if(customers!=null){
			map=new HashMap<String,Object>();
			map.put("itemCount", customerCount);
			map.put("customers", customers);
		}
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="brand",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Brand>> queryBrand(){
		List<Brand> brands = brandService.getBrand(BaseConstant.BASE_TRUE);
		return new ResponseEntity<List<Brand>>(brands,HttpStatus.OK);
	}
	
	@RequestMapping(value="secretPool/public",method=RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<Map<String, Object>> querySecretPool(String brandName,String area,Short callStatus,
			Short currentPage,Integer pageSize,@CookieValue(HttpConstant.AIM_MARKETING_USERID_COOKIE) String userId){
		Map<String, Object> map=new HashMap<String, Object>();
		brandName=null;
		area=StringUtil.isoStringToU8String(area);
		int itemCount = callService.secretPoolCount(BaseConstant.KEEP_STATUS_PUBLIC, BaseConstant.BASE_TRUE, brandName,userId,area,callStatus);
		int pageItemStart = PageUtil.getPageItemStart(currentPage, pageSize);
		List<SecretPool> secretPools = callService.getSecretPool(BaseConstant.KEEP_STATUS_PUBLIC, BaseConstant.BASE_TRUE, brandName, userId,
				area, pageItemStart, pageSize, callStatus);
		map.put("itemCount", itemCount);
		map.put("secretPools", secretPools);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="secretPool/private",method=RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<Map<String, Object>> queryKeepSecret(@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId,
			@RequestBody KeepSecretDTO keepSecretDTO){
			Date keepDate=null;
		    if(StringUtils.isNoneBlank(keepSecretDTO.getKeepTime())){
		    	keepDate = DateUtil.stringToDate(keepSecretDTO.getKeepTime(), BaseConstant.DATE_FORMAT_YEAR_TO_DAY);
		    	keepSecretDTO.setKeepDate(keepDate);
		    }
		    keepSecretDTO.setEnterpriseId(enterpriseId);
		    keepSecretDTO.setKeepStatus(BaseConstant.KEEP_STATUS_PRIVATE);
		    keepSecretDTO.setStatus(BaseConstant.BASE_TRUE);
		    int keepSecretCount = callService.getKeepSecretCount(keepSecretDTO);
		    int pageItemStart = PageUtil.getPageItemStart(keepSecretDTO.getCurrentPage(), keepSecretDTO.getPageSize());
		    keepSecretDTO.setPageItemStart(pageItemStart);
		    List<KeepSecret> keepSecrets = callService.getKeepSecret(keepSecretDTO);
		    Map<String, Object> map=null;
		    if(keepSecrets!=null&&keepSecrets.size()>0){
				map=new HashMap<String, Object>();
				map.put("itemCount", keepSecretCount);
				map.put("keepSecrets", keepSecrets);
			}
		    return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
			
	}
	
	@RequestMapping(value="secretPool/release/{id}/{secretId}",method=RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<Void> releaseKeepSecret(@PathVariable String id,@PathVariable String secretId,@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpirseId){
		SecretPool pool=new SecretPool();
		pool.setSecretId(secretId);
		pool.setKeepStatus(BaseConstant.KEEP_STATUS_PUBLIC);
		KeepSecret keepSecret=new KeepSecret();
		keepSecret.setFreedTime(new Date());
		keepSecret.setSecretId(secretId);
		keepSecret.setEnterpirseId(enterpirseId);
		callService.releaseKeepSecret(pool,keepSecret);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}

}
