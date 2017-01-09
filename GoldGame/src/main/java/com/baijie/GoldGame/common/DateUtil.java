package com.baijie.GoldGame.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * DateUtils.java 日期时间工具
 * 
 * @version 1.0
 * @author SUNY Written Date: 2007-7-18
 * 
 * Modified By: Modified Date:
 */
public class DateUtil {

	/** 缺省日期格式 */
	public static final String DEFAULT_DATE_FMT = "yyyy/MM/dd";

	/** 缺省时间格式 */
	public static final String DEFAULT_TIME_FMT = "yyyy/MM/dd HH:mm:ss";

	/** 全部时区名字 */
	private static final List TimeZoneIds = Arrays.asList(TimeZone
			.getAvailableIDs());

	/** 自定义时区缓存 */
	private static final Map TimeZoneCache = new HashMap();

	/** 北京时区 */
	public static final TimeZone timeZoneBeijing = TimeZone
			.getTimeZone("Asia/Shanghai");
	/** 操作历史时间格式 */
	public static final String HIS_TIME_FMT = "HH:mm:ss yyyy/MM/dd";
	/**
	 * 转换日期毫秒数为缺省日期格式字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String Date2String(long date) {
		return Date2String(new Date(date), DEFAULT_DATE_FMT, null);
	}

	/**
	 * 转换日期毫秒数为缺省日期格式字符串
	 * 
	 * @param date
	 * @param timeZone
	 * @return
	 */
	public static String Date2String(long date, TimeZone timeZone) {
		return Date2String(new Date(date), DEFAULT_DATE_FMT, timeZone);
	}

	/**
	 * 转换日期为缺省日期格式字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String Date2String(Date date) {
		return Date2String(date, DEFAULT_DATE_FMT, null);
	}

	/**
	 * 转换日期为缺省日期格式字符串
	 * 
	 * @param date
	 * @param timeZone
	 * @return
	 */
	public static String Date2String(Date date, TimeZone timeZone) {
		return Date2String(date, DEFAULT_DATE_FMT, timeZone);
	}

	/**
	 * 转换日期毫秒数为缺省日期格式字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String Time2String(long date) {
		return Date2String(new Date(date), DEFAULT_TIME_FMT, null);
	}

	/**
	 * 转换日期毫秒数为缺省日期格式字符串
	 * 
	 * @param date
	 * @param timeZone
	 * @return
	 */
	public static String Time2String(long date, TimeZone timeZone) {
		return Date2String(new Date(date), DEFAULT_TIME_FMT, timeZone);
	}

	/**
	 * 转换日期为缺省日期格式字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String Time2String(Date date) {
		return Date2String(date, DEFAULT_TIME_FMT, null);
	}

	/**
	 * 转换日期为缺省日期格式字符串
	 * 
	 * @param date
	 * @param timeZone
	 * @return
	 */
	public static String Time2String(Date date, TimeZone timeZone) {
		return Date2String(date, DEFAULT_TIME_FMT, timeZone);
	}

	/**
	 * 转换日期为指定格式字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String Date2String(Date date, String format) {
		return Date2String(date, format, null);

	}

	/**
	 * 转换日期为指定格式字符串
	 * 
	 * @param date
	 * @param format
	 * @param timeZone
	 * @return
	 */
	public static String Date2String(Date date, String format, TimeZone timeZone) {
		if (date == null || format == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (timeZone != null)
			sdf.setTimeZone(timeZone);
		return sdf.format(date);

	}

	/**
	 * 解析日期时间字符串,支持 yyMMdd,yyyyMMdd, yyyy-MM-dd, yyyy/MM/dd, yyyyMMddHHmm,
	 * yyyyMMddHHmmss, yyyyMMddHHmmssSSS, yyyy-MM-dd HH:mm:ss, yyyy-MM-dd
	 * HH:mm:ss.SSS 格式, 其它方式结果不保证正确
	 * 
	 * @param str
	 * @return date
	 */
	public static Date String2Date(String str) {
		return String2Date(str, (TimeZone) null);
	}

	/**
	 * 解析日期时间字符串,支持 yyMMdd,yyyyMMdd, yyyy-MM-dd, yyyy/MM/dd, yyyyMMddHHmm,
	 * yyyyMMddHHmmss, yyyyMMddHHmmssSSS, yyyy-MM-dd HH:mm:ss, yyyy-MM-dd
	 * HH:mm:ss.SSS 格式, 其它方式结果不保证正确
	 * 
	 * @param str
	 * @param timeZone
	 * @return date
	 */
	public static Date String2Date(String str, TimeZone timeZone) {
		if (str == null)
			return null;
		str = str.trim();
		if (str.length() == 6)
			return String2Date(str, "yyMMdd", timeZone);
		if (str.length() == 8)
			return String2Date(str, "yyyyMMdd", timeZone);
		if (str.length() == 10) {
			if (str.indexOf("-") != -1)
				return String2Date(str, "yyyy-MM-dd", timeZone);

			if (str.indexOf("/") != -1)
				return String2Date(str, "yyyy/MM/dd", timeZone);
		}
		if (str.length() == 12)
			return String2Date(str, "yyyyMMddHHmm", timeZone);
		if (str.length() == 14)
			return String2Date(str, "yyyyMMddHHmmss", timeZone);
		if (str.length() == 17)
			return String2Date(str, "yyyyMMddHHmmssSSS", timeZone);
		if (str.length() == 19) {
			if (str.indexOf("-") != -1)
				return String2Date(str, "yyyy-MM-dd HH:mm:ss", timeZone);
			if (str.indexOf("/") != -1)
				return String2Date(str, "yyyy/MM/dd HH:mm:ss", timeZone);
		}
		if (str.length() == 23) {
			if (str.indexOf("-") != -1)
				return String2Date(str, "yyyy-MM-dd HH:mm:ss.SSS", timeZone);
			if (str.indexOf("/") != -1)
				return String2Date(str, "yyyy/MM/dd HH:mm:ss.SSS", timeZone);
		}
		try {
			return new SimpleDateFormat().parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 按指定方式解析日期时间
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date String2Date(String str, String format) {
		return String2Date(str, format, null);
	}

	/**
	 * 按指定方式解析日期时间
	 * 
	 * @param str
	 * @param format
	 * @param timeZone
	 * @return
	 */
	public static Date String2Date(String str, String format, TimeZone timeZone) {
		if (str == null)
			return null;
		if (format == null)
			format = DEFAULT_DATE_FMT;
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		if (timeZone != null)
			fmt.setTimeZone(timeZone);
		try {
			return fmt.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 比较两个日期是否是一天(不考虑时间)
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isDateEqual(Date date1, Date date2) {
		return truncDate(date1).equals(truncDate(date2));
	}

	/**
	 * 比较两个日期是否是一天(不考虑时间)
	 * 
	 * @param date1
	 * @param date2
	 * @param timeZone
	 * @return
	 */
	public static boolean isDateEqual(Date date1, Date date2, TimeZone timeZone) {
		return truncDate(date1, timeZone).equals(truncDate(date2, timeZone));
	}

	/**
	 * 返回某日零时整
	 * 
	 * @param date
	 * @return
	 */
	public static Date truncDate(Date date) {
		return truncDate(date, Calendar.DATE);
	}

	/**
	 * 返回某日零时整
	 * 
	 * @param date
	 * @param timeZone
	 * @return
	 */
	public static Date truncDate(Date date, TimeZone timeZone) {
		return truncDate(date, Calendar.DATE, timeZone);
	}

	/**
	 * 日期时间取整，支持年、月、周、日、时、分、秒
	 * 
	 * @param date
	 * @param mode
	 * @return date
	 */
	public static Date truncDate(Date date, int mode) {
		return truncDate(date, mode, null);
	}

	/**
	 * 日期时间取整，支持年、月、周、日、时、分、秒
	 * 
	 * @param date
	 * @param mode
	 * @param timeZone
	 * @return date
	 */
	public static Date truncDate(Date date, int mode, TimeZone timeZone) {
		if (date == null)
			return null;
		Calendar cal = (timeZone == null ? Calendar.getInstance() : Calendar
				.getInstance(timeZone));
		cal.setTime(date);
		switch (mode) {
		case Calendar.YEAR:
			cal.clear(Calendar.MONTH);
		case Calendar.MONTH:
		case Calendar.WEEK_OF_MONTH:
			if (mode == Calendar.MONTH)
				cal.set(Calendar.DAY_OF_MONTH, 1);
			else
				cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		case Calendar.DATE:
			cal.set(Calendar.HOUR_OF_DAY, 0);
		case Calendar.HOUR:
			cal.clear(Calendar.MINUTE);
		case Calendar.MINUTE:
			cal.clear(Calendar.SECOND);
		case Calendar.SECOND:
			cal.clear(Calendar.MILLISECOND);
			break;
		default:
			throw new IllegalArgumentException();
		}
		return cal.getTime();
	}

	/**
	 * 循环调整时间
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date rollDate(Date date, int field, int amount) {
		return rollDate(date, field, amount, null);
	}

	/**
	 * 循环调整时间
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @param timeZone
	 * @return
	 */
	public static Date rollDate(Date date, int field, int amount,
			TimeZone timeZone) {
		Calendar cal = (timeZone == null ? Calendar.getInstance() : Calendar
				.getInstance(timeZone));
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}

	/**
	 * 调整时间
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date addDate(Date date, int field, int amount) {
		return addDate(date, field, amount, null);
	}

	/**
	 * 调整时间
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @param timeZone
	 * @return
	 */
	public static Date addDate(Date date, int field, int amount,
			TimeZone timeZone) {
		Calendar cal = (timeZone == null ? Calendar.getInstance() : Calendar
				.getInstance(timeZone));
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}

	/**
	 * 比较2个同时区时间先后，注意:时间的格式必须在String2Date支持的格式范围内
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 如果时间1等于时间2，返回0，如果时间1小于时间2，返回负值，如果时间1大于时间2，返回正值
	 */
	public static int compare(String date1, String date2) {
		return String2Date(date1).compareTo(String2Date(date2));
	}

	/**
	 * 比较2个时间先后
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 如果时间1等于时间2，返回0，如果时间1小于时间2，返回负值，如果时间1大于时间2，返回正值
	 */
	public static int compare(Date date1, Date date2) {
		return date1.compareTo(date2);
	}

	/**
	 * 查询时区
	 * 
	 * @param timediff
	 * @return
	 */
	public static TimeZone findTimeZone(int timediff) {
		String[] ids = TimeZone.getAvailableIDs(timediff * 60000
				+ TimeZone.getDefault().getOffset(System.currentTimeMillis()));
		if (ids == null)
			return new SimpleTimeZone(timediff * 60000, "UDT");
		return TimeZone.getTimeZone(ids[0]);
	}

	/**
	 * 根据时区名字取得时区 如果非java已知标准名字，则必须为 GMT[+-]hh:mm 格式
	 * 
	 * @param id
	 */
	public static TimeZone getTimeZone(String id) {
		if (id == null)
			return null;
		if (TimeZoneIds.contains(id))
			return TimeZone.getTimeZone(id);
		if (TimeZoneCache.containsKey(id))
			return (TimeZone) TimeZoneCache.get(id);
		Pattern p = Pattern.compile("^GMT[+-](0[0-9]|1[01]):([0-5][0-9])$");
		Matcher m = p.matcher("id");
		if (!m.matches())
			return null;
		int hh = Integer.parseInt(id.substring(4, 6));
		int mm = Integer.parseInt(id.substring(7));
		int sign = (id.charAt(3) == '-' ? -1 : 1);
		TimeZone tz = new SimpleTimeZone((hh * 60 + mm) * 60000 * sign, id);
		TimeZoneCache.put(id, tz);
		return tz;
	}

	public static Timestamp string2TimeStamp(Object millions, Object nanos) {
		try {
			Timestamp res = new Timestamp(Long.parseLong((String) millions));
			res.setNanos(Integer.parseInt((String) nanos));

			return res;
		} catch (Exception e) {
			return null;
		}

	}

	/** 把Date转为Timestamp */
	public static Timestamp date2Timestamp(Date adate) {
		return new Timestamp(adate.getTime());
	}

	/**
	 * <p>
	 * 把用户当地时间转成网银时间。
	 * </p>
	 * 
	 * @param date
	 *            待转换的时间。
	 * @param dest
	 *            用户所在时区。
	 * @return 转换后的时间。
	 */
	public static Date transformDateFrom(Date date, TimeZone dest) {

		long offset = dest.getOffset(date.getTime())
				- timeZoneBeijing.getOffset(date.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime() - offset);
		return cal.getTime();
	}

	/**
	 * <p>
	 * 把网银时间转成用户当地时间。
	 * </p>
	 * 
	 * @param date
	 *            待转换的时间。
	 * @param dest
	 *            用户所在时区。
	 * @return 转换后的时间。
	 */
	public static Date transformDateInto(Date date, TimeZone dest) {
		long offset = dest.getOffset(date.getTime())
				- timeZoneBeijing.getOffset(date.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime() + offset);
		return cal.getTime();
	}

	/**
	 * 校验起始日期和结束日期的合法性
	 * <p>
	 * 例如：起始日期距当前日期不超过12个月，起始结束日期间隔不超过3个月，调用<br>
	 * validateDateRange(startDate, endDate, currentDate, 3, 12)
	 * 
	 * @param startDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @param currentDate
	 *            当前日期
	 * @param maxInterval
	 *            起始日期和结束日期的最大距离（单位为月）
	 * @param amount
	 *            起始日期和当前日期的最大距离（单位为月）
	 * @return
	 */
	public static boolean validateDateRange(Date startDate, Date endDate,
			Date currentDate, int maxInterval, int amount) {
		if (startDate.after(endDate))
			return false;

		if (currentDate.after(addDate(startDate, Calendar.MONTH, amount)))
			return false;

		if (endDate.after(addDate(startDate, Calendar.MONTH, maxInterval)))
			return false;

		return true;
	}

	/**
	 * 判断输入的年份是否闰年
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}

	public static Date getCurrentDateHHMMSS() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date(System.currentTimeMillis());

		return sdf.parse(sdf.format(currentDate));

	}
	
	public static String getCurrentStringDateHHMMSS()  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date(System.currentTimeMillis());

		return sdf.format(currentDate);

	}
	/**
	 * 获取指定日期的 下月同一日 或上月同一日，不够天数的 以最后一天为准
	 * @author Mr Tang
	 * @param flag true向后跳一个月，false 像前跳一个月
	 * @return Date
	 */
	public static Date getNextMonthDay(Date date,boolean flag,TimeZone timeZone){
		Calendar cal = (timeZone == null ? Calendar.getInstance() : Calendar
				.getInstance(timeZone));
		cal.setTime(date);
		if(flag){
			cal.add(Calendar.MONTH, 1);
		}else{
			cal.add(Calendar.MONTH, -1);
		}
		return cal.getTime();
	}
	
	/**
	 * 获取指定日期的 下月同一日 或上月同一日，不够天数的 以最后一天为准
	 * @author Mr Tang
	 * @param flag true向后跳一个月，false 像前跳一个月
	 * @return Date
	 */
	public static Date getNextMonthDay(Date date,boolean flag){
		
		return getNextMonthDay(date,flag,null);
	}
	
	public static String getCreDtTm(){
		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS ");
		long now=System.currentTimeMillis();
		Calendar calendar =Calendar.getInstance() ;
		calendar.setTimeInMillis(now);
		String nowtime=formatter.format(now);
		String datenow=nowtime.substring(0,10);
		String timenow=nowtime.substring(10).trim();
		String credatm=datenow+"T"+timenow;
		return credatm;

		
	}
	  /**  
     * 功能：得到当前月份月初 格式为：xxxx-yy-zz (eg: 2007-01-01)
     * @return String  
     * @author   
     */  
   public static String thisMonth(int y,int m) {   
       String strM= null;   
       strM= m >= 10 ? String.valueOf(m) : ("0" + m);
       return y + "-" + strM + "-01";   
   }   
   /**  
     * 功能：得到当前月份月底 格式为：xxxx-yy-zz (eg: 2007-12-31)
     * @return String  
     * @author   
     **/  
   public static String thisMonthEnd(int y,int m) {   
       String strM = null;   
       String strZ = null;   
       boolean leap = false;   
      
       if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {   
           strZ = "31";   
       }   
       if (m == 4 || m == 6 || m == 9 || m == 11) {   
           strZ = "30";   
       }   
       if (m == 2) {   
           leap = isLeapYear(y);   
           if (leap) {   
               strZ = "29";   
           }else {   
               strZ = "28";   
           }   
       }   
       strM = m >= 10 ? String.valueOf(m) : ("0" + m);   
       return y+"-"+strM+"-"+strZ;   
   }   
   
   /**
    * 获取时间段内的所有日期
    * Methods Name: getDate
    * Description: 
    * @author name: zhanghua
    * Written Date: 2013-9-4 下午06:05:40
    * Modified By: 
    * Modified Date: 
    * @param statetime
    * @param endtime
    * @return 
    * @return List<String>
    */
   public static List<String> getDate(String statetime , String endtime){
	   List<String> dateArrayList = new ArrayList<String>();
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	   try {
		Date begin=sdf.parse(statetime);
		Date end = sdf.parse(endtime);
		double between = (end.getTime()-begin.getTime())/1000;
		double day = between/(24*3600);
		for(int i=0; i <= day;i++){
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(statetime));
			cd.add(Calendar.DATE, i);
			dateArrayList.add(sdf.format(cd.getTime()));
		}
		
	} catch (ParseException e) {
		return null;
	}
	   return dateArrayList;
   }

   /**
    * 校验这2005-01-26或20050126 俩种的日期格式的 
    * @param dateString
    * @return
    */
   public static boolean checkDateFormat(String dateString){
	   String regex="(((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29)))|(((((1[6-9]|[2-9]\\d)\\d{2})/(0?[13578]|1[02])/(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})/(0?[13456789]|1[012])/(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})/(0?2)/(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))/(0?2)/29)))|(((((1[6-9]|[2-9]\\d)\\d{2})(0?[13578]|1[02])(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})(0?[13456789]|1[012])(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})0?2(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))0?229)))|([0])";
	   return dateString.trim().matches(regex);
   }
   
   /**
    * 计算两个 时间差值
    * 
    * @author Mr Tang
    * @param  date1
    * @param  date2
    * @return String
    */
   public static String subDate(Date date1,Date date2) {
	    long time = date1.getTime()-date2.getTime();
		
		long hour = (time/(60*60*1000));
		long min=((time/(60*1000))-hour*60);
		long s=(time/1000-hour*60*60-min*60);
		long ss=(time-(hour*60*60+min*60+s)*1000);
		
		return (hour+"小时"+min+"分"+s+"秒"+ss+"毫秒");
   }
}