package rmit.sepm.PandaDiary.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rmit.sepm.PandaDiary.entity.User;
import rmit.sepm.PandaDiary.pojo.BriefOrderBean;
import rmit.sepm.PandaDiary.pojo.ExecuteResult;
import rmit.sepm.PandaDiary.services.OrderService;
import rmit.sepm.PandaDiary.services.UserService;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@RestController
@RequestMapping(value="order")
public class OrderController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="historyTop5", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult getHistoryTop5(@RequestParam(value="userid")String userId) {
		
		ExecuteResult result = new ExecuteResult();
		
		if (StringUtils.isAllBlank(userId)) {
			result.setResultCode(1);
			result.setReturnObj("No user id.");
			return result;
		}
		
		User user = userService.findByUserId(userId);
		
		if (user == null) {
			result.setResultCode(1);
			result.setReturnObj("Cannot find the user.");
			return result;
		}
		
		List<BriefOrderBean> histories = orderService.getTop5Orders(user);
		
		result.setResultCode(0);
		result.setReturnObj(histories);
		
		return result;
		
	}

}
