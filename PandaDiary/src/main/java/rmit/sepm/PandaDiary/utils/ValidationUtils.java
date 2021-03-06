package rmit.sepm.PandaDiary.utils;

import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rmit.sepm.PandaDiary.entity.User;
import rmit.sepm.PandaDiary.services.UserService;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@Component
public class ValidationUtils {
	
	private static UserService staticUserService;
	
	@Autowired
	UserService userService;
	
	@PostConstruct
	private void init() {
		staticUserService = this.userService;
	}
	
	public static String emailValidation(String email, Boolean isLogin) {
		
		String message = "";
		
		if (StringUtils.isBlank(email)) {
			message = message + "No email. ";
		}
		
		if (!StringUtils.isBlank(message)) {
			return message;
		}
		
		String emailReg = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		if (!Pattern.matches(emailReg, email)) {
			message = message + "No format of email is wrong. ";
		}
		
		if (!isLogin) {
			User user = staticUserService.findByEmail(email);
			if (user != null)
				message = message + "The email is duplicated. ";
		}
		
		return message;
		
	}
	
	public static String passwordValidation(String password) {
		
		String message = "";
		
		if (StringUtils.isBlank(password)) {
			message = message + "No password. ";
		}
		
		if (StringUtils.length(password) < 8) {
			message = message + "The length of password must be greater than 8. ";
		}
		
		return message;
		
	}

}
