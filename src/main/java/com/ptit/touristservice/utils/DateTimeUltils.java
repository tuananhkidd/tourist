package com.ptit.touristservice.utils;


import com.ptit.touristservice.constants.Constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUltils {
    public static Integer HOUR_PER_DAY = 24;
    public static Integer MINUTE_PER_HOUR = 60;
    public static Integer MINUTE_PER_DAY = HOUR_PER_DAY * MINUTE_PER_HOUR;

    public static String toDateString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }



    public static Date calculateExpirationDate(int numberOfDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, numberOfDay);
        return cal.getTime();
    }

    public static Date addDayToDate(Date date, int numberOfDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, numberOfDay);
        return cal.getTime();
    }



    public static int getTimeInMinute(int hour, int minute) throws InvalidTimeException {
        if (minute >= MINUTE_PER_HOUR) {
            hour += minute / MINUTE_PER_HOUR;
            minute = minute % MINUTE_PER_HOUR;
        }
        if (0 > hour || hour > HOUR_PER_DAY) {
            throw new InvalidTimeException("Hour must be from 0 to " + HOUR_PER_DAY);
        }
        return hour * MINUTE_PER_HOUR + minute;
    }

    public static String toHour(int minute) {
        int hour = minute / 60;
        minute = minute % 60;
        return hour + ":" + (minute < 10 ? "0" + minute : minute);
    }

    public static class InvalidTimeException extends Exception {
        public InvalidTimeException(String msg) {
            super(msg);
        }
    }

    public static String calculateTimeLeft(Date endTime, int level, long currentTime) {
        String result = "";
        int currentLevel = 0;

        long timeDiff = endTime.getTime() - currentTime;
        String headPrefix = "";
        String tailPrefix = "";
        if (timeDiff > 0) {
            headPrefix = Constant.LEFT + " ";
        } else {
            tailPrefix = Constant.AGO;
        }
        long absTimeDiff = Math.abs(timeDiff);

        long month = absTimeDiff / Constant.A_MONTH_IN_MILLIS;
        if (month > 0) {
            result += month + " " + Constant.MONTH + " ";
            currentLevel++;
            if (currentLevel == level) {
                return headPrefix + result + tailPrefix;
            }
            absTimeDiff = absTimeDiff % Constant.A_MONTH_IN_MILLIS;
        }

        long day = absTimeDiff / Constant.A_DAY_IN_MILLIS;
        if (day > 0) {
            result += day + " " + Constant.DAY + " ";
            currentLevel++;
            if (currentLevel == level) {
                return headPrefix + result + tailPrefix;
            }
            absTimeDiff = absTimeDiff % Constant.A_DAY_IN_MILLIS;
        }

        long hour = absTimeDiff / Constant.AN_HOUR_IN_MILLIS;
        if (hour > 0) {
            result += hour + " " + Constant.HOUR + " ";
        }

        return headPrefix + result + tailPrefix;
    }



}
