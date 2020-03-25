package rmit.sepm.PandaDiary.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rmit.sepm.PandaDiary.entity.User;
import rmit.sepm.PandaDiary.pojo.ExecuteResult;
import rmit.sepm.PandaDiary.pojo.LoginBean;
import rmit.sepm.PandaDiary.services.UserService;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@RestController
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult login(LoginBean loginBean) {
		
		String message = this.validationForLogin(loginBean);
		
		if (!StringUtils.isBlank(message)) {
			ExecuteResult result = new ExecuteResult();
			result.setResultCode(1);
			result.setReturnObj(message);
			return result;
		}
		
		User user = userService.login(loginBean.getEmail(), loginBean.getPassword());
		
		if (user == null) {
			ExecuteResult result = new ExecuteResult();
			result.setResultCode(1);
			result.setReturnObj("Cannot find the user.");
			return result;
		}
		
		ExecuteResult result = new ExecuteResult();
		result.setResultCode(0);
		result.setReturnObj(user);
		
		return result;
	}

	private String validationForLogin(LoginBean loginBean) {
		String message = "";
		
		if (loginBean == null) {
			message = "No data input. \n";
		}
		
		if (StringUtils.isBlank(loginBean.getEmail())) {
			message = message + "No email \n";
		}
		
		if (StringUtils.isBlank(loginBean.getPassword())) {
			message = message + "No password \n";
		}
		
		return message;
		
	}

}
