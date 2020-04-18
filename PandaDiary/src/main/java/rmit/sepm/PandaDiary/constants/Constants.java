package rmit.sepm.PandaDiary.constants;

import java.util.List;
import java.util.Map;

import rmit.sepm.PandaDiary.entity.DeliveryOption;
import rmit.sepm.PandaDiary.entity.PurchaseOption;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
public class Constants {

	public final static String ROLE_ADMIN = "a";
	public final static String ROLE_CUSTOMER = "c";
	
	public final static String DIARY_PARAMETERS_PC = "paperColor";
	public final static String DIARY_PARAMETERS_PT = "paperType";
	public final static String DIARY_PARAMETERS_CC = "coverColor";
	public final static String[] ALL_DIARY_PARAMETERS = {DIARY_PARAMETERS_PC, DIARY_PARAMETERS_PT, DIARY_PARAMETERS_CC};
	
	public static Map<String, Object> DIARY_PARAMS = null;
	
	public static List<PurchaseOption> PURCHASE_OPTIONS = null;
	public static List<DeliveryOption> DELIVERY_OPTIONS = null;
}
