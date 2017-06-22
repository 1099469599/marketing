package bz.sunlight;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.entity.KeyValueCategory;
import bz.sunlight.service.KeyValueService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-service.xml","classpath:spring-mybatis.xml"})
public class KeyValueServiceTest {

	@Autowired
	private KeyValueService keyValueService;
	@Test
	public void getItem(){
//		List<KeyValueItem> items = keyValueService.selectAllKeyValueItem();
		List<KeyValueCategory> keyValueCategorys = keyValueService.selectKeyValueCategory(BaseConstant.BASE_TRUE);
		System.out.println(keyValueCategorys);
	}
}
