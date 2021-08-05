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
        SimpleDateFormat formatter= new SimpleDateFormat("mm:ss");
        Long sub = limit*60*1000 - (System.currentTimeMillis() - timeStart);
        if (sub <0) return String.valueOf(sub);
        Date date = new Date(sub);
        return formatter.format(date);
    }

    public static String getCurrentDateTime(String format)
    {
        SimpleDateFormat formatter= new SimpleDateFormat(format);
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

}
