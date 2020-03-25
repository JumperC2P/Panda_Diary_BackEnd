package rmit.sepm.PandaDiary.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@Entity
@Table(name = "Orders")
@Data
public class Order {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "buyer")
	private String buyer;
	
	@Column(name = "paper_type")
	private String paperType;
	
	@Column(name = "paper_color")
	private String paperColor;
	
	@Column(name = "cover_color")
	private String coverColor;
	
	@Column(name = "title_on_cover")
	private String titleOnCover;
	
	@Column(name = "purchase_option")
	private Integer purchaseOption;
	
	@Column(name = "delivery_option")
	private Integer deliveryOption;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "delivery_street")
	private String deliveryStreet;
	
	@Column(name = "delivery_surburb")
	private String deliverySurburb;
	
	@Column(name = "delivery_postcode")
	private String deliveryPostcode;
	
	@Column(name = "delivery_state")
	private String deliveryState;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name = "delivery_date")
	private Date deliveryDate;
	
	@Column(name = "close_date")
	private Date closeDate;
	
	@Column(name = "review_score")
	private Integer reviewScore;
	
	@Column(name = "review_date")
	private Date reviewDate;
	
	@Column(name = "review_desc")
	private String reviewDesc;

}
