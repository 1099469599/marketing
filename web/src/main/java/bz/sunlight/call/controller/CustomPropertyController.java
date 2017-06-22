package bz.sunlight.call.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.common.BaseController;
import bz.sunlight.constant.BaseConstant;
import bz.sunlight.constant.HttpConstant;
import bz.sunlight.dto.CustomPropertyDTO;
import bz.sunlight.entity.CustomProperties;
import bz.sunlight.service.PropertyService;
import bz.sunlight.util.BeanUtil;
import bz.sunlight.util.UUIDUtil;

@Controller
@RequestMapping("/call/")
public class CustomPropertyController extends BaseController{

	@Autowired
	private PropertyService propertyService;
	
	@RequestMapping(value="custom_properties",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> add(@RequestBody CustomPropertyDTO customPropertyDTO,
			@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId){
		CustomProperties customProperties = new CustomProperties();
		BeanUtil.copyProperties(customProperties, customPropertyDTO);
		customProperties.setCreateTime(new Date());
		customProperties.setId(UUIDUtil.getOrigUUID());
		customProperties.setEnterpriseId(enterpriseId);
		customProperties.setStatus(BaseConstant.BASE_TRUE);
		int count = propertyService.getCount(customProperties);
		customProperties.setCode(count++);
		propertyService.add(customProperties);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="custom_properties",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Void> edit(@RequestBody CustomPropertyDTO customPropertyDTO){
		CustomProperties customProperties = new CustomProperties();
		BeanUtil.copyProperties(customProperties, customPropertyDTO);
		propertyService.updateById(customProperties);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="custom_properties",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String,List<CustomProperties>>> queryAll(@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId){
		Map<String,List<CustomProperties>> map=new HashMap<String,List<CustomProperties>>();
		List<CustomProperties> reserveOne = propertyService.queryAll(BaseConstant.BASE_TRUE,enterpriseId,BaseConstant.CUSTOM_PROPERTIES_STATUS_RESERVE,BaseConstant.PROPERTY_TYPE_ONE);
		List<CustomProperties> reserveTwo = propertyService.queryAll(BaseConstant.BASE_TRUE,enterpriseId,BaseConstant.CUSTOM_PROPERTIES_STATUS_RESERVE,BaseConstant.PROPERTY_TYPE_TWO);
		List<CustomProperties> callOne = propertyService.queryAll(BaseConstant.BASE_TRUE,enterpriseId,BaseConstant.CUSTOM_PROPERTIES_STATUS_CALL_RECORD,BaseConstant.PROPERTY_TYPE_ONE);
		List<CustomProperties> callTwo = propertyService.queryAll(BaseConstant.BASE_TRUE,enterpriseId,BaseConstant.CUSTOM_PROPERTIES_STATUS_CALL_RECORD,BaseConstant.PROPERTY_TYPE_TWO);
		map.put("reserveOne", reserveOne);
		map.put("reserveTwo", reserveTwo);
		map.put("callOne", callOne);
		map.put("callTwo", callTwo);
		return new ResponseEntity<Map<String,List<CustomProperties>>>(map,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="custom_properties/all",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<CustomProperties>> queryCustomPropertiesAll(@CookieValue(HttpConstant.AIM_MARKETING_ENTERPRISEID_COOKIE) String enterpriseId){
		List<CustomProperties> customProperties = propertyService.queryCustomPropertiesAll(BaseConstant.BASE_TRUE,enterpriseId);
		return new ResponseEntity<List<CustomProperties>>(customProperties,HttpStatus.OK);
	}
}
