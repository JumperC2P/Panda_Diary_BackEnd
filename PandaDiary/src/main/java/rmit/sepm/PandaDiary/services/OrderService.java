package rmit.sepm.PandaDiary.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import rmit.sepm.PandaDiary.constants.Constants;
import rmit.sepm.PandaDiary.entity.CoverColor;
import rmit.sepm.PandaDiary.entity.DeliveryOption;
import rmit.sepm.PandaDiary.entity.Order;
import rmit.sepm.PandaDiary.entity.PaperColor;
import rmit.sepm.PandaDiary.entity.PaperType;
import rmit.sepm.PandaDiary.entity.PurchaseOption;
import rmit.sepm.PandaDiary.entity.User;
import rmit.sepm.PandaDiary.pojo.OrderBean;
import rmit.sepm.PandaDiary.repository.DeliveryOptionRepository;
import rmit.sepm.PandaDiary.repository.OrderRepository;
import rmit.sepm.PandaDiary.repository.PurchaseOptionRepository;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	PurchaseOptionRepository purchaseOptionRepository;
	
	@Autowired
	DeliveryOptionRepository deliveryOptionRepository;
	
	public List<OrderBean> getTop5Orders(User user){
		
		List<Order> orders = orderRepository.findTop5ByBuyer(user.getId());
		
		if (CollectionUtils.isEmpty(orders)) {
			return null;
		}
		
		List<OrderBean> briefOrders = new ArrayList<>();
		
		for (Order order : orders) {
			
			OrderBean bean = new OrderBean();
			bean.setId(order.getId());
			
			@SuppressWarnings("unchecked")
			List<PaperColor> paperColors = (List<PaperColor>) Constants.DIARY_PARAMS.get(Constants.DIARY_PARAMETERS_PC);
			for (PaperColor paperColor : paperColors) {
				if (order.getPaperColor() == paperColor.getId()) {
					bean.setPaperColor(paperColor.getDescription());
					break;
				}
			}
			@SuppressWarnings("unchecked")
			List<PaperType> paperTypes = (List<PaperType>) Constants.DIARY_PARAMS.get(Constants.DIARY_PARAMETERS_PT);
			for (PaperType paperType : paperTypes) {
				if (order.getPaperType() == paperType.getId()) {
					bean.setPaperType(paperType.getDescription());
					break;
				}
			}
			@SuppressWarnings("unchecked")
			List<CoverColor> coverColors = (List<CoverColor>) Constants.DIARY_PARAMS.get(Constants.DIARY_PARAMETERS_CC);
			for (CoverColor coverColor : coverColors) {
				if (order.getCoverColor() == coverColor.getId()) {
					bean.setCoverColor(coverColor.getDescription());
					break;
				}
			}
			
			for (PurchaseOption option : Constants.PURCHASE_OPTIONS) {
				if (order.getPurchaseOption() == option.getId()) {
					bean.setPurchaseOption(option.getDescription());
					break;
				}
			}
			
			for (DeliveryOption option : Constants.DELIVERY_OPTIONS) {
				if (order.getDeliveryOption() == option.getId()) {
					bean.setDeliveryOption(option.getDescription());
					break;
				}
			}
			
			bean.setTitleOnCover(order.getTitleOnCover());
			bean.setOrderDate(order.getOrderDate());
			bean.setDeliveryDate(order.getDeliveryDate());
			bean.setCloseDate(order.getCloseDate());
			bean.setReviewScore(order.getReviewScore());
			bean.setReviewDate(order.getReviewDate());
			bean.setReviewDesc(order.getReviewDesc());
			
			bean.setPhone(order.getPhone());
			bean.setDeliveryStreet(order.getDeliveryStreet());
			bean.setDeliverySuburb(order.getDeliverySuburb());
			bean.setDeliveryPostcode(order.getDeliveryPostcode());
			bean.setDeliveryState(order.getDeliveryState());
			
			
			briefOrders.add(bean);
		}
		
		return briefOrders;
	}

	public List<PurchaseOption> getPurchaseOptions() {
		return purchaseOptionRepository.findAll();
	}

	public List<DeliveryOption> getDeliveryOptions() {
		return deliveryOptionRepository.findAll();
	}

}
