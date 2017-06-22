package bz.sunlight.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.entity.KeyValueCategory;
import bz.sunlight.entity.KeyValueItem;
import bz.sunlight.service.KeyValueService;

@Controller
@RequestMapping("/base/")
public class KeyValueController {

	@Autowired
	private KeyValueService keyValueService;
	
	
	@RequestMapping(value="enum",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> queryAll(HttpServletResponse response){
		
		Map<String, Object> itemMap = new HashMap<String, Object>();
		List<KeyValueCategory> keyValueCategorys = keyValueService.selectKeyValueCategory(BaseConstant.BASE_TRUE);
		if(keyValueCategorys!=null&&keyValueCategorys.size()>0){
			for (KeyValueCategory keyValueCategory : keyValueCategorys) {
				List<KeyValueItem> items = keyValueService.selectKeyValueItemByCategory(keyValueCategory.getId());
				itemMap.put(keyValueCategory.getCode(), items);
			}
		}
		return new ResponseEntity<Map<String, Object>>(itemMap,HttpStatus.OK);
		
	}
	
	
}
