package rmit.sepm.PandaDiary.pojo;

import java.sql.Date;

import lombok.Data;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@Data
public class BriefOrderBean {
	
	private String id;
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
	

}
