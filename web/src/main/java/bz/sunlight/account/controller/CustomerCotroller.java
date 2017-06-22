package bz.sunlight.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.entity.CustomerServer;
import bz.sunlight.service.CustomerServerService;

@Controller
@RequestMapping("/user/")
public class CustomerCotroller {

	@Autowired
	private CustomerServerService customerServerService;
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	@ResponseBody
	public CustomerServer getUser(@PathVariable String id){
		return customerServerService.getById(id);
	}
	
	@RequestMapping(value="user/{name}",method=RequestMethod.GET)
	@ResponseBody
	public void insertUser(@PathVariable String name){
		CustomerServer customerServer1=new CustomerServer();
		CustomerServer customerServer2=new CustomerServer();
		customerServer1.setId("4");
		customerServer1.setName("sale4");
		customerServer1.setCode("00004");
		customerServer2.setId("3");
		customerServer2.setName("beppe");
		customerServerService.add(customerServer1, customerServer2);
	}
}
