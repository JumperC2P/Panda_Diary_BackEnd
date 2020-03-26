package rmit.sepm.PandaDiary.pojo;

import lombok.Data;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@Data
public class RegisterBean {
	
	private Integer role;
	private String name;
	private String email;
	private String password;

}
