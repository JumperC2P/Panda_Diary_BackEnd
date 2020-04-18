package rmit.sepm.PandaDiary.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rmit.sepm.PandaDiary.constants.Constants;
import rmit.sepm.PandaDiary.pojo.ExecuteResult;
import rmit.sepm.PandaDiary.services.DiaryService;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@RestController
@RequestMapping(value="diary")
public class DiaryController {
	
	@Autowired
	DiaryService dairyService;
	
	@RequestMapping(value="getParameters", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody ExecuteResult getDiaryParameters() {
		
		ExecuteResult result = new ExecuteResult();
		
		if (Constants.DIARY_PARAMS == null) {
			Map<String, Object> parameters = dairyService.getDiaryParameters(Constants.ALL_DIARY_PARAMETERS);
			
			if (parameters == null) {
				result.setResultCode(1);
				result.setReturnObj("Failed to get parameters for diary customization.");
			}else {
				result.setResultCode(0);
				result.setReturnObj(parameters);
			}
		}else {
			result.setResultCode(0);
			result.setReturnObj(Constants.DIARY_PARAMS);
		}
		
		
		
		return result;
	}

	@RequestMapping(value="getParameters", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ExecuteResult getDiaryParameters_GET() {
		return getDiaryParameters();
	}
	
}
