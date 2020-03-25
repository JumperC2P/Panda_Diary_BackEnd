package rmit.sepm.PandaDiary.pojo;

import lombok.Data;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
@Data
public class ExecuteResult {
	
	//0: success, 1: warning, 2: error
	private Integer resultCode;
	
	private Object returnObj;

}
