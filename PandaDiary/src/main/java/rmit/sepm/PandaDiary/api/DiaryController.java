package rmit.sepm.PandaDiary.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rmit.sepm.PandaDiary.pojo.ExecuteResult;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@RestController
@RequestMapping(value="diary")
public class DiaryController {
	
	@RequestMapping(value="getParameters", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult getDiaryParameters() {
		
		ExecuteResult result = new ExecuteResult();
		
		return result;
	}

}
