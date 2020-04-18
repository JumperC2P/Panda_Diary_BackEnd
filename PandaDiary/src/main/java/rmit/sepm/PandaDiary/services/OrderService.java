package rmit.sepm.PandaDiary.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import rmit.sepm.PandaDiary.constants.Constants;
import rmit.sepm.PandaDiary.entity.CoverColor;
import rmit.sepm.PandaDiary.entity.Order;
import rmit.sepm.PandaDiary.entity.PaperColor;
import rmit.sepm.PandaDiary.entity.PaperType;
import rmit.sepm.PandaDiary.entity.User;
import rmit.sepm.PandaDiary.pojo.BriefOrderBean;
import rmit.sepm.PandaDiary.repository.OrderRepository;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	public List<BriefOrderBean> getTop5Orders(User user){
		
		List<Order> orders = orderRepository.findTop5ByBuyer(user.getId());
		
		if (CollectionUtils.isEmpty(orders)) {
			return null;
		}
		
		List<BriefOrderBean> briefOrders = new ArrayList<>();
		
		for (Order order : orders) {
			
			BriefOrderBean bean = new BriefOrderBean();
			bean.setId(order.getId());
			
			@SuppressWarnings("unchecked")
			List<PaperColor> paperColors = (List<PaperColor>) Constants.PARAMS.get(Constants.DIARY_PARAMETERS_PC);
			for (PaperColor paperColor : paperColors) {
				if (order.getPaperColor() == paperColor.getId()) {
					bean.setPaperColor(paperColor.getDescription());
					break;
				}
			}
			@SuppressWarnings("unchecked")
			List<PaperType> paperTypes = (List<PaperType>) Constants.PARAMS.get(Constants.DIARY_PARAMETERS_PT);
			for (PaperType paperType : paperTypes) {
				if (order.getPaperType() == paperType.getId()) {
					bean.setPaperType(paperType.getDescription());
					break;
				}
			}
			@SuppressWarnings("unchecked")
			List<CoverColor> coverColors = (List<CoverColor>) Constants.PARAMS.get(Constants.DIARY_PARAMETERS_CC);
			for (CoverColor coverColor : coverColors) {
				if (order.getCoverColor() == coverColor.getId()) {
					bean.setCoverColor(coverColor.getDescription());
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
			
			briefOrders.add(bean);
		}
		
		return briefOrders;
	}

}
