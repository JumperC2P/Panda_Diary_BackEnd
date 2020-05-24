package rmit.sepm.PandaDiary.pojo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import rmit.sepm.PandaDiary.entity.Order;

@Data
public class ReportBean {
	
	private String peroid;
	private List<Order> orders;
	private BigDecimal sum;

}
