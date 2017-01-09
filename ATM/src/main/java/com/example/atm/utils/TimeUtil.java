package com.example.atm.utils;

import android.annotation.SuppressLint;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

@SuppressLint("SimpleDateFormat")
public class TimeUtil {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getCurrentTimestamp());
	}

	/**
	 * 取得当天日期,格式 2009-02-11
	 * 
	 * @return
	 */
	public static String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd");
		Calendar cl = new GregorianCalendar();
		return sdf.format(cl.getTime());
	}

	/**
	 * 
	 * @param date
	 * @return long[]date日期所在月的第一天的long值和最后一天的long值,index为0是第一天的long值,
	 *         index为1是最后一天的long值
	 */
	public static long[] getMinMaxOfMonth(String date) {
		int[] daysAndMonthNum = getNumOfDaysAndMonthNum(date);
		String year = date.substring(4, date.length());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date minDate = null;
		Date maxDate = null;
		long[] minMaxOfMonth = new long[2];
		try {
			if (daysAndMonthNum[1] < 10) {
				minDate = sdf.parse(year + "-0" + daysAndMonthNum[1] + "-"
						+ "01");
				maxDate = sdf.parse(year + "-0" + daysAndMonthNum[1] + "-"
						+ daysAndMonthNum[0]);
			} else {
				minDate = sdf.parse(year + "-" + daysAndMonthNum[1] + "01");
				maxDate = sdf.parse(year + "-0" + daysAndMonthNum[1] + "-"
						+ +daysAndMonthNum[0]);
			}
			minMaxOfMonth[0] = minDate.getTime();
			minMaxOfMonth[1] = maxDate.getTime();

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return minMaxOfMonth;
	}

	public static int[] getNumOfDaysAndMonthNum(String date) {
		String month = date.substring(0, 3);
		int num[] = new int[2];
		switch (month) {
		case "Jan":
			num[0] = 31;
			num[1] = 1;
			break;
		case "Feb":
			num[0] = 28;
			num[1] = 2;
			break;
		case "Mar":
			num[0] = 31;
			num[1] = 3;
			break;
		case "Apr":
			num[0] = 30;
			num[1] = 4;
			break;
		case "May":
			num[0] = 31;
			num[1] = 5;
			break;
		case "Jun":
			num[0] = 30;
			num[1] = 6;
			break;
		case "Jul":
			num[0] = 31;
			num[1] = 1;
			break;
		case "Aug":
			num[0] = 31;
			num[1] = 8;
			break;
		case "Sep":
			num[0] = 30;
			num[1] = 9;
			break;
		case "Oct":
			num[0] = 31;
			num[1] = 10;
			break;
		case "Nov":
			num[0] = 30;
			num[1] = 11;
		case "Dec":
			num[0] = 31;
			num[1] = 12;
			break;
		}
		return num;

	}

	/**
	 * 取得当天日期,格式 20090211
	 * 
	 * @return
	 */
	public static String getTodayNumber() {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyyMMdd");
		Calendar cl = new GregorianCalendar();
		return sdf.format(cl.getTime());
	}

	/**
	 * 给出日期转换成格式 2009-02-11,如果date为空那么返回null
	 * 
	 * @param date
	 * @return
	 */
	public static String getZDDay(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 取得当天日期时间,格式 2009-02-11 23:9:21
	 * 
	 * @return
	 */
	public static String getTodaytime() {
		Calendar cl = new GregorianCalendar();
		return getToday() + " " + cl.get(Calendar.HOUR_OF_DAY) + ":"
				+ cl.get(Calendar.MINUTE) + ":" + cl.get(Calendar.SECOND) + " ";
	}

	/**
	 * 取得当前时间,格式 23:12:20
	 * 
	 * @return
	 */
	public static String getTime() {
		Calendar cl = new GregorianCalendar();
		return cl.get(Calendar.HOUR_OF_DAY) + ":" + cl.get(Calendar.MINUTE)
				+ ":" + cl.get(Calendar.SECOND) + " ";
	}

	/**
	 * 取得当前小时
	 * 
	 * @return
	 */
	public static int getHour() {
		Calendar cl = new GregorianCalendar();
		return cl.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 取得当前日期 格式为20090211
	 * 
	 * @return
	 */
	public static String getNoFormatToday() {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyyMMdd");
		Calendar cl = new GregorianCalendar();
		return sdf.format(cl.getTime());
	}

	/**
	 * 取得当前时间 格式为231611
	 * 
	 * @return
	 */
	public static String getNoFormatTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"HHmmss");
		Calendar cl = new GregorianCalendar();
		return sdf.format(cl.getTime());
	}

	/**
	 * 取得当前年份
	 * 
	 * @return
	 */
	public static String getYear() {
		return TimeUtil.getNoFormatToday().substring(0, 4);
	}

	/**
	 * 取得当前月份
	 * 
	 * @return
	 */
	public static String getMonth() {
		return TimeUtil.getNoFormatToday().substring(4, 6);
	}

	/**
	 * 取得当前日
	 * 
	 * @return
	 */
	public static String getDay() {
		return TimeUtil.getNoFormatToday().substring(6, 8);
	}

	/**
	 * 返回N天前（后的）日期，正数是后的日期，负数是前的日期。例如：2009-02-11 12:12:12
	 * 
	 * @param number
	 * @return
	 */
	public static String getYesterday(int number) {
		String strYesterday = "";
		Calendar cale = null;
		cale = new GregorianCalendar();
		cale.add(Calendar.DATE, number);
		strYesterday = TimeUtil.getStrByCalendar(cale);
		return strYesterday;
	}

	public static String getStrByCalendar(Calendar cale) {
		return (new SimpleDateFormat("yyyy-MM-dd")).format(cale
				.getTime());
	}

	/**
	 * 日期字符串的格式转换,例如"2009-02-11"转换为2009年2月11日
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getChnDateString(String sDate) {
		if (sDate == null) {
			return null;
		}
		sDate = sDate.trim();
		if (sDate.length() == 7) {
			sDate += "-01";
		}
		StringTokenizer st = new StringTokenizer(sDate, "-");
		int year = 2100;
		int month = 0;
		int day = 1;
		try {
			year = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken()) - 1;
			day = Integer.parseInt(st.nextToken());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar cl = new GregorianCalendar(year, month, day);
		return cl.get(Calendar.YEAR) + "年" + (cl.get(Calendar.MONTH) + 1) + "月"
				+ cl.get(Calendar.DATE) + "日";
	}

	/**
	 * 取得某年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getMaxDayOfMonth(int year, int month) {
		Calendar cal = new GregorianCalendar(year, month - 1, 1);
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	/**
	 * 取得某年某月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getMinDayOfMonth(int year, int month) {
		Calendar cal = new GregorianCalendar(year, month - 1, 1);
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	/**
	 * 取得当天的中文日期，像2006年11月28日 星期二
	 * 
	 * @return
	 */
	public static String getChineseToDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 E",
				Locale.CHINESE);
		Calendar cl = new GregorianCalendar();
		return sdf.format(cl.getTime());
	}

	/**
	 * 取得当天的中文日期，像2006年11月28日 星期二 下午05:06
	 * 
	 * @return
	 */
	public static String getChineseToDayTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 E a",
				Locale.CHINESE);
		Calendar cl = new GregorianCalendar();
		return sdf.format(cl.getTime());
	}

	/**
	 * 根据字符串，取得日期类
	 * 
	 * @param sDate
	 * @return
	 */
	public static Calendar getDate(String sDate) {
		if (sDate == null) {
			return null;
		}
		sDate = sDate.trim();
		if (sDate.length() == 7) {
			sDate += "-01";
		}
		StringTokenizer st = new StringTokenizer(sDate, "-");
		int year = 2100;
		int month = 0;
		int day = 1;
		try {
			year = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken()) - 1;
			day = Integer.parseInt(st.nextToken());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new GregorianCalendar(year, month, day);
	}

	/**
	 * 根据日期类取得日期的字符串形式
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getDateString(Calendar sDate) {
		if (sDate == null) {
			return "";
		}
		return (new SimpleDateFormat("yyyy-MM-dd")).format(sDate
				.getTime());
	}

	/**
	 * 根据日期类取得日期的字符串形式
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getDateString(Date sDate) {
		if (sDate == null) {
			return "";
		}
		return (new SimpleDateFormat("yyyy-MM-dd")).format(sDate
				.getTime());
	}

	/**
	 * 根据日期类取年月的字符串形式
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getYearMonth(Calendar sDate) {
		if (sDate == null) {
			return "";
		}
		return (new SimpleDateFormat("yyyy-MM")).format(sDate
				.getTime());
	}

	/**
	 * 比较两个日期类型的字符串，格式为（yyyy-mm-dd） 如果cale1大于cale2，返回1 如果cale1小于cale2，返回-1
	 * 如果相等，返回0 如果格式错误，返回-2
	 * 
	 * @param cale1
	 * @param cale2
	 * @return
	 */
	public static int compareCalendar(String cale1, String cale2) {
		Calendar calendar1 = getDate(cale1);
		Calendar calendar2 = getDate(cale2);
		if (calendar1 == null || calendar2 == null) {
			return -2;
		}
		return calendar1.compareTo(calendar2);
	}

	/**
	 * 获取当前时间的 Timestamp
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTimestamp() {

		Timestamp tt = new Timestamp(System.currentTimeMillis());
		return tt;
	}

	/**
	 * 将Timestamp格式时间转换为String类型
	 * 
	 * @param time
	 * @return
	 */
	public static String getTimestampToString(Timestamp time) {
		String t = "";
		if (!"".equals(time) || time == null) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			t = sf.format(time);
		}

		return t;
	}

	/**
	 * 将String类型转换为Timestamp类型
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp getStringToTimestamp(String time) {

		if (!"".equals(time) || time == null) {
			Timestamp t = Timestamp.valueOf(time);
			return t;
		}
		return null;
	}

	/**
	 * 得到某个时间几小时之后的时间
	 * 
	 * @param time
	 * @param delaylimit
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getAfterNHoursDate(String time, BigDecimal delaylimit) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BigDecimal m = delaylimit.multiply(new BigDecimal(60));
		// double m = delaylimit.doubleValue() * 60;
		Date date = null;
		try {
			date = sf.parse(time);
			date.setMinutes(date.getMinutes() + m.intValue());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sf.format(date);
	}

	/**
	 * 比较两个String类型时间的大小
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean compareDate(String time1, String time2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean flag = false;
		try {
			Date t1 = (Date) df.parse(time1);
			Date t2 = (Date) df.parse(time2);
			flag = t1.getTime() > t2.getTime();
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return flag;
	}

	// 转化月
	public static String getMonth(int month) {
		String str = null;
		switch (month) {
		case 1:
			str = "Jan ";
			break;
		case 2:
			str = "Feb ";
			break;
		case 3:
			str = "Mar ";
			break;
		case 4:
			str = "Apr ";
			break;
		case 5:
			str = "May ";
			break;
		case 6:
			str = "Jun ";
			break;
		case 7:
			str = "Jul ";
			break;
		case 8:
			str = "Aug ";
			break;
		case 9:
			str = "Sep ";
			break;
		case 10:
			str = "Oct ";
			break;
		case 11:
			str = "Nov ";
			break;
		case 12:
			str = "Dec ";
			break;
		}

		return str;
	}

	// 转化月
	public static int getMonthToText(String month) {
		int str = 0;
		switch (month) {
		case "Jan":
			str = 1;
			break;
		case "Feb":
			str = 2;
			break;
		case "Mar":
			str = 3;
			break;
		case "Apr":
			str = 4;
			break;
		case "May":
			str = 5;
			break;
		case "Jun":
			str = 6;
			break;
		case "Jul":
			str = 7;
			break;
		case "Aug":
			str = 8;
			break;
		case "Sep":
			str = 9;
			break;
		case "Oct":
			str = 10;
			break;
		case "Nov":
			str = 11;
			break;
		case "Dec":
			str = 12;
			break;
		}

		return str;
	}

	// 取得星期几
	public static String getWeekOfDate(Date date) {

		String[] weekOfDays = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		Calendar calendar = Calendar.getInstance();

		if (date != null) {
			calendar.setTime(date);
		}

		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];
	}

	// 获得系统当前时间
	public static String getNowDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(new Date());
	}
}
