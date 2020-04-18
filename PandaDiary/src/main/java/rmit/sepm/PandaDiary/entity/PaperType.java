package rmit.sepm.PandaDiary.entity;

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
@Table(name = "Paper_Types")
@Data
public class PaperType {
	
	@Id
	@Column (name = "id")
	private Integer id;
	
	@Column (name = "description")
	private String description;

}
