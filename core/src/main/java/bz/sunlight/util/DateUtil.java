package bz.sunlight.util;






import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bz.sunlight.constant.BaseConstant;

public class DateUtil {

	//日期转字符串
	public static String dateToString(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf=new SimpleDateFormat(format);
			String str = sdf.format(date);
			return str;
		}
		return "";
		
	}
	
	//字符串转日期格式
	public static Date stringToDate(String str,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date date=null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("日期转化出错");
			e.printStackTrace();
		}
		return date;
		
	}
	
	//日期转日期  从带时分秒到不带时分秒
	public static Date dateToDate(Date date){
		if(date!=null){
			date = stringToDate(dateToString(date, BaseConstant.DATE_FORMAT_YEAR_TO_SECOND), BaseConstant.DATE_FORMAT_YEAR_TO_DAY);
			return date;
		}
		return null;
		
	}
	
	
    /**
     * 判断两个日期是否是同一天
     * 
     * @param Date
     *            date1
     * @param Date
     *            date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                        .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }
    
    /**
     * 当天开始时间
     * @param Date date
     * @return Date
     */
    public static Date startOfCurrentDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 当天的结束时间
     * @param Date date
     * @return Date
     */
    public static Date endOfCurrentDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
    }

    
    // 获得本月第一天0点时间  
    public static Date getTimesMonthStart() {  
        Calendar cal = Calendar.getInstance();  
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));  
        return cal.getTime();  
    }  
  
    // 获得本月最后一天24点时间  
    public static Date getTimesMonthEnd() {  
        Calendar cal = Calendar.getInstance();  
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));  
        cal.set(Calendar.HOUR_OF_DAY, 24);  
        return cal.getTime();  
    }  
    
    // 获取当前时间的年份
    public static int currentYear() {  
        Calendar now = Calendar.getInstance();  
        return now.get(Calendar.YEAR);
    }    

    // 获取当前时间的月份
    public static int currentMonth() {  
        Calendar now = Calendar.getInstance();  
        return now.get(Calendar.MONTH) + 1;
    }    
	
    // 获取当前时间的前一个小时的时间
    public static Date beforeOneHourToNowDate() {  
        Calendar calendar = Calendar.getInstance();  

        /* HOUR_OF_DAY 指示一天中的小时 */  
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);  

        return calendar.getTime();
    }
    
    // 计算两个 date 相差的秒数
    public static long getDiffSeconds(Date start, Date end) {
        Calendar c1 = Calendar.getInstance();
        c1.clear();

        Calendar c2 = Calendar.getInstance();
        c2.clear();

        // Set the date for both of the calendar instance
        c1.setTime(start);
        c2.setTime(end);

        // Get the represented date in milliseconds
        long time1 = c1.getTimeInMillis();
        long time2 = c2.getTimeInMillis();

        long diffSec = (time2 - time1) / 1000;
        return diffSec;
    }
    
 // 计算两个 date 相差的天数
    public static int getDiffDays(Date start, Date end) {
        Calendar c1 = Calendar.getInstance();
        c1.clear();

        Calendar c2 = Calendar.getInstance();
        c2.clear();

        // Set the date for both of the calendar instance
        c1.setTime(start);
        c2.setTime(end);

        // Get the represented date in milliseconds
        long time1 = c1.getTimeInMillis();
        long time2 = c2.getTimeInMillis();

        long diffSec = (time2 - time1) / (1000*3600*24);
        return Integer.parseInt(String.valueOf(diffSec));
    }
    
    
	public static void main(String[] args) {
//		System.out.println(getTimesMonthStart());
//		System.out.println(getTimesMonthEnd());
//		
//		String year = String.valueOf(currentYear());
//		String month = String.valueOf(currentMonth());
//		System.out.println(year);
//		System.out.println(month);
//		
//		System.out.println("当前时间：" + new Date());
//		System.out.println("一小时以前时间：" + beforeOneHourToNowDate());
//		
//		Calendar c = Calendar.getInstance(); 
//		c.set(2017, 5, 6, 19, 22, 0);
//		System.out.println(getDiffSeconds(new Date(), c.getTime()));
		Date start = stringToDate("2017-06-14 19:08:07", BaseConstant.DATE_FORMAT_YEAR_TO_DAY);
		int diffDays = getDiffDays(start, new Date())+1;
		System.out.println(diffDays);
	}
	
}
