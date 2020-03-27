package rmit.sepm.PandaDiary.utils;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
public class CommonUtils {
	
	public static String addZero(String str, int num) {
		if (str == null || (str.length() > num )) {
			return str;
		}else {
			StringBuffer sb = new StringBuffer();
			for (int x = (num - str.length()) ; x >0 ; x--) {
				sb.append("0");
			}
			sb.append(str);
			return sb.toString();
		}
			
	}

}
