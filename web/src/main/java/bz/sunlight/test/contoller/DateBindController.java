package bz.sunlight.test.contoller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DateBindController {

	@RequestMapping("dateBinder")
	@ResponseBody
	public void getDate(@RequestBody BinderDTO binderDTO){
		System.out.println(binderDTO.getDate1());
	}
}
