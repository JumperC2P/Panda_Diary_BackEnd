package rmit.sepm.PandaDiary.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import rmit.sepm.PandaDiary.constants.Constants;
import rmit.sepm.PandaDiary.services.DiaryService;

@Component
@Order(value=1)
public class DataLoader implements ApplicationRunner{
	
	@Autowired
	DiaryService diaryService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Constants.PARAMS = diaryService.getDiaryParameters(Constants.ALL_DIARY_PARAMETERS);
	}

}
