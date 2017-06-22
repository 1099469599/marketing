package bz.sunlight.trigger;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bz.sunlight.constant.BaseConstant;
import bz.sunlight.entity.KeepSecret;
import bz.sunlight.service.CallService;
import bz.sunlight.util.DateUtil;

@Component
public class BusinessGenerator {

	@Autowired
	private CallService callService;

	public void reAddBusinessQueue() {
		Date currentTime = new Date();
		Date start = DateUtil.startOfCurrentDay(currentTime);
		Date end = DateUtil.endOfCurrentDay(currentTime);
		List<KeepSecret> keepSecrets = callService.getKeepSecretByTime(start, end);
		for (KeepSecret keepSecret : keepSecrets) {
			// 对当日未释放的保留区密号再次加入任务队列以便开展第二天的收费
			if (keepSecret.getFreedTime() == null) {
				callService.saveBusinessQueue(keepSecret.getId(), BaseConstant.CONSUME_TYPE_KEEP);
			}
		}
	}
	
}
