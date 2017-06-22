package bz.sunlight.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.entity.KeyValueCategory;
import bz.sunlight.entity.KeyValueItem;
import bz.sunlight.service.KeyValueService;
import bz.sunlight.util.UUIDUtil;

@Controller
@RequestMapping("/admin/")
public class KeyValueController {

	@Autowired
	private KeyValueService keyValueService;
	
	@RequestMapping(value="category",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<KeyValueCategory>> queryCategory(String categoryName){
		List<KeyValueCategory> keyValueCategorys=null;
		if(categoryName!=null){
			try {
				categoryName=new String(categoryName.getBytes(BaseConstant.ISO_8859_1),BaseConstant.UTF_8);
				keyValueCategorys = keyValueService.selectKeyValueCategory(categoryName,BaseConstant.BASE_TRUE);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			keyValueCategorys=keyValueService.selectKeyValueCategory(BaseConstant.BASE_TRUE);
		}
		return new ResponseEntity<List<KeyValueCategory>>(keyValueCategorys,HttpStatus.OK);
		
	}
	

	
	@RequestMapping(value="category",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> addCategory(@RequestBody KeyValueCategory keyValueCategory){
		keyValueCategory.setId(UUIDUtil.getOrigUUID());
		keyValueCategory.setCreateTime(new Date());
		keyValueCategory.setStatus(BaseConstant.BASE_TRUE);
		keyValueCategory.setIsBuiltIn(BaseConstant.IS_BUILT_IN_YES);
		keyValueService.add(keyValueCategory);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="item",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> add(@RequestBody KeyValueItem keyValueItem){
		KeyValueCategory category = keyValueService.selectCategory(keyValueItem.getCategoryId());
		int dictKey = keyValueService.selectMax(keyValueItem.getCategoryId())+1;
		keyValueItem.setId(UUIDUtil.getOrigUUID());
		keyValueItem.setDictKey((short)dictKey);
		if(category==null){
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		keyValueItem.setCategoryId(category.getId());
		keyValueItem.setName(category.getCode());
		keyValueItem.setCaption(category.getName());
		keyValueItem.setStatus(BaseConstant.BASE_TRUE);
		keyValueItem.setCreateTime(new Date());
		keyValueItem.setIsBuiltIn(category.getIsBuiltIn());
		keyValueService.add(keyValueItem);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="item",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Void> editItem(@RequestBody KeyValueItem keyValueItem){
		KeyValueItem item = keyValueService.selectKeyValueItem(keyValueItem.getId());
		if(item!=null){
			item.setDictText(keyValueItem.getDictText());
			keyValueService.update(item);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="item/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> delete(@PathVariable String id){
		KeyValueItem item = keyValueService.selectKeyValueItem(id);
		item.setStatus(BaseConstant.BASE_FALSE);
		if(item!=null){
			keyValueService.delete(item);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="item/{categoryId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<KeyValueItem>> selectByCategory(@PathVariable String categoryId){
		List<KeyValueItem> keyValueItems = keyValueService.selectKeyValueItemByCategory(categoryId,BaseConstant.BASE_TRUE);
		return new ResponseEntity<List<KeyValueItem>>(keyValueItems,HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value="enum",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map> queryAll(HttpServletResponse response){
		
		HashMap<String, Object> itemMap = new HashMap<String, Object>();
		List<KeyValueCategory> keyValueCategorys = keyValueService.selectKeyValueCategory(BaseConstant.BASE_TRUE);
		if(keyValueCategorys!=null&&keyValueCategorys.size()>0){
			for (KeyValueCategory keyValueCategory : keyValueCategorys) {
				List<KeyValueItem> items = keyValueService.selectKeyValueItemByCategory(keyValueCategory.getId());
				itemMap.put(keyValueCategory.getCode(), items);
			}
		}
		return new ResponseEntity<Map>(itemMap,HttpStatus.OK);
		
	}
	
}

