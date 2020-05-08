package rmit.sepm.PandaDiary.api;

import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rmit.sepm.PandaDiary.constants.Constants;
import rmit.sepm.PandaDiary.entity.CoverColor;
import rmit.sepm.PandaDiary.entity.PaperColor;
import rmit.sepm.PandaDiary.entity.PaperType;
import rmit.sepm.PandaDiary.entity.User;
import rmit.sepm.PandaDiary.pojo.ExecuteResult;
import rmit.sepm.PandaDiary.services.DiaryService;
import rmit.sepm.PandaDiary.services.UserService;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@RestController
@RequestMapping(value="diary")
public class DiaryController {
	
	@Autowired
	DiaryService diaryService;
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="updateDiaryParameters", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult updateDiaryParameters(@RequestParam(value="userId")String userId, @RequestParam(value="target") String target, @RequestParam(value="description") String description) {
		
		ExecuteResult result = new ExecuteResult();
		
		String message = this.updateDiaryParametersValidation(target, description);
		if (!StringUtils.isBlank(message)) {
			result.setResultCode(1);
			result.setReturnObj(message);
			return result;
		}
		
		if (StringUtils.isBlank(userId)) {
			result.setResultCode(1);
			result.setReturnObj("Unvalid user");
			return result;
		}
		
		User user = userService.findByUserId(userId);
		
		if (user == null || !StringUtils.equals(userId.substring(0,1).toLowerCase(), Constants.ROLE_ADMIN)) {
			result.setResultCode(1);
			result.setReturnObj("Unvalid user.");
			return result;
		}
		
		description = StringUtils.capitalize(description);
		
		switch (target) {
			case Constants.DIARY_PARAMETERS_CC:
				CoverColor addCC = diaryService.addCoverColors(description);
				if (addCC != null) {
					result.setReturnObj(addCC);
				}
				break;
			case Constants.DIARY_PARAMETERS_PT:
				PaperType addPT = diaryService.addPaperTypes(description);
				if (addPT != null) {
					result.setReturnObj(addPT);
				}
				break;
			case Constants.DIARY_PARAMETERS_PC:
				PaperColor addPC = diaryService.addPaperColors(description);
				if (addPC != null) {
					result.setReturnObj(addPC);
				}
				break;
			default:
				result.setReturnObj("Something Wrong. Please contact with system administrator.");
				break;
		}
		
		if (result.getReturnObj()==null) {
			result.setResultCode(2);
		}else {
			result.setResultCode(0);
		}
		
		return result;
	}
	
	private String updateDiaryParametersValidation(String target, String description) {
		String message = "";
		
		if (!ArrayUtils.contains(Constants.ALL_DIARY_PARAMETERS, target)) {
			message = "Unvalid target.";
		}
		
		if (StringUtils.isBlank(description)) {
			if (!StringUtils.isBlank(message))
				message = message + " ";
			message = message + "No option description.";
		}
		return message;
	}

	@RequestMapping(value="getParameters", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody ExecuteResult getDiaryParameters() {
		
		ExecuteResult result = new ExecuteResult();
		
		if (Constants.DIARY_PARAMS == null) {
			Map<String, Object> parameters = diaryService.getDiaryParameters(Constants.ALL_DIARY_PARAMETERS);
			
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
