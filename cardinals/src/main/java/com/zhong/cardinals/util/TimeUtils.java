package com.zhong.cardinals.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间格式化工具类
 */
public class TimeUtils {
    public static final String DATE_FORMAT_YEAR_TO_SECONDS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YEAR_TO_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_YEAR_TO_DAY = "yyyy-MM-dd";
    public static final String DATE_FORMAT_HOUR_TO_SECONDS = "HH:mm:ss";
    public static final String DATE_FORMAT_YEAR_TO_MONTH = "yyyy-MM";
    public static final String DATE_FORMAT_MONTH_TO_MINUTE = "MM-dd HH:mm";
    public static final String DATE_FORMAT_MONTH_TO_SECONDS = "MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_MONTH_TO_DAY = "MM-dd";
    public static final String DATE_FORMAT_YEAR_TO_DAY_CN = "yyyy年MM月dd日";
    public static final String DATE_FORMAT_WEEK = "EEEE";
    public static final String DATE_FORMAT_YEAR_TO_DAY_2 = "yyyyMMdd";
    public static final String DATE_FORMAT_YEAR_TO_WEEK = "yyyy.MM.dd EEEE";
    private static final int seconds_of_1minute = 60;
    private static final int seconds_of_30minutes = 30 * 60;
    private static final int seconds_of_1hour = 60 * 60;
    private static final int seconds_of_1day = 24 * 60 * 60;
    private static final int seconds_of_15days = seconds_of_1day * 15;
    private static final int seconds_of_30days = seconds_of_1day * 30;
    private static final int seconds_of_6months = seconds_of_30days * 6;
    private static final int seconds_of_1year = seconds_of_30days * 12;
    // 一分钟的毫秒数
    private static long MILLISECOND_OF_MINUTES = 60 * 1000;
    // 一小时的毫秒数
    private static long MILLISECOND_OF_HOUR = 60 * MILLISECOND_OF_MINUTES;
    // 一天的毫秒数
    private static long MILLISECOND_OF_DAY = 24 * MILLISECOND_OF_HOUR;

    /**
     * @param template 时间模板
     * @return 返回当前时间
     */
    public static String getNowTime(String template) {
        SimpleDateFormat df = new SimpleDateFormat(template, Locale.getDefault());
        return df.format(new Date());
    }

    /**
     * @param format
     * @param position
     * @return 获取前几天或者后几天
     */
    public static String getPastDate(String format, int position) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, position);
        return new SimpleDateFormat(format, Locale.getDefault()).format(cal.getTime());
    }

    public static String getDateFormat(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(date);
    }

    public static String getDateFormat(String time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        Date date = null;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return dateFormat.format(date);
    }

    public static Date getFormatDate(String time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        Date date = null;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /*
    * 毫秒转化
    */
    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        // String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        String time;
        if (hour > 0) {
            time = strHour + ":" + strMinute + ":" + strSecond;
        } else {
            time = strMinute + ":" + strSecond;
        }

        return time;
    }

    /**
     * 把毫秒转化成日期
     *
     * @param dateFormat 格式化模板
     * @param millSec    时间
     * @return 格式化后的结果
     */
    public static String transferLongToDate(String dateFormat, long millSec) {
        Date d = new Date(millSec);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return sdf.format(d);
    }

    /**
     * @return timtPoint距离现在经过的时间，分为
     * 刚刚，1-29分钟前，半小时前，1-23小时前，1-14天前，半个月前，1-5个月前，半年前，1-xxx年前
     */
    public static String getTimeElapse(long createTime) {

        long nowTime = new Date().getTime() / 1000;

        //createTime是发表文章的时间

        long oldTime = createTime / 1000;

        //elapsedTime是发表和现在的间隔时间

        long elapsedTime = nowTime - oldTime;

        if (elapsedTime < seconds_of_1minute) {

            return "刚刚";
        }
        if (elapsedTime < seconds_of_30minutes) {

            return elapsedTime / seconds_of_1minute + "分钟前";
        }
        if (elapsedTime < seconds_of_1hour) {

            return "半小时前";
        }
        if (elapsedTime < seconds_of_1day) {

            return elapsedTime / seconds_of_1hour + "小时前";
        }
        if (elapsedTime < seconds_of_15days) {
            if (elapsedTime / seconds_of_1day <= 2) {
                return elapsedTime / seconds_of_1day + "天前";
            }
        }
//        if (elapsedTime < seconds_of_30days) {
//
//            return "半个月前";
//        }
//        if (elapsedTime < seconds_of_6months) {
//
//            return elapsedTime / seconds_of_30days + "月前";
//        }
//        if (elapsedTime < seconds_of_1year) {
//
//            return "半年前";
//        }
//        if (elapsedTime >= seconds_of_1year) {
//
//            return elapsedTime / seconds_of_1year + "年前";
//        }

        return transferLongToDate(DATE_FORMAT_MONTH_TO_MINUTE, createTime);
    }

    /**
     * 判断某一时间是否在一个区间内
     *
     * @param sourceTime 时间区间,半闭合,如[10:00-20:00)
     * @param curTime    需要判断的时间 如10:00
     * @return
     * @throws IllegalArgumentException
     */
    public static boolean isInTime(String sourceTime, String curTime) {
        //Logger.d(sourceTime+"  :  "+sourceTime.contains("-"));
       /* if (sourceTime == null || !sourceTime.contains("—") || !sourceTime.contains(":")) {
           // throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);

            return false;
        }*/

        if (sourceTime == null || !sourceTime.contains("—") || !sourceTime.contains(":")) {
            Logger.d("格式化数据不规范1 " + sourceTime + " - " + sourceTime.contains("—") + " ： " + sourceTime.contains(":"));
            return false;
        }
        if (curTime == null || !curTime.contains(":")) {
            //  throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
            Logger.d("格式化数据不规范2");
            return false;
        }
        String[] args = sourceTime.split("-");
        Logger.d("time1=" + args[0] + " ; " + args[1] + " -" + sourceTime.contains("-"));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            long now = sdf.parse(curTime).getTime();
            long start = sdf.parse(args[0]).getTime();
            long end = sdf.parse(args[1]).getTime();
            if (args[1].equals("00:00")) {
                args[1] = "24:00";
            }
            if (end < start) {
                return !(now >= end && now < start);
            } else {
                return now >= start && now < end;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Logger.d("格式化数据错误");
            return false;
            // throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }

    }


}
