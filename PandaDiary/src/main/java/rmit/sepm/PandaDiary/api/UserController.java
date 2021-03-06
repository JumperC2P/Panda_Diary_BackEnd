package rmit.sepm.PandaDiary.api;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rmit.sepm.PandaDiary.entity.User;
import rmit.sepm.PandaDiary.pojo.ExecuteResult;
import rmit.sepm.PandaDiary.pojo.LoginBean;
import rmit.sepm.PandaDiary.pojo.RegisterBean;
import rmit.sepm.PandaDiary.services.UserService;
import rmit.sepm.PandaDiary.utils.ValidationUtils;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	

	@RequestMapping(value="updateUserProfile", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult updateUserProfile(User user) {
		
		ExecuteResult result = new ExecuteResult();
		
		if (user == null || user.getId() == null) {
			result.setResultCode(1);
			result.setReturnObj("Nothing to update.");
			return result;
		}
		
		User userA = userService.findByUserId(user.getId());
		
		if (userA == null) {
			result.setResultCode(1);
			result.setReturnObj("Cannot find the admin.");
			return result;
		}
		
		user.setPassword(userA.getPassword());
		User userU = userService.updateUserProfile(user);
		
		if (userU == null) {
			result.setResultCode(1);
			result.setReturnObj("Failed to update the user profile");
			return result;
		}
		
		result.setResultCode(0);
		result.setReturnObj(userU);
		
		return result;
	}	
	
	
	@RequestMapping(value="getAllUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult getAllUser(User user) {
		
		ExecuteResult result = new ExecuteResult();
		
		User userF = userService.findByUserId(user.getId());
		
		if (userF == null) {
			result.setResultCode(1);
			result.setReturnObj("Cannot find the user.");
			return result;
		}
		
		List<User> users = userService.findAll();
		
		if (CollectionUtils.isEmpty(users)) {
			result.setResultCode(1);
			result.setReturnObj("Cannot get users.");
			return result;
		}
		
		result.setResultCode(0);
		result.setReturnObj(users);
		return result;
	}
	
	@RequestMapping(value="changeActive", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult changeActive(User user) {
		
		ExecuteResult result = new ExecuteResult();
		
		User userF = userService.findByUserId(user.getId());
		
		if (userF == null) {
			result.setResultCode(1);
			result.setReturnObj("Cannot find the user.");
			return result;
		}
		
		User userU = userService.changeActive(userF, user.getActive());
		
		if (userU == null) {
			result.setResultCode(1);
			result.setReturnObj("Cannot change the user status");
			return result;
		}
		
		result.setResultCode(0);
		result.setReturnObj(userU);
		return result;
	}
	
	@RequestMapping(value="checkEmail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult emailChecker(@RequestParam(value="email")String email) {
		
		ExecuteResult result = new ExecuteResult();
		
		String message = ValidationUtils.emailValidation(email, false);
		
		if (!StringUtils.isBlank(message)) {
			result.setResultCode(1);
			result.setReturnObj(message);
			return result;
		}else {
			result.setResultCode(0);
			result.setReturnObj("The email is acceptable.");
		}
		
		return result;
	}
	
	@RequestMapping(value="register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult register(RegisterBean registerBean) {
		
		ExecuteResult result = new ExecuteResult();
		
		String message = this.validationForRegister(registerBean);
		
		if (!StringUtils.isBlank(message)) {
			result.setResultCode(1);
			result.setReturnObj(message);
			return result;
		}
		
		User user = userService.register(registerBean);
		
		if (user == null) {
			result.setResultCode(1);
			result.setReturnObj("Failed to create the user.");
			return result;
		}
		
		user.setPassword("");
		
		result.setResultCode(0);
		result.setReturnObj(user);
		
		return result;
	}
	
	private String validationForRegister(RegisterBean registerBean) {
		
		String message = "";
		
		if (registerBean == null) {
			message = "No user input. ";
			return message;
		}
		
		if (registerBean.getRole() == null || registerBean.getRole() == 0) {
			message = "No Role. ";
		}else if (registerBean.getRole() > 2) {
			message = "Role is unvalid.";
		}
		
		if (!StringUtils.isBlank(message)) {
			return message;
		}
		
		if (StringUtils.isBlank(registerBean.getName())) {
			message = message + "No Name. ";
		}
		
		String emailCheckMessage = ValidationUtils.emailValidation(registerBean.getEmail(), false);
		if (!StringUtils.isBlank(emailCheckMessage)) {
			message = message + emailCheckMessage;
		}
		
		String passwordCheckMessage = ValidationUtils.passwordValidation(registerBean.getPassword());
		if (!StringUtils.isBlank(passwordCheckMessage)) {
			message = message + passwordCheckMessage;
		}
		
		return message;
	}

	@RequestMapping(value="login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult login(LoginBean loginBean) {
		
		ExecuteResult result = new ExecuteResult();
		
		String message = this.validationForLogin(loginBean);
		
		if (!StringUtils.isBlank(message)) {
			result.setResultCode(1);
			result.setReturnObj(message);
			return result;
		}
		
		User user = userService.login(loginBean.getEmail(), loginBean.getPassword());
		
   		if (user == null) {
			result.setResultCode(1);
			result.setReturnObj("Cannot find the user.");
			return result;
		}
		
		result.setResultCode(0);
		result.setReturnObj(user);
		
		return result;
	}

	private String validationForLogin(LoginBean loginBean) {
		
		String message = "";
		
		if (loginBean == null) {
			message = "No data input. ";
			return message;
		}
		
		String emailCheckMessage = ValidationUtils.emailValidation(loginBean.getEmail(), true);
		if (!StringUtils.isBlank(emailCheckMessage)) {
			message = message + emailCheckMessage;
		}

		String passwordCheckMessage = ValidationUtils.passwordValidation(loginBean.getPassword());
		if (!StringUtils.isBlank(passwordCheckMessage)) {
			message = message + passwordCheckMessage;
		}
		
		return message;
		
	}

}
