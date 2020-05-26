package rmit.sepm.PandaDiary.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rmit.sepm.PandaDiary.constants.Constants;
import rmit.sepm.PandaDiary.entity.DeliveryOption;
import rmit.sepm.PandaDiary.entity.Order;
import rmit.sepm.PandaDiary.entity.PurchaseOption;
import rmit.sepm.PandaDiary.entity.User;
import rmit.sepm.PandaDiary.pojo.ExecuteResult;
import rmit.sepm.PandaDiary.pojo.OrderBean;
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
	
	@RequestMapping(path = "/download", method = RequestMethod.POST)
	public ResponseEntity<Resource> download(@RequestParam(value="period")Integer period) throws IOException{
		
		if (period == null)
			period = 1;
		
		File file = orderService.getReport(period);
		
	    Path path = Paths.get(file.getAbsolutePath());
	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
	    
	    HttpHeaders headers = new HttpHeaders();

	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(file.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}
	
	@RequestMapping(value="send", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult sendOrder(OrderBean orderBean) {
		
		ExecuteResult result = new ExecuteResult();
		
		String message = this.validateOrder(orderBean);
		
		if (!StringUtils.isBlank(message)) {
			result.setResultCode(1);
			result.setReturnObj(message);
			return result;
		}
		
		String orderId = orderService.addOrder(orderBean);
		
		if (StringUtils.isBlank(orderId)) {
			result.setResultCode(2);
			result.setReturnObj("Something is wrong. Please contact with administrator.");
			return result;
		}
		
		result.setResultCode(0);
		result.setReturnObj(orderId);
		
		return result;
		
	}
	
	private String validateOrder(OrderBean orderBean) {
		
		String message = "";
		if (StringUtils.isBlank(orderBean.getPhone())) {
			message = "No phone.";
		}
		if (StringUtils.isBlank(orderBean.getDeliveryStreet()) || StringUtils.isBlank(orderBean.getDeliveryPostcode())
				|| StringUtils.isBlank(orderBean.getDeliverySuburb())) {
			if (!StringUtils.isBlank(message))
				message = message + " ";
			message = "Address is incomplete.";
		}
		
		return message;
	}
	
	@RequestMapping(value="addReview", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult addReview(@RequestParam(value="userId")String userId, @RequestParam(value="orderId")String orderId, @RequestParam(value="reviewScore") Integer reviewScore, @RequestParam(value="reviewDesc") String reviewDesc) {
		
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
		
		Order orderS = orderService.findById(orderId);
		
		if (orderS == null) {
			result.setResultCode(1);
			result.setReturnObj("Cannot find the order.");
			return result;
		}
		
		Order order = orderService.addReview(orderS, reviewScore, reviewDesc);
		
		if (order == null) {
			result.setResultCode(1);
			result.setReturnObj("Cannot update the order.");
			return result;
		}
		
		
		result.setResultCode(0);
		result.setReturnObj(order);
		
		return result;
		
	}

	@RequestMapping(value="getOrderHistory", method = RequestMethod.POST, produces = "application/json; charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ExecuteResult getOrderHistory(@RequestParam(value="userId")String userId, @RequestParam(value="number")Integer number) {
		
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
		
		List<OrderBean> histories = null;
		
		if (number == -1)
			histories = orderService.getAllOrders();
		else
			histories = orderService.getHistoryOrders(user, number);
		
		result.setResultCode(0);
		result.setReturnObj(histories);
		
		return result;
		
	}
	
	@RequestMapping(value="getPurchaseOptions", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody ExecuteResult getPurchaseOptions() {
		
		ExecuteResult result = new ExecuteResult();
		
		if (Constants.PURCHASE_OPTIONS == null) {
			List<PurchaseOption> parameters = orderService.getPurchaseOptions();
			
			if (parameters == null) {
				result.setResultCode(1);
				result.setReturnObj("Failed to get parameters for purchase options.");
			}else {
				result.setResultCode(0);
				result.setReturnObj(parameters);
			}
		}else {
			result.setResultCode(0);
			result.setReturnObj(Constants.PURCHASE_OPTIONS);
		}
		
		return result;
	}

	@RequestMapping(value="getPurchaseOptions", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ExecuteResult getPurchaseOptions_GET() {
		return getPurchaseOptions();
	}
	
	@RequestMapping(value="getDeliveryOptions", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody ExecuteResult getDeliveryOptions() {
		
		ExecuteResult result = new ExecuteResult();
		
		if (Constants.DELIVERY_OPTIONS == null) {
			List<DeliveryOption> parameters = orderService.getDeliveryOptions();
			
			if (parameters == null) {
				result.setResultCode(1);
				result.setReturnObj("Failed to get parameters for delivery options.");
			}else {
				result.setResultCode(0);
				result.setReturnObj(parameters);
			}
		}else {
			result.setResultCode(0);
			result.setReturnObj(Constants.DELIVERY_OPTIONS);
		}
		
		return result;
	}
	
	@RequestMapping(value="getDeliveryOptions", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody ExecuteResult getDeliveryOptions_GET() {
		return getDeliveryOptions();
	}

}
