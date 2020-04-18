package rmit.sepm.PandaDiary.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import rmit.sepm.PandaDiary.constants.Constants;
import rmit.sepm.PandaDiary.services.DiaryService;
import rmit.sepm.PandaDiary.services.OrderService;

@Component
@Order(value=1)
public class DataLoader implements ApplicationRunner{
	
	@Autowired
	DiaryService diaryService;
	
	@Autowired
	OrderService orderService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Constants.DIARY_PARAMS = diaryService.getDiaryParameters(Constants.ALL_DIARY_PARAMETERS);
		
		Constants.PURCHASE_OPTIONS = orderService.getPurchaseOptions();
		
		Constants.DELIVERY_OPTIONS = orderService.getDeliveryOptions();
	}

}
