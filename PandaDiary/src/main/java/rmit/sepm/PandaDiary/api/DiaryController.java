package rmit.sepm.PandaDiary.api;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
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
import rmit.sepm.PandaDiary.pojo.DiaryParametersBean;
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

	@RequestMapping(value="deleteDiaryParameters", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ExecuteResult deleteDiaryParameters(@RequestBody DiaryParametersBean deleteParametersBean) {
		
		String userId = deleteParametersBean.getUserId();
		String target = deleteParametersBean.getTarget();
		List<PaperColor> deleteOptions = deleteParametersBean.getDiaryOptions();
		
		ExecuteResult result = new ExecuteResult();

		String message = this.deleteDiaryParametersValidation(target, deleteOptions);
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
		
		switch (target) {
			case Constants.DIARY_PARAMETERS_CC:
				Boolean delCC = diaryService.deleteCoverColors(deleteOptions);
				if (delCC != null) {
					result.setReturnObj(delCC);
				}
				break;
			case Constants.DIARY_PARAMETERS_PT:
				Boolean delPT = diaryService.deletePaperTypes(deleteOptions);
				if (delPT != null) {
					result.setReturnObj(delPT);
				}
				break;
			case Constants.DIARY_PARAMETERS_PC:
				Boolean delPC = diaryService.deletePaperColors(deleteOptions);
				if (delPC != null) {
					result.setReturnObj(delPC);
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
			
			// update the storage of diary parameters
			Constants.DIARY_PARAMS = diaryService.getDiaryParameters(Constants.ALL_DIARY_PARAMETERS);
		}
		
		return result;
	}	
	
	@RequestMapping(value="recoverDiaryParameters", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ExecuteResult recoverDiaryParameters(@RequestBody DiaryParametersBean recoverParametersBean) {
		
		String userId = recoverParametersBean.getUserId();
		String target = recoverParametersBean.getTarget();
		List<PaperColor> recoverOptions = recoverParametersBean.getDiaryOptions();
		
		ExecuteResult result = new ExecuteResult();
		
		String message = this.recoverDiaryParametersValidation(target, recoverOptions);
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
		
		switch (target) {
		case Constants.DIARY_PARAMETERS_CC:
			Boolean recCC = diaryService.recoverCoverColors(recoverOptions);
			if (recCC != null) {
				result.setReturnObj(recCC);
			}
			break;
		case Constants.DIARY_PARAMETERS_PT:
			Boolean recPT = diaryService.recoverPaperTypes(recoverOptions);
			if (recPT != null) {
				result.setReturnObj(recPT);
			}
			break;
		case Constants.DIARY_PARAMETERS_PC:
			Boolean recPC = diaryService.recoverPaperColors(recoverOptions);
			if (recPC != null) {
				result.setReturnObj(recPC);
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
			
			// update the storage of diary parameters
			Constants.DIARY_PARAMS = diaryService.getDiaryParameters(Constants.ALL_DIARY_PARAMETERS);
		}
		
		return result;
	}	
	
	private String recoverDiaryParametersValidation(String target, List<PaperColor> recoverOptions) {
		String message = "";
		
		if (!ArrayUtils.contains(Constants.ALL_DIARY_PARAMETERS, target)) {
			message = "Unvalid target.";
		}
		
		if (recoverOptions == null || recoverOptions.size() == 0) {
			if (!StringUtils.isBlank(message))
				message = message + " ";
			message = message + "No option to recover.";
		}
		return message;
	}

	private String deleteDiaryParametersValidation(String target, List<PaperColor> deleteOptions) {
		
		String message = "";
		
		if (!ArrayUtils.contains(Constants.ALL_DIARY_PARAMETERS, target)) {
			message = "Unvalid target.";
		}
		
		if (deleteOptions == null || deleteOptions.size() == 0) {
			if (!StringUtils.isBlank(message))
				message = message + " ";
			message = message + "No option to delete.";
		}
		return message;
	}

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
			
			// update the storage of diary parameters
			Constants.DIARY_PARAMS = diaryService.getDiaryParameters(Constants.ALL_DIARY_PARAMETERS);
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
