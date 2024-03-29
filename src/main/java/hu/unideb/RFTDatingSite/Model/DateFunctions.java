package hu.unideb.RFTDatingSite.Model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.util.Calendar.*;
import static java.util.Calendar.DATE;

public class DateFunctions {

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static int yearsPassedSince(Date first) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(new java.util.Date());
        int diff = b.get(YEAR) - +a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Calendar getCalendar(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static String getDateFromYearsAgoInString(int years) throws ParseException {
        Calendar cal = Calendar.getInstance();
        Calendar b = getCalendar(new java.util.Date());
        cal.set(YEAR, b.get(YEAR)-years);
       return cal.get(YEAR)+"-"+cal.get(MONTH)+"-"+cal.get(DAY_OF_MONTH);
    }

}
