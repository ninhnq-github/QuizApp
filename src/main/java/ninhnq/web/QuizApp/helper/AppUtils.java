package ninhnq.web.QuizApp.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class AppUtils {

    public static String getNextDate(int days){
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(getCurrentDateTime("yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) return "1997-01-01";
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return format.format(calendar.getTime());
    }

    public static String getCurrentDateTime()
    {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public static String getTimeLeft(Long timeStart, Long limit)
    {
        Long sub = limit*60*1000 - (System.currentTimeMillis() - timeStart);
        if (sub <0) return "TIMEOUT";
        int h = (int) (sub/(60*60*1000));
        sub = sub%(60*60*1000);
        int m = (int) (sub/(60*1000));
        sub = sub%(60*1000);
        int s = (int) (sub/(1000));
        String time = h+":"+m+":"+s;
        return time;
    }

    public static String getCurrentDateTime(String format)
    {
        SimpleDateFormat formatter= new SimpleDateFormat(format);
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public static long getTimeMilis(String time, String pattern)  {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = (Date)formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long mills = date.getTime();
        return mills;
    }

    public static String getDateTimeFormat(long time, String pattern){
        SimpleDateFormat formatter= new SimpleDateFormat(pattern);
        Date date = new Date(time);
        return formatter.format(date);
    }
}
