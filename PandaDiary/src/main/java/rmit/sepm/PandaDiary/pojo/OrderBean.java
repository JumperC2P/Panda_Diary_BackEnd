package rmit.sepm.PandaDiary.pojo;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@Data
public class OrderBean {
	
	private String id;
	private String buyer;
	private String paperColor;
	private String paperType;
	private String coverColor;
	private String titleOnCover;
	private Date orderDate;
	private Date deliveryDate;
	private Date closeDate;
	private Integer reviewScore;
	private Date reviewDate;
	private String reviewDesc;
	private String purchaseOption;
	private String deliveryOption;
	private String phone;
	private String deliveryStreet;
	private String deliverySuburb;
	private String deliveryPostcode;
	private String deliveryState;
	private BigDecimal price;

}
