package rmit.sepm.PandaDiary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Chih-Hsuan Lee<s3714761>
 *
 */
@Entity
@Table(name = "Users")
@Data
public class User {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address_street")
	private String addressStreet;
	
	@Column(name = "address_surburb")
	private String addressSurburb;
	
	@Column(name = "address_postcode")
	private String addressPostcode;
	
	@Column(name = "address_state")
	private String addressState;
	
	@Column(name = "active")
	private Boolean active;

}
