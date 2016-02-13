package clinic2.activlife.com.myapplication.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DBUtils {

	
	/**
	 * get datetime
	 * */
	public static String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
