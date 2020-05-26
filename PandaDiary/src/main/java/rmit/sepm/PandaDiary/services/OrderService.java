package rmit.sepm.PandaDiary.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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
import rmit.sepm.PandaDiary.pojo.ReportBean;
import rmit.sepm.PandaDiary.repository.DeliveryOptionRepository;
import rmit.sepm.PandaDiary.repository.OrderRepository;
import rmit.sepm.PandaDiary.repository.PurchaseOptionRepository;
import rmit.sepm.PandaDiary.utils.DateUtils;

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
	
	public List<OrderBean> getHistoryOrders(User user, Integer number){
		
		List<Order> orders = orderRepository.findByBuyerWithLimit(user.getId(), number);
		
		if (CollectionUtils.isEmpty(orders)) {
			return null;
		}
		
		return transformOrders(orders);
	}
	
	private List<OrderBean> transformOrders(List<Order> orders) {
		
		List<OrderBean> briefOrders = new ArrayList<>();
		
		for (Order order : orders) {
			
			OrderBean bean = new OrderBean();
			bean.setId(order.getId());
			bean.setBuyer(order.getBuyer());
			
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
			bean.setPrice(order.getPrice());
			
			briefOrders.add(bean);
		}
		
		return briefOrders;
	}

	public List<OrderBean> getAllOrders(){
		
		List<Order> orders = orderRepository.findAll();
		
		if (CollectionUtils.isEmpty(orders)) {
			return null;
		}
		
		return transformOrders(orders);
	}

	public List<PurchaseOption> getPurchaseOptions() {
		return purchaseOptionRepository.findAll();
	}

	public List<DeliveryOption> getDeliveryOptions() {
		return deliveryOptionRepository.findAll();
	}

	public String addOrder(OrderBean orderBean) {
		
		String lastOrderId = orderRepository.findLastOrderId();
		
		if (StringUtils.isBlank(lastOrderId)) {
			return null;
		}
		
		Integer id = Integer.valueOf(lastOrderId.substring(lastOrderId.length()-2));
		
		String lastOrderDate = lastOrderId.substring(0, lastOrderId.length()-2);
		String today = DateUtils.getDateWithYYYYMMDD();
		if (StringUtils.equals(today, lastOrderDate)) {
			id++;
		}else {
			id = 1;
		}
		
		String idString = String.valueOf(id);
		if (id < 10) {
			idString = today + "0" + id;
		}else {
			idString = today + id;
		}
		
		Order order = new Order();
		order.setId(idString);
		order.setBuyer(orderBean.getBuyer());
		order.setPaperType(Integer.valueOf(orderBean.getPaperType()));
		order.setPaperColor(Integer.valueOf(orderBean.getPaperColor()));
		order.setCoverColor(Integer.valueOf(orderBean.getCoverColor()));
		order.setTitleOnCover(orderBean.getTitleOnCover());
		order.setPurchaseOption(Integer.valueOf(orderBean.getPurchaseOption()));
		order.setDeliveryOption(Integer.valueOf(orderBean.getDeliveryOption()));
		order.setPhone(orderBean.getPhone());
		order.setDeliveryStreet(orderBean.getDeliveryStreet());
		order.setDeliverySuburb(orderBean.getDeliverySuburb());
		order.setDeliveryPostcode(orderBean.getDeliveryPostcode());
		order.setDeliveryState(orderBean.getDeliveryState());
		order.setOrderDate(DateUtils.getToday());
		order.setPrice(orderBean.getPrice());
		
		Order addOrder = null;
		try {
			addOrder = orderRepository.save(order);
		} catch (Exception e) {}
		
		if (addOrder == null)
			return null;
		
		return idString;
	}

	public File getReport(Integer period) {
		
		String fileRoot = System.getProperty("user.dir")+"/src/main/resources/files/reports/";
		
		List<Order> orders = orderRepository.findAll();
		orders.sort((o1, o2) -> o1.getOrderDate().compareTo(o2.getOrderDate()));
		
		String filePath = "";
		if (period == null || period == 0) {
			filePath = this.weeklyReport(fileRoot, orders);
		}else {
			filePath = this.monthlyReport(fileRoot, orders);
		}
		return new File(filePath);
	}

	private String monthlyReport(String fileRoot, List<Order> orders) {
		FileWriter writer = null;
		PrintWriter pw = null;
		try {
			writer = new FileWriter(fileRoot+"report-monthly.csv");
			pw = new PrintWriter(writer);
			
			List<ReportBean> groups = this.seperateByMonthly(orders);
			for (ReportBean group : groups) {
				pw.write("Month " + group.getPeroid() + ", Total Profit: " + group.getSum() + "\n");
				pw.write("Order ID,Buyer,Paper Type, Paper Color, Cover Color, Title, Purchase Option, Delivery Option, Phone, Street, Suburb, Postcode, State, Order Date, Delivery Date, Close Date, Review Score, Review Date, Review, Price\n");
				for (Order order : group.getOrders()) {
					pw.write(order.toString()+"\n");
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (pw != null)
				pw.close();
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {}
		}
		return fileRoot+"report-monthly.csv";
	}

	private List<ReportBean> seperateByMonthly(List<Order> orders) {
		List<ReportBean> beans = new ArrayList<>();
		List<Order> temp = new ArrayList<>();
		ReportBean rb = new ReportBean();
		BigDecimal sum = BigDecimal.ZERO;
		String oldPeriod = null;
		for (Order order : orders) {
			
			Calendar calendar = Calendar. getInstance();
			calendar.setTime(order.getOrderDate());
			String month = calendar.get(Calendar.YEAR)+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1);
			
			if (oldPeriod == null || month.equals(oldPeriod)) {
				temp.add(order);
				sum = sum.add(order.getPrice());
				oldPeriod = month;
			}else {
				rb = new ReportBean();
				rb.setPeroid(oldPeriod);
				rb.setOrders(temp);
				rb.setSum(sum);
				beans.add(rb);
				sum = BigDecimal.ZERO;
				temp = new ArrayList<>();
				temp.add(order);
				sum = sum.add(order.getPrice());
				oldPeriod = month;
			}
		}
		rb = new ReportBean();
		rb.setPeroid(oldPeriod);
		rb.setOrders(temp);
		rb.setSum(sum);
		beans.add(rb);
		
		return beans;
	}

	private String weeklyReport(String fileRoot, List<Order> orders) {
		FileWriter writer = null;
		PrintWriter pw = null;
		try {
			writer = new FileWriter(fileRoot+"report-weekly.csv");
			pw = new PrintWriter(writer);
			
			List<ReportBean> groups = this.seperateByWeekly(orders);
			for (ReportBean group : groups) {
				pw.write("WEEK " + group.getPeroid() + ", Total Profit: " + group.getSum() + "\n");
				pw.write("Order ID,Buyer,Paper Type, Paper Color, Cover Color, Title, Purchase Option, Delivery Option, Phone, Street, Suburb, Postcode, State, Order Date, Delivery Date, Close Date, Review Score, Review Date, Review, Price\n");
				for (Order order : group.getOrders()) {
					pw.write(order.toString()+"\n");
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (pw != null)
				pw.close();
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {}
		}
		return fileRoot+"report-weekly.csv";
	}

	private List<ReportBean> seperateByWeekly(List<Order> orders) {
		
		List<ReportBean> beans = new ArrayList<>();
		List<Order> temp = new ArrayList<>();
		ReportBean rb = null;
		BigDecimal sum = BigDecimal.ZERO;
		String oldPeriod = null;
		for (Order order : orders) {
			
			Calendar calendar = Calendar. getInstance();
			calendar.setTime(order.getOrderDate());
			String weekOfYear = String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
			
			if (oldPeriod == null || weekOfYear.equals(oldPeriod)) {
				temp.add(order);
				sum = sum.add(order.getPrice());
				oldPeriod = weekOfYear;
			}else {
				rb = new ReportBean();
				rb.setPeroid(oldPeriod);
				rb.setOrders(temp);
				rb.setSum(sum);
				beans.add(rb);
				
				sum = BigDecimal.ZERO;
				temp = new ArrayList<>();
				temp.add(order);
				sum = sum.add(order.getPrice());
				oldPeriod = weekOfYear;
			}
		}
		rb = new ReportBean();
		rb.setPeroid(oldPeriod);
		rb.setOrders(temp);
		rb.setSum(sum);
		beans.add(rb);
		
		return beans;
	}

	public Order findById(String orderId) {
		Optional<Order> orders = orderRepository.findById(orderId);
		if (orders != null) {
			return orders.get();
		}
		return null;
	}

	public Order addReview(Order orderS, Integer reviewScore, String reviewDesc) {
		orderS.setReviewDate(DateUtils.getToday());
		orderS.setReviewScore(reviewScore);
		orderS.setReviewDesc(reviewDesc);
		return orderRepository.save(orderS);
	}

}
