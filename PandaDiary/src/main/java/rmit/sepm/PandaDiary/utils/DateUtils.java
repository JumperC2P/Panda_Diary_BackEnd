package rmit.sepm.PandaDiary.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Chih-Hsuan Lee <s3714761>
 *
 */
public class DateUtils {
	
	public static String getDateWithYYYYMMDD() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String format = formatter.format(cal.getTime());
		return format;
	}
	
	public static Date getToday() {
		Calendar cal = Calendar.getInstance();
		return new Date(cal.getTimeInMillis());
	}

}
